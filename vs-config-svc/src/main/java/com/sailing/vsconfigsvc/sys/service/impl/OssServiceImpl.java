package com.sailing.vsconfigsvc.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sailing.vsconfigsvc.config.ImageConfig;
import com.sailing.vsconfigsvc.enums.OssErr;
import com.sailing.vsconfigsvc.sys.domain.entity.Oss;
import com.sailing.vsconfigsvc.sys.mapper.OssMapper;
import com.sailing.vsconfigsvc.sys.service.IOssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sailing.vsconfigsvc.sys.utils.Assert;
import com.sailing.vsconfigsvc.sys.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 上传 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2020-03-31
 */
@Service
@Slf4j
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements IOssService {


    /**
     * oss
     */
    @Resource
    private OssMapper sysOssDao;
    @Autowired
    private ImageConfig imageConfig;


    /**
     * 保存文件
     *
     * @param file 文件对象
     * @param type 类别：1-图片，2-文件
     * @return
     */
    private Oss save(MultipartFile file, Long id, int type) {
        String contentType = file.getContentType();

        String filePath = imageConfig.getPath();
        Oss poss = sysOssDao.selectById(id);
        String path = poss.getPath();
        // 原始文件名
        String fileName = file.getOriginalFilename();
        Oss sysOss = new Oss();
        sysOss.setFile(fileName);
        sysOss.setPath(path);
        sysOss.setStatus(1);
        sysOss.setType(contentType);
        sysOss.setPid(poss.getId());
//        String nFileName = FileUtils.getExtension(fileName);
        //上传文件
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.debug("filePath:{},name:{}", path, fileName);
        try {

            FileUtils.uploadFile(file.getBytes(), filePath + "/" + path, fileName);
            Oss obj = getFileByPath( path + "/" + fileName);
            if (ObjectUtils.isEmpty(obj)) {
                sysOssDao.insert(sysOss);
            }

        } catch (Exception e) {
            Assert.error(OssErr.sys_unknown_err.getDescription());
        }
        return sysOss;
    }


    @Override
    public List<Oss> getChildDataById(Long id) {
        return sysOssDao.getChildDataById(id);
    }

    @Override
    public Oss saveImg(MultipartFile file, Long id) {
        return this.save(file, id, 1);
    }

    @Override
    public Oss saveFile(MultipartFile file, Long id) {
        return this.save(file, id, 2);
    }

    @Override
    public Oss saveBase64Img(String base64, Long id, String fileName) {
        String contentType = "image/" + FileUtils.getExtension(fileName);
        Oss poss = sysOssDao.selectById(id);
        String path = poss.getPath();
        String filePath = imageConfig.getPath();
        Oss sysOss = new Oss();
        String nFileName = FileUtils.getExtension(fileName);
        sysOss.setName(fileName);
        sysOss.setPath(path + "/" + nFileName);
        sysOss.setStatus(1);
        sysOss.setType(contentType);
        sysOss.setPid(poss.getId());
        //上传文件
        try {
            FileUtils.uploadBase64Image(base64, filePath, nFileName);
            Oss obj = getFileByPath( path + "/" + fileName);
            if (ObjectUtils.isEmpty(obj)) {
                sysOssDao.insert(sysOss);
            }
        } catch (Exception e) {
            Assert.error(OssErr.sys_unknown_err.getDescription());
        }
        return sysOss;
    }

    private Oss getFileByPath(String fileName) {
        Oss query = new Oss();
        int len = fileName.lastIndexOf('/');
        query.setFile(fileName.substring(len + 1, fileName.length()));
        query.setPath(fileName.substring(0, len));
        Oss sysOss = sysOssDao.selectOne(new QueryWrapper<>(query));
        return sysOss;

    }

    @Override
    public byte[] getFile(String fileName) {

        Oss sysOss = getFileByPath(fileName);
        Assert.notNull(sysOss, "文件不存在：" + fileName);
        byte data[] = new byte[0];
        try {
            Assert.isTrue(FileUtils.exists(imageConfig.getPath() + fileName), "文件不存在！");
            File file = new File(imageConfig.getPath() + fileName);
            // 读取文件
            FileInputStream is = new FileInputStream(file);
            int i = is.available(); // 得到文件大小
            data = new byte[i];
            is.read(data); // 读数据
            is.close();
            return data;
        } catch (FileNotFoundException e) {
            Assert.error(OssErr.unknown_file_type.getDescription());
        } catch (IOException e) {
            Assert.error(OssErr.sys_unknown_err.getDescription());
        }
        return data;
    }

    @Override
    public byte[] getFileWater(String fileName) {
        Oss sysOss = sysOssDao.selectById(FileUtils.getFileName(fileName));
        byte data[] = new byte[0];
        try {
            File tempFile = new File(sysOss.getName());
            //给每一张图片添加水印
            org.springframework.core.io.Resource res = new ClassPathResource("BD.png");
            BufferedImage watermarkImage = ImageIO.read(res.getFile());
            Thumbnails.of(imageConfig.getPath() + sysOss.getPath()).scale(1).watermark(Positions.CENTER, watermarkImage, 0.5f).toFile(tempFile);

            // 读取文件
            FileInputStream is = new FileInputStream(tempFile);
            int i = is.available(); // 得到文件大小
            data = new byte[i];
            is.read(data); // 读数据
            is.close();
            tempFile.delete();//删除自动保存的水印图片
            return data;
        } catch (FileNotFoundException e) {
            Assert.error(OssErr.unknown_file_type.getDescription());
        } catch (IOException e) {
            Assert.error(OssErr.sys_unknown_err.getDescription());
        }
        return data;
    }

    @Override
    public boolean save(Oss entity) {
        Oss sysOss = this.baseMapper.selectById(entity.getPid());
        String path = entity.getCode();
        if (!ObjectUtils.isEmpty(sysOss)) {
            path = sysOss.getPath() + "/" + path;
        }
        entity.setPath(path);
        if(null ==entity.getPid()){
            entity.setPid(0L);
        }
        return this.retBool(this.baseMapper.insert(entity));

    }
}

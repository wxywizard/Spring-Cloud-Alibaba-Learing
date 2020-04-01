package com.sailing.vsconfigsvc.sys.controller;



import com.sailing.vsconfigsvc.config.ImageConfig;
import com.sailing.vsconfigsvc.enums.OssErr;
import com.sailing.vsconfigsvc.sys.domain.entity.Oss;
import com.sailing.vsconfigsvc.sys.service.IOssService;
import com.sailing.vsconfigsvc.sys.utils.Assert;
import com.sailing.vsconfigsvc.sys.utils.FileConfig;
import com.sailing.vsconfigsvc.sys.utils.FileUtils;
import com.sailing.vsconfigsvc.utils.ResultVoUtil;
import com.sailing.vsconfigsvc.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 上传 前端控制器
 * </p>
 *
 * @author auto
 * @since 2019-10-12
 */
@RestController
@RequestMapping("sys/oss")
@Api(tags = "文件上传")
public class OssController{

    @Autowired
    private IOssService iOssService;
    @Autowired
    private ImageConfig imageConfig;

    private static long imgMaxSize = 4 * 1024 * 1024 * 8; // 4mb
    private static long fileMaxSize = 10 * 1024 * 1024 * 8; //10mb


    /**
     * 获取图片
     */
    @GetMapping("images")
    @ApiOperation("获取当前模块下的图片列表")
    public List<Oss> getImgs(Long id) {
        return iOssService.getChildDataById(id);
    }

    /**
     * 上传图片
     */
    @PostMapping("img/base64/upload")
    @ApiOperation(value = "上传base64", notes = "上传base64")
    public ResultVo uploadBase64Img(String base64, Long id, String fileName) throws Exception {
        Assert.isBlank(imageConfig.getPath(), "未设置图片上传路径");
        Assert.isBlank(imageConfig.getType().toString(), "未设置图片上传格式");
        Assert.isNull(base64, "图片内容不能为空");
        Assert.isNull(fileName, "原始文件名不能为空");
        long size = base64.getBytes("utf-8").length;
        if (size > imgMaxSize) {
            Assert.error(OssErr.picture_max_size.getDescription());
        }
        Oss sysOss = iOssService.saveBase64Img(base64, id, fileName);
        return ResultVoUtil.success(sysOss);
    }

    @PostMapping("upload/img")
    @ApiOperation("图片上传")
    public ResultVo uploadImg(MultipartFile file, Long id) throws Exception {
        Assert.isBlank(imageConfig.getPath(), "未设置图片上传路径");
        Assert.isBlank(imageConfig.getType().toString(), "未设置图片上传格式");
        if (file.isEmpty()) {
            Assert.error(OssErr.file_null.getDescription());
        }
        long size = file.getSize();
        if (size > imgMaxSize) {
            Assert.error(OssErr.picture_max_size.getDescription());
        }
        Oss sysOss = iOssService.saveImg(file, id);
        return ResultVoUtil.success(sysOss);
    }

    /**
     * 上传文件
     */
    @PostMapping("upload/file")
    @ApiOperation("文件上传")
    public ResultVo uploadFile(@RequestParam("file") MultipartFile file, Long id) throws Exception {
        Assert.isBlank(FileConfig.getPath(), "未设置文件上传路径");
        Assert.isNull(file, OssErr.file_null.getDescription());
        Assert.isTrue(file.getSize() < imgMaxSize, OssErr.picture_max_size.getDescription());
        Oss oss = iOssService.getById(id);
        Oss sysOss = iOssService.saveFile(file, id);
        return ResultVoUtil.success(sysOss);
    }

    /**
     * 获取图片
     *
     * @param name 文件名称，restful中的参数若包含[.]则必须是[name:.+]
     */
    @GetMapping("image")
    @ApiOperation("获取图片")
    public void getImg(String name, HttpServletResponse response) {
        Assert.isBlank(name, "缺少参数fileName");
        List<String> listType = Arrays.asList(imageConfig.getType());
        String suffix = FileUtils.getExtension(name);
        Assert.notIn(listType, suffix, OssErr.picture_format_err.getDescription());

        byte[] data = iOssService.getFile(name);
        response.setContentType("image/" + FileUtils.getExtension(name)); // 设置返回的文件类型
        try {
            OutputStream stream = response.getOutputStream();
            stream.write(data); // 输出数据
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取水印图片
     *
     * @param name 文件名称，restful中的参数若包含[.]则必须是[name:.+]
     */
    @GetMapping("waterImages")
    @ApiOperation("获取带水印图片")
    public void getImgWater(String name, HttpServletResponse response) {
        Assert.isBlank(name, "缺少参数fileName");
        List<String> listType = Arrays.asList(imageConfig.getType());
        String suffix = FileUtils.getExtension(name);
        Assert.notIn(listType, suffix, OssErr.picture_format_err.getDescription());
        byte[] data = iOssService.getFileWater(name);
        response.setContentType("image/" + FileUtils.getExtension(name)); // 设置返回的文件类型
        try {
            OutputStream stream = response.getOutputStream();
            stream.write(data);  // 输出数据
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件
     *
     * @param file 文件名称，restful中的参数若包含[.]则必须是[name:.+]
     */
    @GetMapping("file")
    @ApiOperation("获取文件")
    public void getFile(String file, HttpServletResponse response) {
        Assert.isBlank(file, "缺少参数fileName");
        byte[] data = iOssService.getFile(file);
        try {
            OutputStream stream = response.getOutputStream();
            stream.write(data); // 输出数据
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取上传图片支持的文件类型
     *
     * @return
     */
    @GetMapping("imgTypes")
    public ResultVo getImgTypes() {
        return ResultVoUtil.success(imageConfig.getType().toString());
    }


}


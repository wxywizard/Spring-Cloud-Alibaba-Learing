package com.sailing.vsconfigsvc.sys.service;

import com.sailing.vsconfigsvc.sys.domain.entity.Oss;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 上传 服务类
 * </p>
 *
 * @author wangxy
 * @since 2020-03-31
 */
public interface IOssService extends IService<Oss> {

    List<Oss> getChildDataById(Long id);
    Oss saveImg(MultipartFile file, Long id);

    Oss saveFile(MultipartFile file, Long id);

    Oss saveBase64Img(String base64, Long id, String fileName);

    byte[] getFile(String fileName);

    byte[] getFileWater(String fileName);
}

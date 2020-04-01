package com.sailing.vsconfigsvc.sys.mapper;

import com.sailing.vsconfigsvc.sys.domain.entity.Oss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 上传 Mapper 接口
 * </p>
 *
 * @author wangxy
 * @since 2020-03-31
 */
public interface OssMapper extends BaseMapper<Oss> {

    List<Oss> getChildDataById(Long id);
}

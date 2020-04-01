package com.sailing.vsconfigsvc.sys.service.impl;

import com.sailing.vsconfigsvc.sys.domain.entity.TUser;
import com.sailing.vsconfigsvc.sys.mapper.TUserMapper;
import com.sailing.vsconfigsvc.sys.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2020-04-01
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}

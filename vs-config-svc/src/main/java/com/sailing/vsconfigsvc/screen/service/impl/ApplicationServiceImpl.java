package com.sailing.vsconfigsvc.screen.service.impl;

import com.sailing.vsconfigsvc.screen.domain.entity.Application;
import com.sailing.vsconfigsvc.screen.mapper.ApplicationMapper;
import com.sailing.vsconfigsvc.screen.service.IApplicationService;
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
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

}

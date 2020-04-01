package com.sailing.vsconfigsvc.screen.service.impl;

import com.sailing.vsconfigsvc.screen.domain.entity.Component;
import com.sailing.vsconfigsvc.screen.mapper.ComponentMapper;
import com.sailing.vsconfigsvc.screen.service.IComponentService;
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
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements IComponentService {

}

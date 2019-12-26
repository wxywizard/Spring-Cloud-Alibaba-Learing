package com.wxywizard.contentcenter;

import com.wxywizard.contentcenter.dao.content.ShareMapper;
import com.wxywizard.contentcenter.domain.entity.content.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wangxy
 */
@RestController
public class TestController {

    @Resource
    private ShareMapper shareMapper;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/test")
    public List<Share> testInsert(){
        Share share = new Share();
        share.setAuthor("wxy");
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("123");
        share.setBuyCount(1);
        this.shareMapper.insertSelective(share);
        List<Share> shareList = this.shareMapper.selectAll();
        return shareList;
    }

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("/test2")
    public List<ServiceInstance> getInstances(){
        //查询指定服务的所有实例的信息
        return this.discoveryClient.getInstances("user-center");
    }

}

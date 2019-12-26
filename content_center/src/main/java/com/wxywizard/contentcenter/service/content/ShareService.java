package com.wxywizard.contentcenter.service.content;

import com.wxywizard.contentcenter.dao.content.ShareMapper;
import com.wxywizard.contentcenter.domain.dto.content.ShareDto;
import com.wxywizard.contentcenter.domain.dto.user.UserDto;
import com.wxywizard.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author: wangxy
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public ShareDto findById(Integer id){
        //获取分享详情
        Share share =  this.shareMapper.selectByPrimaryKey(id);
        //发布人id
        Integer userId = share.getUserId();

        //了解stream --> JDK 8
        // lambda表达式
        //functional --> 函数式编程
        //用户中心所有实例的信息
        List<ServiceInstance> instances = this.discoveryClient.getInstances("user-center");
        //所有用户中心实例的请求地址
//        List<String> targetUrls = instances.stream()
//                .map(instance -> instance.getUri().toString()+"/users/{id}")
//                .collect(Collectors.toList());
//        int i = ThreadLocalRandom.current().nextInt(targetUrls.size());

        String targetUrl = instances.stream()
                .map(instance -> instance.getUri().toString()+"/users/{id}")
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));
        //怎么调用用户微服务的/users/{userId}
        //UserDto userDto = this.restTemplate.getForObject("http://172.20.36.177:8040/users/{id}", UserDto.class, userId);
        UserDto userDto = this.restTemplate.getForObject(targetUrl, UserDto.class, userId);
        //消息的装配
        ShareDto shareDto = new ShareDto();
        BeanUtils.copyProperties(share,shareDto);
        shareDto.setWxNickname(userDto.getWxNickname());
        return shareDto;
    }

    /**
     * 使用ribbon负载均衡做法
     * @param id
     * @return
     */
    public ShareDto findById2(Integer id){
        //获取分享详情
        Share share =  this.shareMapper.selectByPrimaryKey(id);
        //发布人id
        Integer userId = share.getUserId();

        //了解stream --> JDK 8
        // lambda表达式
        //functional --> 函数式编程


        //怎么调用用户微服务的/users/{userId}
        //UserDto userDto = this.restTemplate.getForObject("http://172.20.36.177:8040/users/{id}", UserDto.class, userId);
        UserDto userDto = this.restTemplate.getForObject("http://user-center/users/{id}", UserDto.class, userId);
        //消息的装配
        ShareDto shareDto = new ShareDto();
        BeanUtils.copyProperties(share,shareDto);
        shareDto.setWxNickname(userDto.getWxNickname());
        return shareDto;
    }
}

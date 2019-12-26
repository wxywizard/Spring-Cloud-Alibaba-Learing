package com.wxywizard.usercenter.service.user;

import com.wxywizard.usercenter.dao.user.UserMapper;
import com.wxywizard.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wangxy
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserMapper userMapper;

    public User findById(Integer id){

        return this.userMapper.selectByPrimaryKey(id);
    }
}

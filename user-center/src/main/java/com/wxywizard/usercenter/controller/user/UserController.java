package com.wxywizard.usercenter.controller.user;

import com.wxywizard.usercenter.domain.entity.user.User;
import com.wxywizard.usercenter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangxy
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return this.userService.findById(id);
    }
}

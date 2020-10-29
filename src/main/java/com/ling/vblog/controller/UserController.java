package com.ling.vblog.controller;


import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.User;
import com.ling.vblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("login")
    public AjaxResult login(@Valid @RequestBody User user) {
        try {
            UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(upToken);
            return AjaxResult.success();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return AjaxResult.error("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return AjaxResult.error("密码错误");
        } catch (Exception e) {
            return AjaxResult.error("系统错误");
        }
    }

    @PostMapping("register")
    public AjaxResult register(@RequestBody User user) {
        try {
            userService.register(user);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }
}


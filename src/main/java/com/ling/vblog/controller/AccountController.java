package com.ling.vblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.vblog.dto.LoginDto;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.User;
import com.ling.vblog.service.UserService;
import com.ling.vblog.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public AjaxResult login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //断言判断用户是否为空
        Assert.notNull(user, "用户不存在");
        String realPassword = new Md5Hash(loginDto.getPassword(), loginDto.getUsername(),1024).toHex();
        if (!user.getPassword().equals(realPassword)) {
            return AjaxResult.error("密码不正确");
        }
        //生成jwt
        Map<String, String> map = new HashMap<>();
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("nickname", user.getNickname());
        map.put("type",user.getType().toString());
        String jwt = JWTUtils.getToken(map);
        //将jwt防止response的header中
        response.setHeader("Authentication", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authentication");
        return AjaxResult.success(map);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public AjaxResult logout(){
        SecurityUtils.getSubject().logout();
        return AjaxResult.success();
    }


}

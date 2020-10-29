package com.ling.vblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.ling.vblog.entity.User;
import com.ling.vblog.service.UserService;
import com.ling.vblog.utils.JWTUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm  extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String id = JWTUtils.verifyToken(String.valueOf(jwtToken.getPrincipal())).getClaim("id").asString();
        //System.out.println(id+"-------------------------------------");
        User user = userService.getById(Integer.valueOf(id));
        if (user==null){
            throw new UnknownAccountException("账户不存在");
        }
        Profile profile=new Profile();
        profile.setId(user.getId())
                .setAvatar(user.getAvatar())
                .setEmail(user.getEmail())
                .setNickname(user.getNickname())
                .setUsername(user.getUsername());
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}

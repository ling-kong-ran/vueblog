package com.ling.vblog.shiro;

import cn.hutool.json.JSONUtil;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.utils.JWTUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authentication");
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        //  JWTUtils.getToken();
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authentication");
        if (StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            //校验jwt
            try {
                JWTUtils.verifyToken(jwt);
            }catch (ShiroException e){
                e.printStackTrace();
            }
            //执行登录
            return executeLogin(servletRequest,servletResponse);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable=e.getCause()==null? e:e.getCause();
        AjaxResult result=AjaxResult.error(throwable.getMessage());
        String json = JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return super.onLoginFailure(token, e, request, response);
    }

    @Override//进入filter之前的操作，解决跨域问题
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest= WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse= WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-control-Allow-Headers",httpServletRequest.getHeader("Access-control-Allow-Headers"));

        //跨域时会首先发送一个options请求，这里我们给options请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }

        return super.preHandle(request, response);
    }
}

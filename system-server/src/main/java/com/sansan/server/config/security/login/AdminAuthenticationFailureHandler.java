package com.sansan.server.config.security.login;

import com.sansan.server.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  <p> 认证失败处理 - 前后端分离情况下返回json数据格式 </p>
 *
 */
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = e.getMessage();
        } else if (e instanceof LockedException) {
            result = "账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            result = "证书过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            result = "账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            result = "账户被禁用，请联系管理员!";
        } else {
            log.error("登录失败：", e);
            result = "登录失败!";
        }
        ResponseUtils.out(response, new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED));
    }

}

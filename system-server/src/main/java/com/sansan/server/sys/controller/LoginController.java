package com.sansan.server.sys.controller;

import com.sansan.server.user.domain.entity.MrUserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author siss
 * @date 2020/4/20  16:29
 */
@RestController
@RequestMapping
public class LoginController {

    @ResponseBody
    @ApiOperation(value = "登出系统", notes = "用户注销")
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout(){
        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "登录系统", notes = "用户登录获取访问权限")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody MrUserInfo userInfo){
        return new ResponseEntity<>("登陆成功", HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "获取用户", notes = "获取用户信息")
    @RequestMapping(value = "/oauth/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Principal> user(Principal principal){
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}

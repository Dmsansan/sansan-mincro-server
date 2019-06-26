package com.sansan.server.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = "application/json;utf-8")
    public String getUserInfo(){
        return "produce1-server userInfo getted";
    }
}

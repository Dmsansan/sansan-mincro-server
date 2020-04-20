package com.sansan.server.sys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author siss
 * @date 2020/4/20  16:29
 */
@Controller
@RequestMapping(value = "/mrUser")
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout(){
        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }
}

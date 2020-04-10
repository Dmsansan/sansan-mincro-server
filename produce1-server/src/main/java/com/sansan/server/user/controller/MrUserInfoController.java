package com.sansan.server.user.controller;


import com.sansan.server.user.domain.entity.MrUserInfo;
import com.sansan.server.user.service.MrUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author siss
 * @since 2020-04-10
 */
@Controller
@RequestMapping("/mrUser")
public class MrUserInfoController {

    @Autowired
    private MrUserInfoService mrUserInfoService;

    @ResponseBody
    @RequestMapping(path = "/queryUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryUserInfo(){
        MrUserInfo userInfo = mrUserInfoService.getById(1);
        return userInfo.getUserCode();
    }
}

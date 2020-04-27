package com.sansan.server;

import com.sansan.server.user.domain.entity.MrUserInfo;
import com.sansan.server.user.service.MrUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemServerApplicationTests {

    @Autowired
    private MrUserInfoService userInfoService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getUserInfo(){
        MrUserInfo userInfo = userInfoService.getById(1);
        log.info("测试用户信息：{}", userInfo);
    }

    @Test
    public void insertUserInfo(){
        MrUserInfo userInfo = new MrUserInfo();
        userInfo.setUserCode("test");
        userInfo.setUserName("test");
        userInfo.setPassWord("test");
        userInfo.setToken("9e405308be6d4df24d21f6b7548bd40327df0ce6");
        userInfo.setCreateTime(LocalDateTime.now());
        for (int i = 0; i < 15; i++) {
            userInfoService.save(userInfo);
        }
    }

}

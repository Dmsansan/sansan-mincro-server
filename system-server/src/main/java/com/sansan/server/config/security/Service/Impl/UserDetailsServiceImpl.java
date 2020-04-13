package com.sansan.server.config.security.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sansan.server.config.security.dto.SecurityUser;
import com.sansan.server.user.domain.entity.MrUserInfo;
import com.sansan.server.user.mapper.MrUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  <p> 自定义userDetailsService - 认证用户详情 </p>
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MrUserInfoMapper userMapper;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        List<MrUserInfo> userList = userMapper.selectList(new QueryWrapper<MrUserInfo>().eq("user_name", username));
        MrUserInfo user;
        // 判断用户是否存在
        if (!CollectionUtils.isEmpty(userList)){
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user);
    }

}

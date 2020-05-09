package com.sansan.server.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sansan.server.user.domain.entity.MrUserInfo;
import com.sansan.server.user.service.MrUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author siss
 * @since 2020-04-10
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/mrUser")
public class MrUserInfoController {

    @Autowired
    private MrUserInfoService mrUserInfoService;

    @ResponseBody
    @ApiOperation(value = "获取用户信息", notes = "根据ID获取单个用户信息")
    @RequestMapping(path = "/queryUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MrUserInfo> queryUserInfo(){
        MrUserInfo userInfo = mrUserInfoService.getById(1);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "获取用户列表", notes = "获取用户分页信息")
    @RequestMapping(path = "/queryUserInfoByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IPage<MrUserInfo>> queryUserInfoByPage(@RequestParam(value = "curPage") int curPage,
                                                                 @RequestParam(value = "pageNum") int pageNum,
                                                                 @RequestBody MrUserInfo mrUserInfo){
        QueryWrapper<MrUserInfo> queryWrapper = new QueryWrapper<>();
        if(mrUserInfo.getUserCode()!=null) {
            queryWrapper.like("user_code", mrUserInfo.getUserCode());
        }
        if(mrUserInfo.getUserName()!=null) {
            queryWrapper.like("user_name",mrUserInfo.getUserName());
        }

        Page<MrUserInfo> page = new Page<>(curPage, pageNum);
        IPage<MrUserInfo> result = mrUserInfoService.page(page, queryWrapper);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "添加用户信息", notes = "单个用户添加")
    @RequestMapping(path = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody MrUserInfo mrUserInfo){
        ResponseEntity<?> responseEntity = null;
        if(StringUtils.isEmpty(mrUserInfo.getUserCode())||StringUtils.isEmpty(mrUserInfo.getUserName())||
                StringUtils.isEmpty(mrUserInfo.getPassWord())){
            responseEntity = new ResponseEntity<>("缺少必要参数", HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        QueryWrapper<MrUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code", mrUserInfo.getUserCode());
        queryWrapper.or();
        queryWrapper.eq("user_name", mrUserInfo.getUserName());

        List<Object> userInfo = mrUserInfoService.listObjs(queryWrapper);
        if(userInfo.size()>0){
            responseEntity = new ResponseEntity<>("用户已经存在", HttpStatus.FOUND);
            return responseEntity;
        }
        mrUserInfo.setCreateTime(LocalDateTime.now());
        boolean save = mrUserInfoService.save(mrUserInfo);
        if(save){
            responseEntity = new ResponseEntity<>("用户添加成功", HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("用户添加失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}

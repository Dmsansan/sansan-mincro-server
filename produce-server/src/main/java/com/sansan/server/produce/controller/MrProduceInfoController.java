package com.sansan.server.produce.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sansan.server.client.ProduceFeignClient;
import com.sansan.server.entity.ProduceInfoEntity;
import com.sansan.server.produce.domain.entity.MrProduceInfo;
import com.sansan.server.produce.service.MrProduceInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.util.SystemUtil;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author siss
 * @since 2020-05-13
 */
@Controller
@RequestMapping("/produce")
public class MrProduceInfoController implements ProduceFeignClient {

    @Autowired
    private MrProduceInfoService mrProduceInfoService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/queryProduceInfoById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProduceInfoEntity> queryProduceInfoById(){
        MrProduceInfo mrProduceInfo = mrProduceInfoService.getById(18);
        ProduceInfoEntity entity = new ProduceInfoEntity();
        BeanUtils.copyProperties(mrProduceInfo, entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/addProduceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduceInfo(@RequestBody ProduceInfoEntity entity) {
        MrProduceInfo produceInfo = new MrProduceInfo();
        BeanUtils.copyProperties(entity, produceInfo);

        produceInfo.setCreateTime(LocalDateTime.now());
        boolean save = mrProduceInfoService.save(produceInfo);
        return new ResponseEntity<>("新增成功", HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "获取产品列表", notes = "获取产品分页信息")
    @RequestMapping(path = "/queryProduceInfoByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IPage<MrProduceInfo>> queryProduceInfoByPage(@RequestParam(value = "pageSize") int pageSize,
                                                                    @RequestParam(value = "pageNum") int pageNum,
                                                                    @RequestBody MrProduceInfo mrProduceInfo){
        QueryWrapper<MrProduceInfo> queryWrapper = new QueryWrapper<>();
        if(mrProduceInfo.getProduceCode()!=null) {
            queryWrapper.like("produce_code", mrProduceInfo.getProduceCode());
        }
        if(mrProduceInfo.getProduceName()!=null) {
            queryWrapper.like("produce_name",mrProduceInfo.getProduceName());
        }

        Page<MrProduceInfo> page = new Page<>(pageNum, pageSize);
        IPage<MrProduceInfo> result = mrProduceInfoService.page(page, queryWrapper);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

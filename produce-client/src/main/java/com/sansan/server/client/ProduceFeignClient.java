package com.sansan.server.client;

import com.sansan.server.entity.ProduceInfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author siss
 * @date 2020/10/14  14:10
 */
@FeignClient(value = "PRODUCE-SERVER", path = "/produce")
public interface ProduceFeignClient {

    /**
     * 通过id获取产品信息
     *
     * @return
     */
    @RequestMapping(value = "/queryProduceInfoById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProduceInfoEntity> queryProduceInfoById();

    /**
     * 新增产品信息
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addProduceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addProduceInfo(@RequestBody ProduceInfoEntity entity);
}

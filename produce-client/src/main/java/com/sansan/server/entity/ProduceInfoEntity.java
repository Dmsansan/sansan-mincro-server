package com.sansan.server.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author siss
 * @date 2020/10/14  14:13
 */
@Data
public class ProduceInfoEntity {
    private Integer id;

    private String produceCode;

    private String produceName;

    private LocalDateTime createTime;

    private String deletedFlag;
}

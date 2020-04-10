package com.sansan.server.user.mapper;

import com.sansan.server.user.domain.entity.MrUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author siss
 * @since 2020-04-10
 */
@Mapper
public interface MrUserInfoMapper extends BaseMapper<MrUserInfo> {

}

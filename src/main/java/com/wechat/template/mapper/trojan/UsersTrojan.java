package com.wechat.template.mapper.trojan;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechat.template.domain.trojan.Users;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UsersTrojan extends BaseMapper<Users> {
    int updateBatch(List<Users> list);

    int updateBatchSelective(List<Users> list);

    int batchInsert(@Param("list") List<Users> list);

    int insertOrUpdate(Users record);

    int insertOrUpdateSelective(Users record);

    Users findById(int i);

}
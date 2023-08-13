package com.wechat.template.service.trojan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechat.template.domain.trojan.Users;

import java.util.List;

public interface JpUsersService extends IService<Users>{


    int updateBatch(List<Users> list);

    int updateBatchSelective(List<Users> list);

    int batchInsert(List<Users> list);

    int insertOrUpdate(Users record);

    int insertOrUpdateSelective(Users record);

    Users findById(int i);

}

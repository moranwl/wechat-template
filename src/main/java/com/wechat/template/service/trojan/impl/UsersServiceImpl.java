package com.wechat.template.service.trojan.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wechat.template.service.trojan.UsersService;
import com.wechat.template.tool.PasswordHashingExample;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.template.mapper.trojan.UsersTrojan;
import com.wechat.template.domain.trojan.Users;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersTrojan, Users> implements UsersService {


    @Override
    public int updateBatch(List<Users> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Users> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Users> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(final Users record) {
        final String passWord = record.getPassword();
        final Users user = Users.builder()
            .username(record.getUsername())
            .password(PasswordHashingExample.hashPassword(passWord))
            .passwordshow(PasswordHashingExample.base64Encode(passWord))
            .quota(-1L)
            .download(0L)
            .upload(0L)
            .usedays(0)
            .build();
        final Users users = baseMapper.selectOne(new QueryWrapper<Users>().eq("username", user.getUsername()));
        if (!ObjectUtils.isEmpty(users)) {
            user.setId(users.getId());
        }
        return baseMapper.insertOrUpdate(user);
    }
    @Override
    public int insertOrUpdateSelective(Users record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Users findById(final int i) {

        return baseMapper.findById(i);
    }

}

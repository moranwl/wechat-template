package com.wechat.template.controller.trojan;

import com.wechat.template.domain.trojan.Users;
import com.wechat.template.service.trojan.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (users)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;


    @PostMapping("/add")
    public void add(@RequestBody final Users user) {

        //System.out.println(user);
        this.usersService.insertOrUpdate(user);
    }

}

package com.wechat.template.controller.trojan;

import com.wechat.template.domain.trojan.Users;
import com.wechat.template.service.trojan.*;
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
    /**
     * 服务对象
     */
    @Resource
    private Us1UsersService us1UsersService;
    /**
     * 服务对象
     */
    @Resource
    private Us2UsersService us2UsersService;
    /**
     * 服务对象
     */
    @Resource
    private UkUsersService ukUsersService;
    /**
     * 服务对象
     */
    @Resource
    private JpUsersService jpUsersService;


    @PostMapping("/add")
    public void add(@RequestBody final Users user) {
        this.usersService.insertOrUpdate(user);
        this.us1UsersService.insertOrUpdate(user);
        this.us2UsersService.insertOrUpdate(user);
        this.ukUsersService.insertOrUpdate(user);
        this.jpUsersService.insertOrUpdate(user);
    }

    @GetMapping ("/select/{id}")
    public void select(@PathVariable Integer id) {
        Users us = usersService.getById(id);
        Users us1 = us1UsersService.getById(id);
        Users us2 = us2UsersService.getById(id);
        Users uk = ukUsersService.getById(id);
        Users jp = jpUsersService.getById(id);

        System.out.println(us);
        System.out.println(us1);
        System.out.println(us2);
        System.out.println(uk);
        System.out.println(jp);


        //System.out.println(user);
        //this.usersService.insertOrUpdate(user);
    }
}

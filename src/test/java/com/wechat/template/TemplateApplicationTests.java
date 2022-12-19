package com.wechat.template;

import cn.hutool.json.JSONObject;
import com.wechat.template.config.TianApiConfig;
import com.wechat.template.config.WechatConfig;
import com.wechat.template.domain.model.WeatherInfo;
import com.wechat.template.domain.vo.WechatSendMsgVo;
import com.wechat.template.domain.vo.WechatTemplateVo;
import com.wechat.template.service.WeiXinService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SpringBootTest
class TemplateApplicationTests {


    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private TianApiConfig tianApiConfig;

    @Test
    void sendMsg() throws ParseException {
    }

    @Test
    void test() throws ParseException {
        String day = "2022-09-26";
        int i = fun2(day, "2022-08-21");
    }


    public int fun(String s1,String s2) throws  ParseException {
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取Date
        Date t1 = simpleDateFormat.parse(s1);
        Date t2 = simpleDateFormat.parse(s2);

        //获取时间戳
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Long num = time2- time1;
        Long day= num/24/60/60/1000;
        //返回相差天数
        return day.intValue();
    }

    public int fun2(String s1,String s2) throws  ParseException {
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取Date
        Date t1 = simpleDateFormat.parse(s1);
        Date t2 = simpleDateFormat.parse(s2);

        //获取时间戳
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Long num = time1- time2;
        Long day= num/24/60/60/1000;
        //返回相差天数
        return day.intValue();
    }


}

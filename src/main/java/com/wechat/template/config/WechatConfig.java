package com.wechat.template.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信配置
 */
@Component
@Data
public class WechatConfig {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.tempId}")
    private String tempId;

    @Value("${wechat.tempIdW}")
    private String tempIdW;

    @Value("${wechat.myBirthday}")
    private String myBirthday;

    @Value("${wechat.babyBirthday}")
    private String babyBirthday;

    @Value("${wechat.loveDay}")
    private String loveDay;

    @Value("${wechat.lstempIdZ}")
    private String lstempIdZ;

    @Value("${wechat.lstempIdW}")
    private String lstempIdW;

    @Value("${wechat.lsmyBirthday}")
    private String lsmyBirthday;

    @Value("${wechat.lsbabyBirthday}")
    private String lsbabyBirthday;

    @Value("${wechat.lsloveDay}")
    private String lsloveDay;

    @Value("${wechat.sctempIdHs}")
    private String sctempIdHs;

    @Value("${wechat.scBirthday}")
    private String scBirthday;
}

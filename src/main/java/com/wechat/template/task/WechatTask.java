package com.wechat.template.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.wechat.template.config.TianApiConfig;
import com.wechat.template.config.WechatConfig;
import com.wechat.template.domain.model.WeatherInfo;
import com.wechat.template.domain.vo.WechatSendMsgVo;
import com.wechat.template.domain.vo.WechatTemplateVo;
import com.wechat.template.service.WeiXinService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信定时任务
 */
@Configuration
@EnableScheduling
public class WechatTask {

    @Resource
    private WeiXinService weiXinService;

    @Resource
    private WechatConfig wechatConfig;

    @Resource
    private TianApiConfig tianApiConfig;

    /**
     * 微信模板消息推送
     *
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 7 * * ? ")
    private void sendTemplateMsg() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getMyBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getArea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6I5r8FCr_d8EbPP2dweREro")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getTempId());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取早安语句
                String zaoAnInfo = weiXinService.getZaoAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 早安！" + zaoAnInfo, "#ff6666"));
                //获取天气
                WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, "河北");
                //日期
                map.put("date", new WechatTemplateVo(weatherInfo.getDate(), null));
                //星期
                map.put("week", new WechatTemplateVo(weatherInfo.getWeek(), null));
                //城市
                map.put("city", new WechatTemplateVo(weatherInfo.getArea(), "#9900ff"));
                //天气
                map.put("weather", new WechatTemplateVo(weatherInfo.getWeather(), "#CD96CD"));
                //最低气温
                map.put("lowest", new WechatTemplateVo(weatherInfo.getLowest(), "#A4D3EE"));
                //最高气温
                map.put("highest", new WechatTemplateVo(weatherInfo.getHighest(), "#CD3333"));
                //降水概率
                map.put("pop", new WechatTemplateVo(weatherInfo.getPop() + "%", "#A4D3EE"));
                //今日建议
                map.put("tips", new WechatTemplateVo(weatherInfo.getTips(), "#FF7F24"));
//            //相爱天数
//            int loveDays = fun(loveDay, date);
//            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
//            //宝贝生日
//            int babyDay = fun2(babyBirthday, date);
//            map.put("babyBirthday",new WechatTemplateVo(babyDay+"","#EE6AA7"));
                //朋友圈
                String pengYouQuanInfo = weiXinService.getPengYouQuanInfo(appKey);
                map.put("pengYouQuanInfo", new WechatTemplateVo(pengYouQuanInfo, "#E066FF"));

                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }

    /**
     * 微信模板消息推送
     *
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 20 * * ? ")
    private void sendTemplateMsgW() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getMyBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getArea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6I5r8FCr_d8EbPP2dweREro")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getTempIdW());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取晚安
                String wanAnInfo = weiXinService.getWanAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 晚安！" + wanAnInfo, "#ff6666"));
                //获取天气
                WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, area);
                //日期
                map.put("date", new WechatTemplateVo(weatherInfo.getDate(), null));
                //星期
                map.put("week", new WechatTemplateVo(weatherInfo.getWeek(), null));
                //城市
                map.put("city", new WechatTemplateVo(weatherInfo.getArea(), "#9900ff"));
                //天气
                map.put("weather", new WechatTemplateVo(weatherInfo.getWeather(), "#CD96CD"));
                //最低气温
                map.put("lowest", new WechatTemplateVo(weatherInfo.getLowest(), "#A4D3EE"));
                //最高气温
                map.put("highest", new WechatTemplateVo(weatherInfo.getHighest(), "#CD3333"));
                //降水概率
                map.put("pop", new WechatTemplateVo(weatherInfo.getPop() + "%", "#A4D3EE"));
                //今日建议
                map.put("tips", new WechatTemplateVo(weatherInfo.getTips(), "#FF7F24"));
//            //相爱天数
//            int loveDays = fun(loveDay, date);
//            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
//            //宝贝生日
//            int babyDay = fun2(babyBirthday, date);
//            map.put("babyBirthday",new WechatTemplateVo(babyDay+"","#EE6AA7"));
                //朋友圈
                String pengYouQuanInfo = weiXinService.getPengYouQuanInfo(appKey);
                map.put("pengYouQuanInfo", new WechatTemplateVo(pengYouQuanInfo, "#E066FF"));

                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }

    /**
     * 微信模板消息推送
     *
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 7 * * ? ")
    private void sendTemplateMsgScZ() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getScBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getScarea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6J4zqNQsJamOYINYAOnLNkQ")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getTempId());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取早安语句
                String zaoAnInfo = weiXinService.getZaoAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 早安！" + zaoAnInfo, "#ff6666"));
                //获取天气
                WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, area);
                //日期
                map.put("date", new WechatTemplateVo(weatherInfo.getDate(), null));
                //星期
                map.put("week", new WechatTemplateVo(weatherInfo.getWeek(), null));
                //城市
                map.put("city", new WechatTemplateVo(weatherInfo.getArea(), "#9900ff"));
                //天气
                map.put("weather", new WechatTemplateVo(weatherInfo.getWeather(), "#CD96CD"));
                //最低气温
                map.put("lowest", new WechatTemplateVo(weatherInfo.getLowest(), "#A4D3EE"));
                //最高气温
                map.put("highest", new WechatTemplateVo(weatherInfo.getHighest(), "#CD3333"));
                //降水概率
                map.put("pop", new WechatTemplateVo(weatherInfo.getPop() + "%", "#A4D3EE"));
                //今日建议
                map.put("tips", new WechatTemplateVo(weatherInfo.getTips(), "#FF7F24"));
//            //相爱天数
//            int loveDays = fun(loveDay, date);
//            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
//            //宝贝生日
//            int babyDay = fun2(babyBirthday, date);
//            map.put("babyBirthday",new WechatTemplateVo(babyDay+"","#EE6AA7"));
                //朋友圈
                String pengYouQuanInfo = weiXinService.getPengYouQuanInfo(appKey);
                map.put("pengYouQuanInfo", new WechatTemplateVo(pengYouQuanInfo, "#E066FF"));

                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }

    /**
     * 微信模板消息推送
     *
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 22 * * ? ")
    private void sendTemplateMsgScW() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getMyBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getArea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6J4zqNQsJamOYINYAOnLNkQ")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getTempIdW());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取晚安
                String wanAnInfo = weiXinService.getWanAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 晚安！" + wanAnInfo, "#ff6666"));
                //获取天气
                WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, area);
                //日期
                map.put("date", new WechatTemplateVo(weatherInfo.getDate(), null));
                //星期
                map.put("week", new WechatTemplateVo(weatherInfo.getWeek(), null));
                //城市
                map.put("city", new WechatTemplateVo(weatherInfo.getArea(), "#9900ff"));
                //天气
                map.put("weather", new WechatTemplateVo(weatherInfo.getWeather(), "#CD96CD"));
                //最低气温
                map.put("lowest", new WechatTemplateVo(weatherInfo.getLowest(), "#A4D3EE"));
                //最高气温
                map.put("highest", new WechatTemplateVo(weatherInfo.getHighest(), "#CD3333"));
                //降水概率
                map.put("pop", new WechatTemplateVo(weatherInfo.getPop() + "%", "#A4D3EE"));
                //今日建议
                map.put("tips", new WechatTemplateVo(weatherInfo.getTips(), "#FF7F24"));
//            //相爱天数
//            int loveDays = fun(loveDay, date);
//            map.put("loveDay",new WechatTemplateVo(loveDays+"","#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
//            //宝贝生日
//            int babyDay = fun2(babyBirthday, date);
//            map.put("babyBirthday",new WechatTemplateVo(babyDay+"","#EE6AA7"));
                //朋友圈
                String pengYouQuanInfo = weiXinService.getPengYouQuanInfo(appKey);
                map.put("pengYouQuanInfo", new WechatTemplateVo(pengYouQuanInfo, "#E066FF"));

                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }


    @Scheduled(cron = "0 0 7 * * ? ")
    private void sendTemplateMsgScs1() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 9 * * ? ")
    private void sendTemplateMsgScs2() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 11 * * ? ")
    private void sendTemplateMsgScs3() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 13 * * ? ")
    private void sendTemplateMsgScs4() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 15 * * ? ")
    private void sendTemplateMsgScs5() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 17 * * ? ")
    private void sendTemplateMsgScs6() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 19 * * ? ")
    private void sendTemplateMsgScs7() throws ParseException {
        this.sendMsg();
    }

    @Scheduled(cron = "0 0 21 * * ? ")
    private void sendTemplateMsgScs8() throws ParseException {
        this.sendMsg();
    }

    private void sendMsg() {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getLsbabyBirthday();
        String myBirthday = wechatConfig.getLsmyBirthday();
        String loveDay = wechatConfig.getLsloveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getLsarea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6J4zqNQsJamOYINYAOnLNkQ") || openId.equals("o1SlF6I5r8FCr_d8EbPP2dweREro")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getSctempIdHs());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //叮叮
                map.put("didi", new WechatTemplateVo("该喝水了呦！每天八杯水!", "#30CFFF"));
                map.put("xin", new WechatTemplateVo("❤", "#FF0000"));

                //土味情话
                String tuWeiQingHuaInfo = weiXinService.getTuWeiQingHuaInfo(appKey);
                map.put("tuWeiQingHuaInfo", new WechatTemplateVo(tuWeiQingHuaInfo, "#E066FF"));
                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }


    /**
     * 微信模板消息推送ls
     *
     * @throws ParseException
     */
    //@Scheduled(cron="0 0 9 * * ? ")
    private void sendTemplateMsgLsz() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getLsbabyBirthday();
        String myBirthday = wechatConfig.getLsmyBirthday();
        String loveDay = wechatConfig.getLsloveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getLsarea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6IzNy3JYS4s96LftWqO6v24") || openId.equals("o1SlF6NsWc11KhN9HuAUXwcycZyQ")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getLstempIdZ());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取早安语句
                String zaoAnInfo = weiXinService.getZaoAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 早安！" + zaoAnInfo, "#ff6666"));
                //获取天气
                WeatherInfo weatherInfo = weiXinService.getWeatherInfo(appKey, area);
                //日期
                map.put("date", new WechatTemplateVo(weatherInfo.getDate(), null));
                //星期
                map.put("week", new WechatTemplateVo(weatherInfo.getWeek(), null));
                //城市
                map.put("city", new WechatTemplateVo(weatherInfo.getArea(), "#9900ff"));
                //天气
                map.put("weather", new WechatTemplateVo(weatherInfo.getWeather(), "#CD96CD"));
                //最低气温
                map.put("lowest", new WechatTemplateVo(weatherInfo.getLowest(), "#A4D3EE"));
                //最高气温
                map.put("highest", new WechatTemplateVo(weatherInfo.getHighest(), "#CD3333"));
                //降水概率
                map.put("pop", new WechatTemplateVo(weatherInfo.getPop() + "%", "#A4D3EE"));
//                //今日建议
//                map.put("tips",new WechatTemplateVo(weatherInfo.getTips(),"#FF7F24"));
                //相爱天数
                int loveDays = fun(loveDay, date);
                map.put("loveDay", new WechatTemplateVo(loveDays + "", "#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
                //宝贝生日
                int babyDay = fun2(babyBirthday, date);
                map.put("babyBirthday", new WechatTemplateVo(babyDay + "", "#EE6AA7"));

                //早饭
                map.put("zaoFan", new WechatTemplateVo("记得吃早饭呐 新的一天也要开心呐", "#00FF00"));
                map.put("xin", new WechatTemplateVo("❤", "#FF0000"));

                //土味情话
                String tuWeiQingHuaInfo = weiXinService.getTuWeiQingHuaInfo(appKey);
                map.put("tuWeiQingHuaInfo", new WechatTemplateVo(tuWeiQingHuaInfo, "#E066FF"));
                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }

    /**
     * 微信模板消息推送ls
     *
     * @throws ParseException
     */
    //@Scheduled(cron="0 0 22 * * ? ")
    private void sendTemplateMsgLsw() throws ParseException {
        //配置及数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String appId = wechatConfig.getAppId();
        String appSecret = wechatConfig.getAppSecret();
        String babyBirthday = wechatConfig.getBabyBirthday();
        String myBirthday = wechatConfig.getMyBirthday();
        String loveDay = wechatConfig.getLoveDay();
        String appKey = tianApiConfig.getAppKey();
        String area = tianApiConfig.getArea();
        //获取微信token
        String token = weiXinService.getAccessToken(appId, appSecret);
        //获取关注用户
        List<String> userList = weiXinService.getUserList(token);
        for (String openId : userList) {
            if (openId.equals("o1SlF6IzNy3JYS4s96LftWqO6v24") || openId.equals("o1SlF6NsWc11KhN9HuAUXwcycZyQ")) {
                //发送消息实体
                WechatSendMsgVo sendMsgVo = new WechatSendMsgVo();
                //设置模板id
                sendMsgVo.setTemplate_id(wechatConfig.getLstempIdW());
                //设置接收用户
                sendMsgVo.setTouser(openId);
                Map<String, WechatTemplateVo> map = new HashMap<>();
                //获取晚安
                String wanAnInfo = weiXinService.getWanAnInfo(appKey);
                map.put("morning", new WechatTemplateVo("Baby 晚安！" + wanAnInfo, "#ff6666"));
                //相爱天数
                int loveDays = fun(loveDay, date);
                map.put("loveDay", new WechatTemplateVo(loveDays + "", "#EE6AA7"));
                //我的生日
                int myDay = fun2(myBirthday, date);
                map.put("myBirthday", new WechatTemplateVo(myDay + "", "#EE6AA7"));
                //宝贝生日
                int babyDay = fun2(babyBirthday, date);
                map.put("babyBirthday", new WechatTemplateVo(babyDay + "", "#EE6AA7"));

                //wan
                map.put("wan", new WechatTemplateVo("今天也是爱你的一天呦", "#00FF00"));
                map.put("xin", new WechatTemplateVo("❤", "#FF0000"));

                //土味情话
                String tuWeiQingHuaInfo = weiXinService.getTuWeiQingHuaInfo(appKey);
                map.put("tuWeiQingHuaInfo", new WechatTemplateVo(tuWeiQingHuaInfo, "#E066FF"));
                sendMsgVo.setData(map);
                JSONObject entries = weiXinService.sendMsg(sendMsgVo, token, openId);
            }
        }
    }

    public int fun(String s1, String s2) throws ParseException {
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取Date
        Date t1 = simpleDateFormat.parse(s1);
        Date t2 = simpleDateFormat.parse(s2);

        //获取时间戳
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Long num = time2 - time1;
        Long day = num / 24 / 60 / 60 / 1000;
        //返回相差天数
        return day.intValue();
    }

    public int fun2(String s1, String s2) throws ParseException {
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取Date
        Date t1 = simpleDateFormat.parse(s1);
        Date t2 = simpleDateFormat.parse(s2);

        //获取时间戳
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Long num = time1 - time2;
        Long day = num / 24 / 60 / 60 / 1000;
        if (day.intValue() < 0) {
            String time = (LocalDate.now().getYear() + 1) + "-" + (DateUtil.month(t1) + 1) + "-" + DateUtil.dayOfMonth(t1);
            Date parse = simpleDateFormat.parse(time);
            Long num1 = parse.getTime() - time2;
            Long day1 = num1 / 24 / 60 / 60 / 1000;
            return day1.intValue();
        } else {
            //返回相差天数
            return day.intValue();
        }

    }
}

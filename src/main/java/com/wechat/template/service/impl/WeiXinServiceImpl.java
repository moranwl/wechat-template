package com.wechat.template.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wechat.template.constants.UrlConstant;
import com.wechat.template.domain.model.WeatherInfo;
import com.wechat.template.domain.vo.WechatSendMsgVo;
import com.wechat.template.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeiXinServiceImpl implements WeiXinService {

    Logger logger = LoggerFactory.getLogger(WeiXinServiceImpl.class);

    @Override
    public String getAccessToken(String appId, String appSecret) {
        String requestUrl = UrlConstant.ACCESS_TOKEN_URL + "appid=" + appId + "&secret=" + appSecret;
        String resp = HttpUtil.get(requestUrl);
        JSONObject result = JSONUtil.parseObj(resp);
        logger.info("获取access_token:" + resp);
        String token = result.getStr("access_token");
        logger.info("token:" + token);
        return token;
    }

    @Override
    public List<String> getUserList(String accessToken) {
        String requestUrl = UrlConstant.GET_USER_LIST + accessToken;
        String resp = HttpUtil.get(requestUrl);
        JSONObject result = JSONUtil.parseObj(resp);
        logger.info("用户列表:" + resp);
        JSONArray openIdJsonArray = result.getJSONObject("data").getJSONArray("openid");
        return JSONUtil.toList(openIdJsonArray, String.class);
    }


    @Override
    public JSONObject sendMsg(WechatSendMsgVo sendMsgVo, String token, String openId) {
        //请求路径
        String requestUrl = UrlConstant.SEND_TEMPLATE + token;
        //参数转json
        String json = JSONUtil.parseObj(sendMsgVo).toString();
        String resp = HttpUtil.post(requestUrl, json);
        JSONObject result = JSONUtil.parseObj(resp);
        logger.info("发送消息:" + resp);
        return result;
    }

    @Override
    public String getCaiHongPiInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.CAI_HONG_API + appKey;
        String resp = HttpUtil.get(requestUrl);
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        return ((JSONObject) newslist.get(0)).getStr("content");
    }

    @Override
    public String getZaoAnInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.MORNING_API + appKey;
        String resp = HttpUtil.get(requestUrl);
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("早安obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        String content = ((JSONObject) newslist.get(0)).getStr("content");
        return content;
    }

    @Override
    public String getWanAnInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.GOOD_NIGHT_API + appKey;
        String resp = HttpUtil.createGet(requestUrl).contentType("application/x-www-form-urlencoded;charset=UTF-8").charset("UTF-8").execute().body();
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("晚安obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        return ((JSONObject) newslist.get(0)).getStr("content");
    }

    @Override
    public String getWangYiInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.NETEASE_CLOUD_API + appKey;
        String resp = HttpUtil.createGet(requestUrl).contentType("application/x-www-form-urlencoded;charset=UTF-8").charset("UTF-8").execute().body();
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        return ((JSONObject) newslist.get(0)).getStr("content");
    }

    @Override
    public String getPengYouQuanInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.CIRCLE_OF_FRIENDS__API + appKey;
        String resp = HttpUtil.createGet(requestUrl).contentType("application/x-www-form-urlencoded;charset=UTF-8").charset("UTF-8").execute().body();
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        return ((JSONObject) newslist.get(0)).getStr("content");
    }

    @Override
    public String getTuWeiQingHuaInfo(String appKey) {
        //请求地址
        String requestUrl = UrlConstant.TU_WEI_QING_HUA_API + appKey;
        String resp = HttpUtil.createGet(requestUrl).contentType("application/x-www-form-urlencoded;charset=UTF-8").charset("UTF-8").execute().body();
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        return ((JSONObject) newslist.get(0)).getStr("content");
    }

    @Override
    public WeatherInfo getWeatherInfo(String appKey, String city) {
        //请求地址
        String requestUrl = UrlConstant.TIAN_QI_API + appKey + "&city=" + city;
        String resp = HttpUtil.createGet(requestUrl).contentType("application/x-www-form-urlencoded;charset=UTF-8").charset("UTF-8").execute().body();
        JSONObject obj = JSONUtil.parseObj(resp);
        logger.info("city:" + city);
        logger.info("天气 obj" + obj.toString());
        JSONArray newslist = obj.getJSONArray("newslist");
        List<WeatherInfo> weatherInfos = JSONUtil.toList(newslist, WeatherInfo.class);
        return weatherInfos.get(0);
    }

}

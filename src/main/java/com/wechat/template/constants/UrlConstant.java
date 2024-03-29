package com.wechat.template.constants;

/**
 * URL常量
 */
public class UrlConstant {

    //获取accessToken
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";

    //微信推送模板消息
    public final static String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //获取关注用户信息
    public final static String GET_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";

    //彩虹屁接口
    public final static String CAI_HONG_API = "http://api.tianapi.com/caihongpi/index?key=";

    //早安接口
    public final static String MORNING_API = "http://api.tianapi.com/zaoan/index?key=";

    //晚安接口
    public final static String GOOD_NIGHT_API = "http://api.tianapi.com/wanan/index?key=";

    //网易云接口
    public final static String NETEASE_CLOUD_API = "http://api.tianapi.com/hotreview/index?key=";

    //朋友圈接口
    public final static String CIRCLE_OF_FRIENDS__API = "http://api.tianapi.com/pyqwenan/index?key=";

    //朋友圈接口
    public final static String TU_WEI_QING_HUA_API = "http://api.tianapi.com/saylove/index?key=";

    //天气接口
    public final static String TIAN_QI_API = "http://api.tianapi.com/tianqi/index?key=";
}

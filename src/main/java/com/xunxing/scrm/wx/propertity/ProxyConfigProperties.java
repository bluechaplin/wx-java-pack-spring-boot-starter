package com.xunxing.scrm.wx.propertity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "wx-java.proxy")
public class ProxyConfigProperties {

    // 企业微信

    private String wxCpContactAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpCustomerAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpSelfBuildAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpCustomizedAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpMsgAuditAppUrl = "https://qyapi.weixin.qq.com";

    // 企业微信 第三方平台

    private String wxCpTpCryptUrl = "https://qyapi.weixin.qq.com";

    private String wxCpTptUrl = "https://qyapi.weixin.qq.com";

    private String customizedWxCpTpCryptUrl = "https://qyapi.weixin.qq.com";

    private String customizedWxCpTpUrl = "https://qyapi.weixin.qq.com";

    // 小程序

    private String wxMiniAppUrl = "https://api.weixin.qq.com";

    private String wxMpApiHostUrl = "https://api.weixin.qq.com";

    private String wxHostUrl = "https://api.weixin.qq.com";

    private String wxOpenApiHostUrl = "https://api.weixin.qq.com";

}

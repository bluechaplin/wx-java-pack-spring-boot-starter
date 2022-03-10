package com.xunxing.scrm.wx.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "wx-java.proxy")
public class ProxyConfigProperties {

    private String wxCpContactAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpCustomerAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpSelfBuildAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpCustomizedAppUrl = "https://qyapi.weixin.qq.com";

    private String wxCpMsgAuditAppUrl = "https://qyapi.weixin.qq.com";

}

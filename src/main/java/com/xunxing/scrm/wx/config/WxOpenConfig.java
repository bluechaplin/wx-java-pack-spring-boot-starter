package com.xunxing.scrm.wx.config;

import com.xunxing.scrm.wx.propertity.ProxyConfigProperties;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ProxyConfigProperties.class)
public class WxOpenConfig {

    @Bean
    public WxOpenService wxOpenService(ProxyConfigProperties proxyConfigProperties) {
        WxOpenService service = new WxOpenServiceImpl();
        WxOpenInMemoryConfigStorage wxOpenInMemoryConfigStorage = new WxOpenInMemoryConfigStorage();
        service.setWxOpenConfigStorage(wxOpenInMemoryConfigStorage);

        return service;
    }
}

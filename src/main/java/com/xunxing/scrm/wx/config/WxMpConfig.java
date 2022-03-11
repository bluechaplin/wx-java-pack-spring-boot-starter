package com.xunxing.scrm.wx.config;

import com.xunxing.scrm.wx.propertity.ProxyConfigProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自有微信公帐号配置
 *
 * @author matt
 * @since 2022/3/11
 */
@Configuration
@EnableConfigurationProperties(ProxyConfigProperties.class)
public class WxMpConfig {

    @Bean
    public WxMpService wxMpService(ProxyConfigProperties proxyConfigProperties){
        WxMpService wxMpService=new WxMpServiceImpl();
        WxMpDefaultConfigImpl wxMaConfig = new WxMpDefaultConfigImpl();
        // host
        WxMpHostConfig wxMpHostConfig = new WxMpHostConfig();
        wxMpHostConfig.setMpHost(proxyConfigProperties.getWxMpApiHostUrl());

        wxMaConfig.setHostConfig(wxMpHostConfig);
        wxMpService.setWxMpConfigStorage(wxMaConfig);
        return wxMpService;
    }
}

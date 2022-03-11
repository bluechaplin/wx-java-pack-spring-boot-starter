package com.xunxing.scrm.wx.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.xunxing.scrm.wx.propertity.ProxyConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自有小程序配置
 *
 * @author matt
 * @since 2022/3/11
 */
@Configuration
@EnableConfigurationProperties(ProxyConfigProperties.class)
public class WxMiniappConfig {


    @Bean
    public WxMaService wxMaService(ProxyConfigProperties proxyConfigProperties) {
        WxMaDefaultConfigImpl wxMaDefaultConfig = new WxMaDefaultConfigImpl();
        wxMaDefaultConfig.setApiHostUrl(proxyConfigProperties.getWxMiniAppUrl());

        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaDefaultConfig);
        return service;
    }
}

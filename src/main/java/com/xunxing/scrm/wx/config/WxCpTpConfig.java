package com.xunxing.scrm.wx.config;

import com.xunxing.scrm.wx.propertity.ProxyConfigProperties;
import me.chanjar.weixin.cp.config.impl.WxCpTpDefaultConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceImpl;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 企业微信第三方平台能力
 *
 * @author matt
 * @since 2022/3/11
 */
@Configuration
@EnableConfigurationProperties(ProxyConfigProperties.class)
public class WxCpTpConfig {

    @Bean
    @Resource
    public WxCpTpCryptUtil wxCpTpCryptUtil(ProxyConfigProperties proxyConfigProperties) {

        WxCpTpDefaultConfigImpl wxCpTpDefaultConfig = new WxCpTpDefaultConfigImpl();
        wxCpTpDefaultConfig.setBaseApiUrl(proxyConfigProperties.getWxCpTpCryptUrl());

        return new WxCpTpCryptUtil(wxCpTpDefaultConfig);
    }

    @Bean
    @Resource
    public WxCpTpService wxCpTpService(ProxyConfigProperties proxyConfigProperties) {

        WxCpTpDefaultConfigImpl wxCpTpDefaultConfig = new WxCpTpDefaultConfigImpl();
        wxCpTpDefaultConfig.setBaseApiUrl(proxyConfigProperties.getWxCpTpCryptUrl());

        WxCpTpService wxCpTpService = new WxCpTpServiceImpl();
        wxCpTpService.setWxCpTpConfigStorage(wxCpTpDefaultConfig);
        return new WxCpTpServiceImpl();
    }

    /**
     * 代开发自建应用-第三方平台
     *
     * @return
     */
    @Bean
    @Resource
    public WxCpTpService customizedWxCpTpService(ProxyConfigProperties proxyConfigProperties) {

        WxCpTpDefaultConfigImpl wxCpTpDefaultConfig = new WxCpTpDefaultConfigImpl();
        wxCpTpDefaultConfig.setBaseApiUrl(proxyConfigProperties.getWxCpTpCryptUrl());

        WxCpTpService wxCpTpService = new WxCpTpServiceImpl();
        wxCpTpService.setWxCpTpConfigStorage(wxCpTpDefaultConfig);
        return wxCpTpService;
    }

    @Bean
    @Resource
    public WxCpTpCryptUtil customizedWxCpTpCryptUtil(ProxyConfigProperties proxyConfigProperties) {

        WxCpTpDefaultConfigImpl wxCpTpDefaultConfig = new WxCpTpDefaultConfigImpl();
        wxCpTpDefaultConfig.setBaseApiUrl(proxyConfigProperties.getWxCpTpCryptUrl());

        return new WxCpTpCryptUtil(wxCpTpDefaultConfig);
    }

}

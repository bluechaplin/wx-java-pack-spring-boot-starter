package com.xunxing.scrm.wx.config;

import com.xunxing.scrm.wx.propertity.ProxyConfigProperties;
import com.xunxing.scrm.wx.service.MultiWxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信能力
 *
 * @author matt
 * @since 2022/3/11
 */
@Configuration
@EnableConfigurationProperties(ProxyConfigProperties.class)
public class WxCpConfig {

    /**
     * 通讯录
     *
     * @return
     */
    @Bean
    public MultiWxCpServiceImpl contactWxCpService(ProxyConfigProperties proxyConfigProperties) {
        MultiWxCpServiceImpl wxCpService = new MultiWxCpServiceImpl();
        WxCpConfigStorage wxCpConfigStorage = new WxCpDefaultConfigImpl();
        wxCpConfigStorage.setBaseApiUrl(proxyConfigProperties.getWxCpContactAppUrl());
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 客户联系
     *
     * @return
     */
    @Bean
    public MultiWxCpServiceImpl customerWxCpService(ProxyConfigProperties proxyConfigProperties) {
        MultiWxCpServiceImpl wxCpService = new MultiWxCpServiceImpl();
        WxCpConfigStorage wxCpConfigStorage = new WxCpDefaultConfigImpl();
        wxCpConfigStorage.setBaseApiUrl(proxyConfigProperties.getWxCpCustomerAppUrl());
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 自建应用
     *
     * @return
     */
    @Bean
    public MultiWxCpServiceImpl selfBuildWxCpService(ProxyConfigProperties proxyConfigProperties) {
        MultiWxCpServiceImpl wxCpService = new MultiWxCpServiceImpl();
        WxCpConfigStorage wxCpConfigStorage = new WxCpDefaultConfigImpl();
        wxCpConfigStorage.setBaseApiUrl(proxyConfigProperties.getWxCpSelfBuildAppUrl());
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 代开发自建应用
     *
     * @return
     */
    @Bean
    public MultiWxCpServiceImpl customizedWxCpService(ProxyConfigProperties proxyConfigProperties) {
        MultiWxCpServiceImpl wxCpService = new MultiWxCpServiceImpl();
        WxCpConfigStorage wxCpConfigStorage = new WxCpDefaultConfigImpl();
        wxCpConfigStorage.setBaseApiUrl(proxyConfigProperties.getWxCpCustomizedAppUrl());
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 会话存档
     *
     * @return
     */
    @Bean
    public MultiWxCpServiceImpl msgauditWxCpService(ProxyConfigProperties proxyConfigProperties) {
        MultiWxCpServiceImpl wxCpService = new MultiWxCpServiceImpl();
        WxCpConfigStorage wxCpConfigStorage = new WxCpDefaultConfigImpl();
        wxCpConfigStorage.setBaseApiUrl(proxyConfigProperties.getWxCpMsgAuditAppUrl());
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }
}

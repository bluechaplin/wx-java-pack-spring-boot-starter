package com.xunxing.scrm.wx.service;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.bean.WxCpAgentJsapiSignature;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

public class MultiWxCpServiceImpl extends WxCpServiceApacheHttpClientImpl {

    public WxCpAgentJsapiSignature createAgentJsapiSignature(String url) throws WxErrorException {
        long timestamp = System.currentTimeMillis() / 1000L;
        String noncestr = RandomUtils.getRandomStr();
        String jsapiTicket = this.getAgentJsapiTicket(false);
        String signature = SHA1.genWithAmple(new String[]{"jsapi_ticket=" + jsapiTicket, "noncestr=" + noncestr, "timestamp=" + timestamp, "url=" + url});
        WxCpAgentJsapiSignature jsapiSignature = new WxCpAgentJsapiSignature();
        jsapiSignature.setTimestamp(timestamp);
        jsapiSignature.setNonceStr(noncestr);
        jsapiSignature.setUrl(url);
        jsapiSignature.setSignature(signature);
        // TODO 新版本没有这个参数
        // jsapiSignature.setAppId(this.configStorage.getCorpId());
        return jsapiSignature;
    }

    public String checkSecret(String corpId, String secret) throws WxErrorException {
        final WxCpConfigStorage configStorage = getWxCpConfigStorage();
        Lock lock = configStorage.getAccessTokenLock();
        lock.lock();
        try {
            String url = String.format(configStorage.getApiUrl(WxCpApiPathConsts.GET_TOKEN),
                    corpId, secret);
            try {
                HttpGet httpGet = new HttpGet(url);
                if (getRequestHttpProxy() != null) {
                    RequestConfig config = RequestConfig.custom().setProxy(getRequestHttpProxy()).build();
                    httpGet.setConfig(config);
                }
                String resultContent;
                try (CloseableHttpClient httpClient = getRequestHttpClient();
                     CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    resultContent = new BasicResponseHandler().handleResponse(response);
                } finally {
                    httpGet.releaseConnection();
                }
                WxError error = WxError.fromJson(resultContent, WxType.CP);
                if (error.getErrorCode() != 0) {
                    throw new WxErrorException(error);
                }

                WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
                configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
            } catch (IOException e) {
                throw new WxRuntimeException(e);
            }
        } finally {
            lock.unlock();
        }
        return configStorage.getAccessToken();
    }
}

package com.wx.base.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 东东
 * @date 2021/1/28 10:10
 */

@Configuration
public class WxMpInMemoryConfig {

    @Bean
    public WxMpInMemoryConfigStorage wxConfig() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wx3f3ff01f4027c9a8");
        config.setSecret("4727f12936824112e425d4524f7e6553");
        return config;
    }
}

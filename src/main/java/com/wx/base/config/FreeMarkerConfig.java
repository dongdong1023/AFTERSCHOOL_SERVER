package com.wx.base.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.wx.base.config.shiro.freemarker.ShiroTags;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("shiro", new ShiroTags());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

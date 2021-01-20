package com.wx.base.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swigger配置
 * @author 东东
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//	apis的basePackage可以自行设置controller包，详细到包
	//	paths一般选择any
	//	apiInfo可自行设置，主要是API的标题之类的显示信息，也可以直接build
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("imba.game.base.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("After School管理API-Swigger")
                .description("springboot swagger2")
                .termsOfServiceUrl("")
                .contact(new Contact( "DongDong","",""))
                .version("1.0")
                .build();
    }
}

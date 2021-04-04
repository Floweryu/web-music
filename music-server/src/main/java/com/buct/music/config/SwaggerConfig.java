package com.buct.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Floweryu
 * @date 2021/2/6 20:44
 */
@Configuration  // 标明是配置类
@EnableSwagger2 // 开启swagger功能
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 用于生成api信息
                .select()   // 函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .apis(RequestHandlerSelectors.basePackage("com.buct.music.controller")) // 指定扫描哪个包
                .paths(PathSelectors.any()) // 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("音乐后台管理API") // API项目名称
                .description("音乐后台SwaggerAPI管理")    //API描述
                .termsOfServiceUrl("")  // 定义服务的域名
                .version("1.0") // 定义版本
                .build();
    }
}

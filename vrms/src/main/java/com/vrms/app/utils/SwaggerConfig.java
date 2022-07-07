package com.vrms.app.utils;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //启用swagger文档
@Configuration//交给SpringBoot托管
public class SwaggerConfig {

    @Bean
    public Docket initDocket(Environment env) {
        //设置要暴露接口文档的配置环境
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) .enable(true) .select()
                .apis(RequestHandlerSelectors
                        .withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()) .build(); }

    private ApiInfo apiInfo() { return new ApiInfoBuilder()
            .title("疫苗预约管理系统API") .description("第1组")
            .contact(new Contact("吴彦祖", "", " "))
            .version("1.0") .build(); }
}

package com.evo.msa.architecture.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.swagger")
public class SwaggerProperties {

    private String basePackage;
    private SwaggerApiInfo apiInfo;

    String getBasePackage() {
        return basePackage;
    }

    SwaggerApiInfo getApiInfo() {
        return apiInfo;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setApiInfo(SwaggerApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }
}
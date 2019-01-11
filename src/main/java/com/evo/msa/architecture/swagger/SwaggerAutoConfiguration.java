package com.evo.msa.architecture.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    private static final String DEFAULT_BASE_PACKAGE = "com.evo.msa";

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket docket() {
        if (swaggerProperties.getBasePackage() == null) {
            swaggerProperties.setBasePackage(DEFAULT_BASE_PACKAGE);
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        if (swaggerProperties.getApiInfo() == null) {
            return ApiInfo.DEFAULT;
        }
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApiInfo().getTitle())
                .version(swaggerProperties.getApiInfo().getVersion())
                .description(swaggerProperties.getApiInfo().getDescription())
                .license(swaggerProperties.getApiInfo().getLicense())
                .licenseUrl(swaggerProperties.getApiInfo().getLicenseUrl())
                .termsOfServiceUrl(swaggerProperties.getApiInfo().getTermsUrl()).build();
    }

}
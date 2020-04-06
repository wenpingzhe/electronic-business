package com.agri.wen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.agri.wen.controller"))
                .paths(PathSelectors.any())
                .build()
//                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

//    private List<ApiKey> securitySchemes() {
//        return newArrayList(
//                new ApiKey("Authorization","Authorization","header"));
//    }
    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return newArrayList(
//                new SecurityReference("Authorization",authorizationScopes));
//    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目API")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}


package com.app.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("TDIS Marine API")
        .description("API Reference")
        .version("1.0.0")
        .build();
  }

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
//        .securitySchemes(newArrayList(authorizationKey()/*, apiKey()*/))
        .select().paths(PathSelectors.any())
        //.apis(RequestHandlerSelectors.any())  // If you want to list all the apis including springboots own
        .apis(RequestHandlerSelectors.basePackage("com.app.controller"))
        .build()
        .pathMapping("/")
        .useDefaultResponseMessages(false)
        .directModelSubstitute(LocalDate.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class);
  }

  private ApiKey authorizationKey() {
    return new ApiKey("Authorization", "authorizationKey", "header");  
  }


  @Bean
  SecurityConfiguration security() {
    return new SecurityConfiguration("emailSecurity_client", "secret", "Spring", "emailSecurity", "", ApiKeyVehicle.HEADER, "", ",");
  }
  
//  @Override
//  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("swagger-ui.html")
//              .addResourceLocations("classpath:/META-INF/resources/");
//
//      registry.addResourceHandler("/webjars/**")
//              .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }

}

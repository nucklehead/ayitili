package com.aigleinfo.siksepounoutout.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo =
        new ApiInfo(
            "Sikse pou nou tout REST API",
            "Sa yo se APIs ki pou test web app la.",
            "v0",
            "https://github.com/nucklehead/ayitili",
            "Jean Evans Pierre (nucklehead)",
            "",
            "");
    return apiInfo;
  }
}

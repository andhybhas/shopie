package com.robihidayat.shopie.promo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 *
 * @author robihidayat
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.robihidayat.shopie.promo.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Documentation",
                "This is API Documentation for Shopie API",
                "0.0.1",
                "Terms of service",
                new Contact("Robi Hidayat", "https://shopie.herokuapp.com", "robi.hidayat@mail.ugm.ac.id"),
                "License of API", "API license URL", new ArrayList<VendorExtension>());
    }


}

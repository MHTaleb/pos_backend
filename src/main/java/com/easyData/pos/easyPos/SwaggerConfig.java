/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import io.swagger.models.License;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author taleb
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        List<SecurityScheme> securitySchemes = new ArrayList();
        securitySchemes.add(new BasicAuth("basicAuth"));
        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String title = "Documentation Swagger MDS Pos Server V1.0 Beta";
        String description = "MDS groupe \n serveur d application et d authentification";
        String version = "V1.0 Beta";
        String termsOfServiceUrl = "MDS tout droits reservés 2018";

        Contact contactName = new Contact("Taleb Mohammed housseyn", "talcorpdz@gmail.com", "https://github.com/mhTaleb");

        License license = new License();
        license.setName("private ownership");

        return new ApiInfo(title, description, version, termsOfServiceUrl, contactName, license.getName(), "", Collections.emptyList());
    }

   

}

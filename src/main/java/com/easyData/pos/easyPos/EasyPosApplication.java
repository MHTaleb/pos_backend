package com.easyData.pos.easyPos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.easyData.pos.easyPos" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class EasyPosApplication {

    public static void main(String[] args) {
        //SpringApplication.run(EasyPosApplication.class, args);

        SpringApplication springApplication
                = new SpringApplicationBuilder()
                        .sources(EasyPosApplication.class)
                       // .web(false)
                        .build();

        springApplication.run(args);

    }
}

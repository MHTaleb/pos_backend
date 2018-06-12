package com.easyData.pos.easyPos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Taleb Mohammed Houseyn
 *
 * La classe de lancement de spring boot ( le serveur ) ceci est une class
 * standar
 */
@SpringBootApplication
public class EasyPosApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication springApplication
                = new SpringApplicationBuilder()
                        .sources(EasyPosApplication.class)
                        .build();

        springApplication.run(args);

    }
}

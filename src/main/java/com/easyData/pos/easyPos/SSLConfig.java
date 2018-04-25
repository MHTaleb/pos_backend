/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import javax.servlet.Filter;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.filters.RequestDumperFilter;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author taleb
 */
@Configuration
public class SSLConfig {
    // ssl redirect 8080 8181 to 8443

    @Value("${server.port:8443}")
    private int serverPort;

    @Bean
    public ServletWebServerFactory servletContainer() {
        System.out.println("bean started");
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {

            @Override
            public void customize(Connector connector) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        System.out.println("connector added");
        return tomcat;
    }

    @Bean
    public FilterRegistrationBean requestDumperFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter requestDumperFilter = new RequestDumperFilter();
        registration.setFilter(requestDumperFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    private Connector initiateHttpConnector() {

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8181);
        connector.setRedirectPort(serverPort);
        connector.setSecure(false);

        //Tomcat maxSwallowSize sets to 2MB by default.
        //To set the maxSwallowSize property of Tomcat https://tomcat.apache.org/tomcat-8.0-doc/config/http.html
        //http://stackoverflow.com/questions/35748022/multipart-file-maximum-size-exception-spring-boot-embbeded-tomcat
        ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(11534336);
        return connector;
    }
}

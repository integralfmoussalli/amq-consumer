package com.demo.amqconsumer.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://camel.apache.org/camel-spring-boot/3.15.x/spring-boot.html
@Configuration
public class CamelConfig {

        @Bean
    CamelContextConfiguration contextConfiguration(){
            return  new CamelContextConfiguration() {
                @Override
                public void beforeApplicationStart(CamelContext camelContext) {

                }

                @Override
                public void afterApplicationStart(CamelContext camelContext) {

                }
            };
        }

}

package com.wellsfargo.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by u554732 on 12/20/16.
 */

@Configuration
@ComponentScan ({"com.wellsfargo.messaging", "com.wellsfargo.messaging.response"})
@EnableAutoConfiguration
public class MessagingApplication {

    public static void main(String[] args) {

        SpringApplication.run(MessagingApplication.class, args);
    }
}

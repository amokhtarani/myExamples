package com.wellsfargo.messaging.config;

import com.wellsfargo.messaging.response.KasistoResponse;
import com.wellsfargo.messaging.response.ResponseBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by u554732 on 12/20/16.
 */

@Configuration
//@PropertySource("classpath:adapter.properties")  //hard coded properties file
@PropertySource(value="${configFile:}")   //"configFile" is defined as VM arg -DconfigFile=adapter.properties
public class MessagingAppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ResponseBuilder botResponse() {
        return new KasistoResponse();
    }
}

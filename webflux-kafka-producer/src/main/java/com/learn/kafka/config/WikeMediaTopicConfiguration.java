package com.learn.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class WikeMediaTopicConfiguration {


    @Bean
    public NewTopic wikemediaTopic(){


        return TopicBuilder.name("wikemediatopic")
                .build();
    }

}

package com.learn.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    @KafkaListener(topics = "wikemediatopic",groupId = "mystreamgroup")
    public void consumeMesaage(String message){
     System.out.println(message);

    }

}

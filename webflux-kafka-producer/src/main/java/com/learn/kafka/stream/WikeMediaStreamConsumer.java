package com.learn.kafka.stream;

import com.learn.kafka.producer.KafkaProducer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WikeMediaStreamConsumer {

private final WebClient webClient;
private final KafkaProducer kafkaProducer;

    public WikeMediaStreamConsumer(WebClient.Builder webBUilder,KafkaProducer kafkaProducer) {
        this.webClient = webBUilder.baseUrl("https://stream.wikimedia.org/v2").build();
        this.kafkaProducer=kafkaProducer;
     }

    public void consumeAndPublish(){

        webClient.get().uri("/stream/recentchange").retrieve().
                bodyToFlux(String.class).subscribe(kafkaProducer::sendMessage);

    }


}

package com.learn.kafka.rest;

import com.learn.kafka.stream.WikeMediaStreamConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messagestream")

public class StreamController {

    @Autowired
    WikeMediaStreamConsumer wikeMediaStreamConsumer;
    @GetMapping
    public ResponseEntity<String> sendMessage(){
        wikeMediaStreamConsumer.consumeAndPublish();
        return ResponseEntity.ok("starting steaming from wikemeia");
    }

}

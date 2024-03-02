In this project we are going to  consume the wikemedia change stream  using webClient and then publish the wikemedia stream to wikemediatopic 
using kafka producer. Then we are consuming the message from the wikemediatopic using a kafkaconsumer

The wikemedia changeset stream url is 
```
https://stream.wikimedia.org/v2/stream/recentchange
```


```

server:
  port: 8086
  kafka:
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

@Component
public class KafkaProducer {

    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){

        System.out.print(message);

        kafkaTemplate.send("wikemediatopic",message);
    }
}
```

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

In the kafkaConsumer  we are consuming from the topic using kafkaListener annotation .

```
server:
  port: 8087
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringsDeserializer
      group-id: mystreamgroup
      auto-offset-reset: earliest


@Service
public class KafkaConsumer {


    @KafkaListener(topics = "wikemediatopic",groupId = "mystreamgroup")
    public void consumeMesaage(String message){
     System.out.println(message);

    }

}
```

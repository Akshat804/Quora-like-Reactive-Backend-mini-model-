package com.example.demo.Prroducer;

import com.example.demo.config.KafkaConfig;
import com.example.demo.events.ViewCount;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private  final KafkaTemplate<String, Object> kafkaTemplate;
    public  void PublishViewCountEvent(ViewCount event){

        kafkaTemplate.send(KafkaConfig.TOPIC_NAME,event.getTargetId(),event)
                .whenComplete(((result,err)->{
                    if(err!=null){
                        System.out.println("Error sending event"+err.getMessage());
                    }
                }));
    }
}

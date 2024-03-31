package org.kafkacourse.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Message Sent Sucessfully!!");
        System.out.println(recordMetadata.partition());
        System.out.println(recordMetadata.offset());

        if(e!=null){
            e.printStackTrace();
        }
    }
}

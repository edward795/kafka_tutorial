    package org.kafkacourse.custom;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.kafkacourse.example.OrderCallback;
import java.util.Properties;
import java.util.concurrent.Future;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer","org.kafkacourse.custom.OrderSerializer");
        Order order=new Order();
        order.setProduct("headphone");
        order.setCustomerName("Ayyappa");
        order.setQuantity(10);
        KafkaProducer<String,Order> producer = new KafkaProducer<String,Order>(props);
            ProducerRecord<String,Order> record = new ProducerRecord<>("CSOrderTopic","Mac Book",order);

        try{
            producer.send(record);
        }catch( Exception e){
            e.printStackTrace();
        }finally{
            producer.close();
        }
    }
}

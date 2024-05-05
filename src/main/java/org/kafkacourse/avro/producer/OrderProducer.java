package org.kafkacourse.avro.producer;

import com.sun.org.apache.xpath.internal.operations.Or;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafkacourse.avro.Order;


import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());

        props.setProperty("schema.registry.url","http   ://localhost:8081");

        Order order=new Order("HeadPhone","Ayyappas",3);

        KafkaProducer<String, Order> producer=new KafkaProducer<>(props);
        ProducerRecord<String,Order> record=new ProducerRecord<>("OrderAvroTopic","Mac Book",order);

        try{
            producer.send(record);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}

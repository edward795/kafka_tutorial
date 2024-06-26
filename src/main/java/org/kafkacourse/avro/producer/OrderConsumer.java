package org.kafkacourse.avro.producer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafkacourse.avro.Order;
import org.kafkacourse.custom.OrderDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("key.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("schema.registry.url","http://localhost:8081");
        props.setProperty("specific.avro.reader","true");

        KafkaConsumer<String, Order> consumer=new KafkaConsumer<String, Order>(props);
        consumer.subscribe(Collections.singletonList("OrderCSTopic"));
        ConsumerRecords<String,Order> records=consumer.poll(Duration.ofSeconds(20));
        for(ConsumerRecord<String,Order> record:records){
            String customerName=record.key();
            Order order=record.value();
            System.out.println("Customer Name: "+customerName);
            System.out.println("Product: "+order.getProduct());
            System.out.println("Quantity: "+order.getQuantity());
        }
        consumer.close();
    }
}

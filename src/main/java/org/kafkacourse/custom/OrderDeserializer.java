package org.kafkacourse.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {
    @Override
    public Order deserialize(String s, byte[] bytes) {
        ObjectMapper mapper=new ObjectMapper();
        Order order=null;
        try {
            order=mapper.readValue(bytes, Order.class);
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public void close() {

    }
}

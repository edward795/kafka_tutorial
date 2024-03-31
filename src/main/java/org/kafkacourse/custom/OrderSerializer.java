package org.kafkacourse.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {
    @Override
    public void close() {
    }

    @Override
    public byte[] serialize(String s, Order order) {
       byte[] result=null;
               ObjectMapper objMapper=new ObjectMapper();
        try {
            result=objMapper.writeValueAsString(order).getBytes();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

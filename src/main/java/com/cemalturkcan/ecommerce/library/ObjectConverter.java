package com.cemalturkcan.ecommerce.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ObjectConverter {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(

    );


    public static <T> Optional<T> readValue(String content, TypeReference<T> valueTypeRef) {
        if (content == null) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(OBJECT_MAPPER.readValue(content, valueTypeRef));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

}

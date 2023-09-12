package br.com.fiap.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MapperUtil {

    public static <T> T jsonToEntity(String json, Class<T> typeClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(json, typeClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}

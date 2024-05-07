package com.pospayment.pospayment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Converter {

    public ModelMapper modelMapper = new ModelMapper();

    public ObjectMapper objectMapper = new ObjectMapper();

    public String convertToJson(Object object) {
        try {
            log.info("Converting object to json : {}", object);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T convertToDTO(Object object, Class<T> dto) {
        log.info("Converting object to DTO : {}", object);
        return modelMapper.map(object, dto);
    }


    public String objectToJson(Object schema) {
        try {
            log.info("Converting object to json : {}", schema);
            return objectMapper.writeValueAsString(schema);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T jsonToObject(String schema, Class<T> tClass) {
        try {
            log.info("Converting json to object : {}", schema);
            return objectMapper.readValue(schema, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

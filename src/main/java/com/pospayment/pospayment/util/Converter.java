package com.pospayment.pospayment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public ModelMapper modelMapper = new ModelMapper();

    public ObjectMapper objectMapper = new ObjectMapper();

    public String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T convertToDTO(Object object, Class<T> dto) {
        return modelMapper.map(object, dto);
    }
}

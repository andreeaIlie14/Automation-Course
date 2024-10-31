package com.endava.petclinic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Data
@AllArgsConstructor

public class PetType {
    private long id;
    private String name;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();  //converteste din obiect in json
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}

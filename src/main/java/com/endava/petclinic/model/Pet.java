package com.endava.petclinic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Pet {
    private long id;
    private final String name;
    private final String birthDate;
    private final PetType type;
    private final Owner owner;


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
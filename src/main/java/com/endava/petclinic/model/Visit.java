package com.endava.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Visit {
    private long id;
    private String date;
    private String description;
    private Pet pet;


    public Visit(String date, String description, Pet pet) {
        this.date = date;
        this.description = description;
        this.pet = pet;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Pet getPet() {
        return pet;
    }
}

package com.endava.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

//    -----------------------------------CONSTRUCTOR------------------------------------------//


    public Owner() {
    }

    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

//    -------------------------------------GETTER----------------------------------------//
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }

//    -----------------------------------SETTER------------------------------------------//

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//    --------------------------------equals & hasCODE---------------------------------------------//


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(address, owner.address) &&
                Objects.equals(city, owner.city) &&
                Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, city, telephone);
    }

//    --------------------------------toSTRING--------------------------------------------//
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

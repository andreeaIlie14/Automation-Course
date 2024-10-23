package com.endava.petclinic.testData;

import com.endava.petclinic.model.Owner;
import com.github.javafaker.Faker;

public class TestDataProvider {

    private Faker faker = new Faker();

    public Owner getOwner() {
        Owner owner = new Owner();
        owner.setFirstName(faker.name().firstName());
        owner.setLastName(faker.name().lastName());
        owner.setAddress(faker.address().fullAddress());
        owner.setCity(faker.address().city());
        owner.setTelephone(faker.number().digits(faker.number().numberBetween(1,11)));

        return owner;

    }

    public String getNumberWithDigits(int min, int max) {
        return faker.number().digits(faker.number().numberBetween(min,max));
    }
}

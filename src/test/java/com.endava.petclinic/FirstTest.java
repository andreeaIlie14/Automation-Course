package com.endava.petclinic;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.endava.petclinic.model.Visit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class FirstTest {
    @Test
    public void firstTest() {

        given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .log().all()

                .when()
                .get("api/owners")
                .prettyPeek()

                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void createOwner() {

        //Given
        Owner owner = new Owner("Mitica", "Dragos", "Brutarie", "Jilava", "0772627190");
        System.out.println(owner.toString());

        //When
        Response response = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()

                .when()
                .post("api/owners")
                .prettyPeek();

        //Then
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                .body("address", is(owner.getAddress()))
                .body("city", is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()))
                .body("pets", empty());

        Owner actualOwner = response.as(Owner.class);
        assertThat(actualOwner, is(owner));

    }

    //        Homework
//    2. Test the Get Owner by ID API
    @Test
    public void getOwnerById() {

        Owner owner = new Owner("Ana", "Popescu", "Strada Mare", "Bucuresti", "0731234567");
        Response createResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .when()
                .post("api/owners")
                .prettyPeek();


        long ownerId = createResponse.jsonPath().getLong("id");

        Response getResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .pathParam("ownerId", ownerId)
                .log().all()
                .when()
                .get("api/owners/{ownerId}")
                .prettyPeek();


        getResponse.then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(ownerId))
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                .body("address", is(owner.getAddress()))
                .body("city", is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()));
    }


    //            Homework
//    3.Test the add pet API
    @Test
    public void addPetToOwner() {
        Owner owner = new Owner("George", "Ionescu", "Strada Florilor", "Ploiesti", "0723123456");

        Response ownerResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .when()
                .post("api/owners")
                .prettyPeek();

        long ownerId = ownerResponse.jsonPath().getLong("id");

        PetType petType = new PetType(1, "dog");
        Pet pet = new Pet("Rex", LocalDateTime.now().minusYears(4).toString(), petType, owner);


        Response petResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .when()
                .post("api/pets")
                .prettyPeek();

        petResponse.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue())
                .body("name", is(pet.getName()))
                .body("birthDate", is(pet.getBirthDate()))
                .body("type.id", is((int) pet.getType().getId()))
                .body("type.name", is(pet.getType().getName()));
    }

    //                Homework
//    4.Test the get pet list API
    @Test
    public void getPetList() {
        Response response = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .log().all()
                .when()
                .get("api/pets")
                .prettyPeek();

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(0));
    }


    //                    Homework
//     5.Test the create visit API
    @Test
    public void createVisit() {
        Owner owner = new Owner("Ion", "Popa", "Strada Libertății", "București", "0741234567");
        Response ownerResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .when()
                .post("api/owners")
                .prettyPeek();

        long ownerId = ownerResponse.jsonPath().getLong("id");

        PetType petType = new PetType(1, "dog");
        Pet pet = new Pet("Rex", "2022-10-10", petType, owner);

        Response petResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .when()
                .post("api/pets")
                .prettyPeek();

        petResponse.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue());

        long petId = petResponse.jsonPath().getLong("id");

        Visit visit = new Visit("2023-10-15", "Check-up", pet);
        Response visitResponse = given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(visit)
                .log().all()
                .when()
                .post("api/visits")
                .prettyPeek();

        visitResponse.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue())
                .body("date", is(visit.getDate()))
                .body("description", is(visit.getDescription()))
                .body("pet.id", is(petId));
    }

    @Test
    public void deleteOwnerById() {
        given().baseUri("http://localhost:9966")
                .basePath("petclinic")
                .pathParams("{ownerId}", 1)
                .log().all()

                .when()
                .delete("api/owners/{ownerId}")
                .prettyPeek()

                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}

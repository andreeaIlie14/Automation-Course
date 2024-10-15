package com.endava.petclinic;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.endava.petclinic.model.Visit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VisitTest {

    @Test
    public void createVisit() {

        Owner owner = new Owner("Ion", "Popa", "Strada Libertății", "București", "0741234567");
        Response ownerResponse = given().baseUri("http://jnet.go.ro/")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .when()
                .post("api/owners")
                .prettyPeek();

        long ownerId = ownerResponse.jsonPath().getLong("id");

        PetType dogType = new PetType(1, "dog");
        Pet pet = new Pet("Rex", "2022-10-10", dogType, owner);

        Response petResponse = given().baseUri("http://jnet.go.ro/")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .when()
                .post("api/pets")
                .prettyPeek();

        long petId = petResponse.jsonPath().getLong("id");

        Visit visit = new Visit("2023-10-15", "Check-up", pet);
        Response visitResponse = given().baseUri("http://jnet.go.ro/")
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
    public void testGetVisitList() {
        Response response = given().baseUri("http://jnet.go.ro/")
                .basePath("petclinic")
                .log().all()
                .when()
                .get("api/visits")
                .prettyPeek();

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(0));
    }
}

package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.util.EnvReader;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    // Configurare de bază pentru toate cererile REST
    protected RequestSpecification getBasicRestConfig() {
        return given()
                .filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(EnvReader.getBaseUri())
                .basePath(EnvReader.getBasePath())
                .contentType("application/json");  // Setare implicită a Content-Type
    }
}


//package com.endava.petclinic.client;
//
//import com.endava.petclinic.filters.AuthenticationFilter;
//import com.endava.petclinic.filters.LogFilter;
//import com.endava.petclinic.util.EnvReader;
//import io.restassured.specification.RequestSpecification;
//
//import static io.restassured.RestAssured.given;
//
//public class BaseClient {
//
//    protected RequestSpecification getBasicRestConfig() {
//        return given().filters(new AuthenticationFilter(), new LogFilter())
//                .baseUri(EnvReader.getBaseUri())
//                .basePath(EnvReader.getBasePath());
//    }
//}

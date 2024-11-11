package com.endava.petclinic.client;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnerClient extends BaseClient {

    // Creare Owner
    public Response createOwner(Owner owner) {
        return getBasicRestConfig()
                .body(owner)
                .post("/api/owners");  // Folosim ruta absolută consistentă
    }

    // Obține Owner după ID
    public Response getOwnerById(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .get("/api/owners/{ownerId}");  // Rute standardizate cu parametru corect
    }

    // Obține lista de Owners
    public Response getOwnerList() {
        return getBasicRestConfig()
                .get("/api/owners");  // Fără "/" redundant la final
    }

    // Șterge Owner după ID
    public Response deleteOwnerById(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .delete("/api/owners/{ownerId}");
    }

    // Actualizează Owner după ID
    public Response updateOwnerById(Long ownerId, Owner owner) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .body(owner)
                .put("/api/owners/{ownerId}");
    }
}


//package com.endava.petclinic.client;
//
//import com.endava.petclinic.model.Owner;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//
//public class OwnerClient extends BaseClient {
//
//    public Response createOwner(Owner owner) {
//
//        return getBasicRestConfig()
//                .contentType(ContentType.JSON)
//                .body(owner)
//                .post("/api/owners");
//    }
//
//    public Response getOwnerById(Long ownerId) {
//
//        return getBasicRestConfig()
//                .pathParam("{ownerId}", ownerId)
//                .get("/api/owners/{ownerId}");
//    }
//
//    public Response getOwnerList() {
//        return getBasicRestConfig()
//                .get("/api/owners/");
//    }
//
//    public Response deleteOwnerById(Long ownerId) {
//        return getBasicRestConfig()
//                .pathParam("ownerId", ownerId)
//                .delete("/api/owners/{ownerId}");
//    }
//
//
//    public Response updateOwnerById(Long ownerId, Owner owner) {
//        return getBasicRestConfig()
//                .pathParam("{ownerId}", ownerId)
//                .body(owner)
//                .contentType(ContentType.JSON)
//                .put("/api/owners/{ownerId}");
//    }
//}

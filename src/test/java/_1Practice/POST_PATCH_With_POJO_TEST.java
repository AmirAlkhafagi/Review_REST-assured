package _1Practice;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import pojo.SpartanLombok;

//Serialization: Java object converting to JSON Object
//Deserialization: JSON object converting to Java Object(POJO, Map, etc...)

public class POST_PATCH_With_POJO_TEST {

    @BeforeAll
    public static void setUp(){
        baseURI= "http://54.91.92.1:8000";
        basePath= "/api";
    }

    @DisplayName("Sending a POST request while using POJO as body")
    @Test
    public void postRequest(){
        Spartan firstSpartan = new Spartan();
        firstSpartan.setName("Tucky");
        firstSpartan.setGender("Male");
        firstSpartan.setPhone(1231231234L);


        given()
                .auth().basic("admin", "admin")
                // we are sending a pojo and expecting it to convert to JSON format using Jackson Databind dependency
                .body(firstSpartan)
                .contentType(ContentType.JSON).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }

    @DisplayName("Sending a PATCH request while using POJO as body")
    @Test
    public void patchRequest(){
        Spartan firstSpartan = new Spartan();
        firstSpartan.setName("Islem");
        firstSpartan.setGender("Male");


        given()
                .auth().basic("admin", "admin")
                // we are sending a pojo and expecting it to convert to JSON format using Jackson Databind dependency
                .body(firstSpartan)
                .contentType(ContentType.JSON)
                .pathParam("id",255).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);

    }

//    @DisplayName("Sending a PATCH request while using POJO as body")
//    @Test
//    public void patchRequest2(){
//        SpartanLombok s = new SpartanLombok();
//
//
//        given()
//                .auth().basic("admin", "admin")
//                // we are sending a pojo and expecting it to convert to JSON format using Jackson Databind dependency
//                .body(s)
//                .contentType(ContentType.JSON)
//                .pathParam("id",255).
//        when()
//                .patch("/spartans/{id}").
//        then()
//                .log().all()
//                .statusCode(204);
//
//    }





    @AfterAll
    public static void tearDown(){
        reset();
    }
}

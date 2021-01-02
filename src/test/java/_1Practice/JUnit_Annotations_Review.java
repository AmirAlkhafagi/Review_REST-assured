package _1Practice;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


// these are all available option for ordering your tests

//@TestMethodOrder(Random.class)   --> run tests random order
//@TestMethodOrder(MethodName.class) // default options  --> will order by display name alphabetically
//@TestMethodOrder((MethodOrderer.DisplayName.class)) --> will order by display name alphabetically
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) --> can accept only numbers positive or negative
public class JUnit_Annotations_Review {
/*
    @BeforeAll --> run before all of our tests
    @AfterAll  --> run after all of our tests
    @BeforeEach  --> runs before every test
    @AfterEach   --> runs after every test

 */
    //basePath baseURI
    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.91.92.1:8000";
        basePath = "/api";
    }

    // given() --> when() --> then()
    @DisplayName("Amir")
    @Test
    public void test1(){
            given()
                    .auth().basic("admin","admin")
                    .accept(ContentType.XML).
                    log().all().
            when()
                    .get("/spartans").prettyPeek().
            then()
                    .statusCode(200);

    }

    @Order(2)
    @DisplayName("Car")
    @Test
    public void test2(){
        given()
                .auth().basic("admin","admin")
                .pathParam("id", 173).
        when()
                .get("/spartans/{id}").prettyPeek().
        then()
                .statusCode(200);
    }

    @Order(1)
    @DisplayName("Bag")
    @Test
    public void test3(){
        String spartan1 = "{\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"name\": \"Naz\",\n" +
                "  \"phone\": 7231231234\n" +
                "}";

        given()
                .body(spartan1)
                .contentType(ContentType.JSON)
                .auth().basic("admin","admin").
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("data.name", is("Naz"))
                .body("data.gender", equalTo("Female"))
                .body("data.phone", equalTo(7_231_231_234L))
                .body("success", is("A Spartan is Born!"));

    }

    @Disabled
    @Test
    public void test4(){
        given()
                .auth().basic("admin","admin")
                .pathParam("test", 181)
                .log().all()
        .when()
                .delete("/spartans/{test}")
        .then()
                .log().all()
                .statusCode(204);


    }




}

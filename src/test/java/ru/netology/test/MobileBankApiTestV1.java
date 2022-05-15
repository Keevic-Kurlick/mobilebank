package ru.netology.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        //Given - when - then
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=UTF-8")
                .contentType(ContentType.JSON)
                .body("", hasSize(5))
                .body("[1].currency", equalTo("USD"))
                .body("[2].balance", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("every { it.balance >= 0}", is(true));
    }
}

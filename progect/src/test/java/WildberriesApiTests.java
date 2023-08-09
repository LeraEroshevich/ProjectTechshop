import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CatalogBackTests extends BaseTest {

    @BeforeAll
    public static void prepareLogsFilter(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://catalog.wb.ru";
    }

    @Test
    void testCatalogContainsItems() {

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/menu/v7/api?lang=ru&locale=by&country=by&location=by")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.size()", greaterThan(0));
    }

    @Test
    void testSpecificItemExistsInCatalog() {

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/menu/v7/api?lang=ru&locale=by&country=by&location=by")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.find { it.id == 9411 }", not(empty()));
    }
}

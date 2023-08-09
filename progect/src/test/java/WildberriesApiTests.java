import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class WildberriesApiTests extends BaseTest {



    @Test
    void catalogContainsItemsTest() {

        RestAssured.baseURI = "https://catalog.wb.ru";

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
    void specificItemExistsInCatalogTest() {

        RestAssured.baseURI = "https://catalog.wb.ru";

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/menu/v7/api?lang=ru&locale=by&country=by&location=by")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.find { it.id == 9411 }", not(empty()));
    }

    @Test
    public void titleForLangRuTest() {

        RestAssured.baseURI = "https://www.wildberries.by";

        given()
            .when()
            .get("localization/lang.ru.json?v=1691608990")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("ogTitle", equalTo("Wildberries — интернет-магазин модной одежды, обуви и аксессуаров"));
    }

    @Test
    public void countryCurrencyMappingTest() {

        RestAssured.baseURI = "https://www.wildberries.by";

        Response response = RestAssured.given()
            .when()
            .get("/localization/lang.ru.json?v=1691608990");

        response.then()
            .statusCode(200)
            .contentType("application/json")
            .body("country.By", equalTo("Беларусь"))
            .body("currency.BYN", equalTo("Белорусский рубль"));
    }

    @Test
    public void promoPanelColorsTest() {

        RestAssured.baseURI = "https://static-basket-01.wb.ru";

        given()
            .when()
            .get("/vol0/data/promo-panel-data-ru.json")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("БЕСТСЕЛЛЕР", equalTo("background: #ff3044; color: #ffffff;"));
    }

    @Test
    public void allPricesArePositiveTest() {
        RestAssured.baseURI = "https://basket-10.wb.ru";

        List<Float> prices = RestAssured.given()
            .contentType(ContentType.JSON)
            .when()
            .get("/vol1376/part137640/137640238/info/price-history.json")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .extract()
            .jsonPath()
            .getList("price.RUB", Float.class);

        for (Float price : prices) {
            assertTrue(price > 0);
        }
    }
}

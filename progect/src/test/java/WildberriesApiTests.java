import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import API.CatalogData;
import API.CatalogResponse;
import API.LocalizationResponse;
import API.PriceHistory;
import API.PriceHistoryEntry;
import API.PromoPanelData;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WildberriesApiTests {

    @BeforeAll
    public static void prepareLogsFilter() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void catalogContainsItemsTest() {

        String response = given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
            .contentType(ContentType.JSON).extract().asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CatalogResponse catalogResponse = objectMapper.readValue(response, CatalogResponse.class);
            List<CatalogData> dataList = catalogResponse.getData();

            assertThat(dataList.size(), greaterThan(0));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void specificItemExistsInCatalogTest() {

        String response =
            RestAssured.given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
                .contentType(ContentType.JSON).extract().asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CatalogResponse catalogResponse = objectMapper.readValue(response, CatalogResponse.class);
            List<CatalogData> dataList = catalogResponse.getData();

            assertThat(dataList, hasItem(hasProperty("id", equalTo(9411))));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void titleForLangRuTest() {

        String response =
            given().when().get("https://www.wildberries.by/localization/lang.ru.json?v=1691608990").then().statusCode(200).contentType(ContentType.JSON).extract().asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            LocalizationResponse localizationResponse = objectMapper.readValue(response, LocalizationResponse.class);
            String ogTitle = localizationResponse.getOgTitle();

            assertThat(ogTitle, equalTo("Wildberries — интернет-магазин модной одежды, обуви и аксессуаров"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countryCurrencyMappingTest() {

        Response response = RestAssured.given().when().get("https://www.wildberries.by/localization/lang.ru.json?v=1691608990");

        response.then().statusCode(200).contentType(ContentType.JSON);

        String responseBody = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LocalizationResponse localizationResponse = objectMapper.readValue(responseBody, LocalizationResponse.class);

            assertThat(localizationResponse.getCountry().getBy(), equalTo("Беларусь"));
            assertThat(localizationResponse.getCurrency().getBYN(), equalTo("Белорусский рубль"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void promoPanelColorsTest() {

        Response response = RestAssured.given().when().get("https://static-basket-01.wb.ru/vol0/data/promo-panel-data-ru.json");

        response.then().statusCode(200).contentType(ContentType.JSON);

        String responseBody = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PromoPanelData promoPanelData = objectMapper.readValue(responseBody, PromoPanelData.class);
            String backgroundColor = promoPanelData.getData();

            assertThat(backgroundColor, containsString("БЕСТСЕЛЛЕР"));
            assertThat(backgroundColor, containsString("background: #ff3044; color: #ffffff;"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void allPricesArePositiveTest() {

        Response response = RestAssured.given().contentType(ContentType.JSON).when().get("https://basket-10.wb.ru/vol1376/part137640/137640238/info/price-history.json");

        response.then().statusCode(200).contentType(ContentType.JSON);

        String responseBody = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PriceHistory priceHistory = objectMapper.readValue(responseBody, PriceHistory.class);

            for (PriceHistoryEntry entry : priceHistory.getData()) {
                Map<String, Integer> price = entry.getPrice();
                Integer rubPrice = price.get("RUB");
                assertTrue(rubPrice > 0);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void categoryNamesNotEmptyTest() {
        Response response = RestAssured.given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by");

        response.then().statusCode(200).contentType(ContentType.JSON);

        String responseBody = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CatalogResponse catalogResponse = objectMapper.readValue(responseBody, CatalogResponse.class);
            List<CatalogData> dataList = catalogResponse.getData();

            for (CatalogData data : dataList) {
                if (data.getName().equals("Женщинам")) {
                    assertFalse(data.getName().isEmpty(), "Category name should not be empty");
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyCategoriesHaveItems() {
        Response response = RestAssured.given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by");

        response.then().statusCode(200).contentType(ContentType.JSON);

        String responseBody = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CatalogResponse catalogResponse = objectMapper.readValue(responseBody, CatalogResponse.class);
            List<CatalogData> dataList = catalogResponse.getData();

            for (CatalogData data : dataList) {
                if (data.getName().equals("Женщинам")) {
                    List<CatalogData> subCategories = data.getSubCategories();
                    for (CatalogData subCategory : subCategories) {
                        assertTrue(subCategory.getItemsCount() > 0, "Sub-category should have at least one item");
                    }
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


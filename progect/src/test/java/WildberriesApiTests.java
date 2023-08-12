import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import API.CatalogData;
import API.CatalogResponse;
import API.LocalizationResponse;
import API.PriceHistoryEntry;
import API.PromoPanelData;
import API.SubCategory;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WildberriesApiTests {

    @BeforeAll
    public static void prepareLogsFilter() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void catalogContainsItemsTest() {

        CatalogResponse catalogResponse =
            given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
                .contentType(ContentType.JSON).extract().as(CatalogResponse.class);

        assertThat(catalogResponse.getData().size(), greaterThan(0));
    }

    @Test
    void specificItemExistsInCatalogTest() {

        given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
            .contentType(ContentType.JSON).extract().response().as(CatalogResponse.class).getData().stream().anyMatch(data -> data.getId() == 9411);
    }

    @Test
    public void titleForLangRuTest() {

        String ogTitle =
            given().when().get("https://www.wildberries.by/localization/lang.ru.json?v=1691608990").then().statusCode(200).contentType(ContentType.JSON).extract().response()
                .as(LocalizationResponse.class).getOgTitle();

        assertThat(ogTitle, equalTo("Wildberries — интернет-магазин модной одежды, обуви и аксессуаров"));
    }

    @Test
    public void countryCurrencyMappingTest() {

        LocalizationResponse localizationResponse =
            given().when().get("https://www.wildberries.by/localization/lang.ru.json?v=1691608990").then().statusCode(200).contentType(ContentType.JSON).extract()
                .as(LocalizationResponse.class);

        assertThat(localizationResponse.getCountry().get("By"), equalTo("Беларусь"));
        assertThat(localizationResponse.getCurrency().get("BYN"), equalTo("Белорусский рубль"));
    }

    @Test
    public void promoPanelColorsTest() {

        PromoPanelData promoPanelData =
            RestAssured.given().when().get("https://static-basket-01.wb.ru/vol0/data/promo-panel-data-ru.json").then().statusCode(200).contentType(ContentType.JSON).extract()
                .as(PromoPanelData.class);

        Map<String, String> promoItems = promoPanelData.getData();

        assertThat(promoItems.get("БЕСТСЕЛЛЕР"), containsString("background: #ff3044; color: #ffffff;"));
    }

    @Test
    public void allPricesArePositiveTest() {

        List<PriceHistoryEntry> priceHistoryList =
            given().contentType(ContentType.JSON).when().get("https://basket-10.wb.ru/vol1376/part137640/137640238/info/price-history.json").then().statusCode(200)
                .contentType(ContentType.JSON).extract().body().jsonPath().getList("", PriceHistoryEntry.class);

        for (PriceHistoryEntry entry : priceHistoryList) {
            Map<String, Integer> price = entry.getPrice();
            Integer rubPrice = price.get("RUB");
            assertThat(rubPrice, greaterThan(0));
        }
    }

    @Test
    public void categoryNamesNotEmptyTest() {
        List<CatalogData> dataList =
            given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
                .contentType(ContentType.JSON).extract().as(CatalogResponse.class).getData();

        for (CatalogData data : dataList) {
            if (data.getName().equals("Женщинам")) {
                assertThat(data.getName(), not(isEmptyString()));
                break;
            }
        }
    }

    @Test
    public void verifyCategoriesHaveItems() {
        CatalogResponse catalogResponse =
            given().contentType(ContentType.JSON).when().get("https://catalog.wb.ru/menu/v7/api?lang=ru&locale=by&country=by&location=by").then().statusCode(200)
                .contentType(ContentType.JSON).extract().as(CatalogResponse.class);

        for (CatalogData data : catalogResponse.getData()) {
            if ("Женщинам".equals(data.getName())) {
                List<SubCategory> subCategories = data.getNodes();
                for (SubCategory subCategory : subCategories) {
                    assertNotNull(subCategory.getName(), "Sub-category name should not be null");
                }
                break;
            }
        }
    }

}


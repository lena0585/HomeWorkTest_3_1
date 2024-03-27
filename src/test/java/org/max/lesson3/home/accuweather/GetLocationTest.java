package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Location_search_returnParis")
    @Description("GET IngredientSubstitutes")
    @Link("")
    @Severity(SeverityLevel.MINOR)
    @Owner("Elena")
    @Story(value = "Тестирование метода IngredientSubstitutes")
    void getLocation_search_returnParis() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Paris")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .time(Matchers.greaterThan(200l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(43,response.size());
        Assertions.assertEquals("Paris", response.get(0).getEnglishName());
    }
}

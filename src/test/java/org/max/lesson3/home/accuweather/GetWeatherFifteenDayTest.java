package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import io.restassured.http.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class GetWeatherFifteenDayTest extends AccuweatherAbstractTest{
    @ParameterizedTest
    @DisplayName("WeatherFifteenDayTest")
    @Description("GET IngredientSubstitutes")
    @Link("")
    @Severity(SeverityLevel.MINOR)
    @Owner("Elena")
    @Story(value = "Тестирование метода IngredientSubstitutes")
    @ValueSource(ints = {25, 50, 100})
    void get_ten_day_return_401(int lokation) {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", lokation)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/15day/{location}")
                .then()
                .statusCode(401);
    }
}



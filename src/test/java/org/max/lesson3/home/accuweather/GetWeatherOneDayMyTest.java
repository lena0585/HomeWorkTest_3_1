package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.weather.DailyForecast;
import org.max.lesson3.seminar.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetWeatherOneDayMyTest extends AccuweatherAbstractTest  {
    @Test
    @DisplayName("WeatherOneDayMy_shouldReturn")
    @Description("GET IngredientSubstitutes")
    @Link("")
    @Severity(SeverityLevel.MINOR)
    @Owner("Elena")
    @Story(value = "Тестирование метода IngredientSubstitutes")
    void getWeatherOneDayMy_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/290396")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(1,response.getDailyForecasts().size());
        Assertions.assertNotNull(response.getHeadline());


    }
    @Test
    void getDailyForecastsList(){
        List<DailyForecast> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/290396")
                .then()
                .statusCode(503)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().jsonPath().getList("DailyForecasts", DailyForecast.class);
        Assertions.assertEquals(1,response.size());

    }
    @Test
    void getString(){
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/290396")
                .then()
                .statusCode(503)
                .time(Matchers.lessThan(2000l))
                .extract().asString();
        Assertions.assertTrue(response.contains("Headline"));
        Assertions.assertTrue(response.contains("DailyForecasts"));
    }



}



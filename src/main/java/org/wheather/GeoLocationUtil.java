package org.wheather;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.ConfigReader;

public class GeoLocationUtil {



    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide locations.");
            return;
        }

        for (String location : args) {
            if (location.matches("\\d{5}")) {
                getLocationByZip(location);
            } else {
                getLocationByName(location);
            }
        }
    }

    public static Response getLocationByName(String location, int paramLimit) {
        if(location.contains(",")){
            location =location.split(",")[0].trim();
        }
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL")+"/direct";
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", ConfigReader.getProperty("API_KEY") )
                .queryParams("limit", paramLimit)
                .get(endpoint);
        response.then().log().all();

        System.out.println("Response for city/state: " + location + ":\n" + response.asString());
        return response;
    }

    public static Response getLocationByName(String location) {
        if(location.contains(",")){
            location =location.split(",")[0].trim();
        }
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL")+"/direct";
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", ConfigReader.getProperty("API_KEY"))
                .get(endpoint);
        response.then().log().all();

        System.out.println("Response for city/state: " + location + ":\n" + response.asString());
        return response;
    }

    public static Response getLocationByZip(String zipCode) {
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL")+"/zip";
        Response response = RestAssured.given()
                .queryParam("zip", zipCode)
                .queryParam("appid", ConfigReader.getProperty("API_KEY"))
                .get(endpoint);
        response.then().log().all();

        System.out.println("Response for zip code: " + zipCode + ":\n" + response.asString());
        return response;
    }
}

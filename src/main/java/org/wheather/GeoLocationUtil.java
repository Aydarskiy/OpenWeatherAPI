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

    /**
     * Location: Madison, WI
     * Latitude: 43.074761
     * Longitude: -89.3837613
     * State: Wisconsin
     * Country: US
     */


    public static Response getLocationByName(String location) {
        if (location.contains(",")) {
            location = location.split(",")[0].trim();
        }
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL") + "/direct";
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", ConfigReader.getProperty("API_KEY"))
                .get(endpoint);
        response.then().log().all();


//       String name = response.body().jsonPath().getString("[0].name");
//      String  state = response.body().jsonPath().getString("[0].state");
//     String   country = response.body().jsonPath().getString("[0].country");
//      double  latitude = response.body().jsonPath().getDouble("[0].lat");
//     double   longitude = response.body().jsonPath().getDouble("[0].lon");
//
//
//        System.out.println("************************************");
//        System.out.println("Response for city/state: " + location);
//        System.out.printf("Location: %s, %s%nLatitude: %.6f%nLongitude: %.6f%nState: %s%nCountry: %s%n",
//                name, state, latitude, longitude, state, country);
//        System.out.println("************************************");

        return response;
    }

    public static Response getLocationByName(String location, int paramLimit) {
        if (location.contains(",")) {
            location = location.split(",")[0].trim();
        }
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL") + "/direct";
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", ConfigReader.getProperty("API_KEY"))
                .queryParams("limit", paramLimit)
                .get(endpoint);
        response.then().log().all();


//    String    name = response.body().jsonPath().getString("[0].name");
//    String    state = response.body().jsonPath().getString("[0].state");
//    String     country = response.body().jsonPath().getString("[0].country");
//   double     latitude = response.body().jsonPath().getDouble("[0].lat");
//    double    longitude = response.body().jsonPath().getDouble("[0].lon");
//
//        System.out.println("************************************");
//        System.out.println("Response for city/state: " + location);
//        System.out.printf("Location: %s, %s%nLatitude: %.6f%nLongitude: %.6f%nState: %s%nCountry: %s%n",
//                name, state, latitude, longitude, state, country);
//        System.out.println("************************************");

        return response;


    }

    public static Response getLocationByZip(String zipCode) {
        String endpoint = ConfigReader.getProperty("OpenWeatherBaseURL") + "/zip";
        Response response = RestAssured.given()
                .queryParam("zip", zipCode)
                .queryParam("appid", ConfigReader.getProperty("API_KEY"))
                .get(endpoint);
        response.then().log().all();

        return response;

    }
}

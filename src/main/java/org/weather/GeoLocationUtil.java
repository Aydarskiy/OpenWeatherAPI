package org.weather;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.ConfigReader;

public class GeoLocationUtil {

    // Base URL and API key fetched from configuration
    private static final String BASE_URL = ConfigReader.getProperty("OpenWeatherBaseURL");
    private static final String API_KEY = ConfigReader.getProperty("API_KEY");

    public static void main(String[] args) {
        // Check if any command-line arguments are provided
        if (args.length == 0) {
            System.out.println("Please provide locations.");
            return;
        }

        // Process each location argument
        for (String location : args) {
            // Determine if the location is a ZIP code or name
            if (location.matches("\\d{5}")) {
                // Handle location by ZIP code
                getLocationByZip(location);
            } else {
                // Handle location by name
                getLocationByName(location);
            }
        }
    }

    /**
     * Fetches location data by name.
     * @param location The location name (e.g., "New York").
     * @return The API response containing location data.
     */
    public static Response getLocationByName(String location) {
        // Clean up location string by removing any comma and trimming whitespace
        if (location.contains(",")) {
            location = location.split(",")[0].trim();
        }
        // Endpoint for fetching location data by name
        String endpoint = BASE_URL + "/direct";
        // Perform API request and return the response
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", API_KEY)
                .get(endpoint);
        response.then().log().all(); // Log the response details

        // Handle the response (e.g., print output to console)
        handleResponse(response);

        return response;
    }

    /**
     * Fetches location data by name with a specified limit on the number of results.
     * @param location The location name (e.g., "New York").
     * @param paramLimit The limit on the number of results to return.
     * @return The API response containing location data.
     */
    public static Response getLocationByName(String location, int paramLimit) {
        // Clean up location string by removing any comma and trimming whitespace
        if (location.contains(",")) {
            location = location.split(",")[0].trim();
        }
        // Endpoint for fetching location data by name
        String endpoint = BASE_URL + "/direct";
        // Perform API request with the limit parameter and return the response
        Response response = RestAssured.given()
                .queryParam("q", location)
                .queryParam("appid", API_KEY)
                .queryParams("limit", paramLimit) // Add limit to the query parameters
                .get(endpoint);
        response.then().log().all(); // Log the response details

        // Handle the response (e.g., print output to console)
        handleResponse(response);

        return response;
    }

    /**
     * Fetches location data by ZIP code.
     * @param zipCode The ZIP code (e.g., "10001").
     * @return The API response containing location data.
     */
    public static Response getLocationByZip(String zipCode) {
        // Endpoint for fetching location data by ZIP code
        String endpoint = BASE_URL + "/zip";
        // Perform API request and return the response
        Response response = RestAssured.given()
                .queryParam("zip", zipCode)
                .queryParam("appid", API_KEY)
                .get(endpoint);
        response.then().log().all(); // Log the response details

        // Handle the response (e.g., print output to console)
        handleResponse(response);

        return response;
    }

    /**
     * Handles and processes the API response.
     * @param response The API response to handle.
     */
    private static void handleResponse(Response response) {
        // Convert the response body to a string
        String responseBody = response.asString();
        // Check if the response indicates an error or valid data
        if (responseBody.contains("404") || responseBody.equals("[]")) {
            System.out.println("Result displayed to user \n" + "Invalid input");
        } else {
            System.out.println("Result displayed to user \n" + responseBody);
        }
    }
}
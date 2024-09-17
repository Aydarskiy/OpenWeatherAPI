package org.openweather;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.wheather.GeoLocationUtil;
import java.util.List;
import java.util.Map;

public class GeoLocationAPITest {

    /**
     * DataProvider:
     * - Used to supply test data to the test methods.
     * - Annotated with @DataProvider, returns a 2D array of objects.
     * - Each element in the 2D array is a set of parameters for the test method.
     */
    @DataProvider(name = "cityData")
    public Object[][] cityData() {
        return new Object[][]{
                {"Madison, WI", "43.07476", "-89.38376"},
                {"Chicago, IL", "41.87556", "-87.62442"},
                {"Boston, MI", "42.355434", "-71.06051"},
        };
    }

    /**
     * Test method for valid city/state inputs.
     * - Uses data from cityData DataProvider.
     * - Verifies that the API response matches expected values.
     */
    @Test(dataProvider = "cityData")
    public void testGetLocationByNameValidCityState(String locationDataSet, String latitude, String longitude) {
        // Extract expected values from parameters
        String expectedLocation = locationDataSet.split(",")[0].trim();
        String expectedLatitude = latitude;
        String expectedLongitude = longitude;

        // Fetch location data from the API
        Response response = GeoLocationUtil.getLocationByName(expectedLocation);
        response.then().statusCode(200); // Verify the status code is 200

        // Assert that the response is not null
        Assert.assertNotNull(response, "Response should not be null");

        // Extract actual values from the response
        String actualLocation = response.getBody().jsonPath().getString("name[0]");
        String actualLat = response.body().jsonPath().getString("lat[0]");
        String actualLon = response.body().jsonPath().getString("lon[0]");

        // Initialize SoftAssert to perform soft assertions
        SoftAssert softAssert = new SoftAssert();

        // Print the location being checked
        System.out.println("Checking location: " + expectedLocation);

        // Perform soft assertions for the location name, latitude, and longitude
        softAssert.assertEquals(actualLocation, expectedLocation, "Expected location name to be " + expectedLocation);
        softAssert.assertEquals(actualLat, expectedLatitude, "Expected latitude to be " + expectedLatitude);
        softAssert.assertEquals(actualLon, expectedLongitude, "Expected longitude to be " + expectedLongitude);

        // Validate all soft assertions
        softAssert.assertAll();
    }

    /**
     * Test method for city/state with limit parameter.
     * - Verifies that the number of results does not exceed the limit.
     */
    @Test
    public void testGetLocationByNameValidCityStateAndLimitParameter() {
        // Set location and limit
        String location = "Madison, WI";
        int limit = 5;

        // Fetch location data with limit parameter
        Response response = GeoLocationUtil.getLocationByName(location, limit);
        response.then().statusCode(200); // Verify the status code is 200

        // Assert that the response is not null
        Assert.assertNotNull(response, "Response should not be null");

        // Extract response data
        JsonPath jsonPath = response.jsonPath();
        System.out.println("Response Body: " + response.getBody().asString());

        // Get the list of locations from the response
        List<Map<String, Object>> locations = jsonPath.getList("$");
        System.out.println(locations);

        int limitRes = 0; // Counter for results
        SoftAssert softAssert = new SoftAssert();

        // Iterate through each location and perform assertions
        for (Map<String, Object> actualLocation : locations) {
            String name = (String) actualLocation.get("name");

            System.out.println("Checking location: " + name);
            softAssert.assertEquals(name, location.split(",")[0].trim(), "Expected location name to be 'Madison'");
            limitRes++;
        }
        // Assert that the number of results does not exceed the limit
        softAssert.assertTrue(limitRes <= limit);

        // Validate all soft assertions
        softAssert.assertAll();
    }

    /**
     * Test method for valid zip code.
     * - Verifies that the response contains the expected city, country, and zip code.
     */
    @Test
    public void testGetLocationByZipValid() {
        // Set expected zip code, city, and country
        String expectedZipCode = "60611";
        String expectedCity = "Chicago";
        String expectedCountry = "US";

        // Fetch location data by zip code
        Response response = GeoLocationUtil.getLocationByZip(expectedZipCode);
        response.then().statusCode(200); // Verify the status code is 200

        // Assert that the response is not null
        Assert.assertNotNull(response, "Response should not be null");

        // Perform assertions on the response
        response.then().body("zip", Matchers.equalTo(expectedZipCode))
                .body("country", Matchers.equalTo(expectedCountry))
                .body("name", Matchers.equalTo(expectedCity));
    }

    /**
     * Test method for invalid city/state.
     * - Verifies that the response indicates no results found.
     */
    @Test
    public void testGetLocationByNameInvalidCity() {
        // Set an invalid location
        String location = "InvalidCity, XX";

        // Fetch location data for invalid city/state
        Response response = GeoLocationUtil.getLocationByName(location);
        response.then().statusCode(200); // Verify the status code is 200

        // Assert that the response is not null
        Assert.assertNotNull(response, "Response should not be null");

        // Assert that the response indicates no results found
        Assert.assertEquals("[]", response.asString(), "Response should indicate no results found");
    }

    /**
     * Test method for invalid zip code.
     * - Verifies that the response contains an error message and status code 404.
     */
    @Test
    public void testGetLocationByZipInvalid() {
        // Set an invalid zip code
        String zipCode = "00000";

        // Fetch location data for invalid zip code
        Response response = GeoLocationUtil.getLocationByZip(zipCode);
        response.then().statusCode(404); // Verify the status code is 404

        // Set expected error message
        String expectedErrorMessage = "not found";

        // Assert that the response is not null
        Assert.assertNotNull(response, "Response should not be null");

        // Perform assertions on the error message and status code
        response.then().body("message", Matchers.equalTo(expectedErrorMessage))
                .body("cod", Matchers.equalTo("404"));
    }

    /**
     * DataProvider for multiple input types (city/state and zip code).
     * - Supplies test data with a mix of city/state and zip code inputs.
     */
    @DataProvider(name = "multipleInputData")
    public Object[][] multipleInputData() {
        return new Object[][]{
                {"Madison, WI", "80014", "Chicago, IL", "10001"},
                {"12345", "Phoenix, AZ", "New York, NY", "90210"},
        };
    }

    /**
     * Test method for multiple input types.
     * - Tests both city/state and zip code inputs.
     */
    @Test(dataProvider = "multipleInputData")
    public void testMultipleLocations(String[] dataSet) {
        // Iterate through each input
        for (String expectedLocation : dataSet) {
            Response response;
            // Determine if the input is a zip code or city/state
            if (expectedLocation.matches("\\d{5}")) {
                response = GeoLocationUtil.getLocationByZip(expectedLocation);
            } else {
                response = GeoLocationUtil.getLocationByName(expectedLocation);
            }

            // Assert that the response is not null
            Assert.assertNotNull(response, "Response should not be null");

            // Assert specific conditions based on input type
            if (expectedLocation.contains(",")) {
                Assert.assertTrue(response.asString().contains(expectedLocation.split(",")[0].trim()), "Response should contain the city name");
            } else {
                Assert.assertTrue(response.asString().contains("US"), "Response should confirm the country 'US'");
            }
        }
    }
}
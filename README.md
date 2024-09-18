# Fetch Take-Home Test

## Overview

This project is a take-home test for the Fetch SDET job application. It provides a Java utility that interacts with the OpenWeather Geocoding API to retrieve geographical information based on city name/states, or ZIP codes. The utility returns details such as latitude, longitude, and place name and supports handling multiple location inputs.


- Coordinates by Location Name: Fetches latitude, longitude, and place name for a given city or state
- Coordinates by ZIP Code: Retrieves geographical information for a given ZIP code.
- Multiple Location Handling: Processes multiple location inputs in a single run.

## Getting Started

This project has been packaged into a single executable JAR that includes all dependencies.

## Prerequisites:
- Java 17 installed on your system.
- Maven installed for building and packaging the project.
 
  If Maven is not already installed, you can download and install it from [Maven's official website](https://maven.apache.org/install.html). You can also verify the installation by running the following command in your terminal:
  ```bash
  mvn -v

## Steps to Run Open Weather API Utlility:

1. Clone this repository:
   ```bash
   git clone https://github.com/Aydarskiy/OpenWeatherAPI.git
   cd OpenWeatherAPI
   
2. Download the Executable JAR
   You can download the pre-built JAR file from the [Releases page](https://github.com/Aydarskiy/OpenWeatherAPI/releases).
   
3. Run the application:
   ```bash
   java -cp "absolutePathToDownloaded/OpenWeatherAPI-1.0-SNAPSHOT.jar" org.weather.GeoLocationUtil New York
  Replace "absolutePathToDownloaded" with absolute path to OpenWeatherAPI-1.0-SNAPSHOT.jar file. (C:\ on Windows, / on Linux or macOS)   
  Replace New York with your desired city name or ZIP code.

### Notes:

Ensure your `Configuration.properties` file is set up correctly with your own `API_KEY` and `OpenWeatherBaseURL` values.

If you receive a "Path for properties file is invalid" message, make sure that the path to the `Configuration.properties` file is specified correctly in the `ConfigReader` class.



## Running Tests
1. **Run the tests** using Maven:
   ```bash
   mvn test
This command will automatically execute the test suite defined in testng.xml, validating both positive and negative scenarios for the API calls.

2. **View test results**:
   
After running the tests, you can find a detailed report in the `target/surefire-reports` directory.

### Additional Resources

- [OpenWeatherMap API Documentation](https://openweathermap.org/api/geocoding-api)
- [Maven Documentation](https://maven.apache.org/)
- [Rest Assured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)

### Contact

For any questions or feedback, please contact me at [aydarzarif@gmail.com](mailto:aydarzarif@gmail.com).

### Thank You!

Thank you for reviewing this project! Your time and consideration are greatly appreciated. If you have any questions or need further information, please feel free to reach out. Looking forward to your feedback and hoping for the opportunity to discuss this further. 

Best regards,

Aydar Z.




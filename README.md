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

## Steps to Run Open Weather API Utlillity:

1. Clone this repository:
   ```bash
   git clone https://github.com/Aydarskiy/OpenWeatherAPI.git
   cd OpenWeatherAPI
   
2. Download the Executable JAR
   You can download the pre-built JAR file from the [Releases page](https://github.com/Aydarskiy/OpenWeatherAPI/releases).
   
3. Run the application:
   ```bash
   java -cp "absolutePathToDownloaded/OpenWeatherAPI-1.0-SNAPSHOT.jar" org.weather.GeoLocationUtil New York
  Replace "absolutePathToDownloaded" with path to OpenWeatherAPI-1.0-SNAPSHOT.jar file. (C:\ on Windows, / on Linux or macOS)   
  Replace New York with your desired city name or ZIP code.
  


   
   




   


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

## Steps to Run:

1. Clone this repository:
   ```bash
   git clone https://github.com/Aydarskiy/OpenWeatherAPI.git
   cd OpenWeatherAPI
   
2. Build the project and create an executable JAR:
   ```bash
   mvn clean package
   
3. Run the application:
   ```bash
   java -jar target/OpenWeatherAPI-1.0-SNAPSHOT.jar "New York" "54321"
  Replace "New York" and "54321" with your desired city name or ZIP code.


   
   




   


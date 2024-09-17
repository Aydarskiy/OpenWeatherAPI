# Fetch Take-Home Test

## Overview

This project is a take-home test for the Fetch SDET job application. It provides a Java utility that interacts with the OpenWeather Geocoding API to retrieve geographical information based on city name/states, or ZIP codes. The utility returns details such as latitude, longitude, and place name and supports handling multiple location inputs.


- Coordinates by Location Name: Fetches latitude, longitude, and place name for a given city or state
- Coordinates by ZIP Code: Retrieves geographical information for a given ZIP code.
- Multiple Location Handling: Processes multiple location inputs in a single run.

## Getting Started

This project has been packaged into a single executable JAR that includes all dependencies.

### Prerequisites:
- Java 17 installed on your system.

### Steps to Run:

1. Clone this repository:
   ```bash
   git clone https://github.com/Aydarskiy/OpenWeatherAPI.git
   cd OpenWeatherAPI
   
2. Build the project and create an executable JAR:
   ```bash
   mvn clean package
   
3. Run the application:
   ```bash
   java -jar target/FetchTakeHomeTest-1.0-SNAPSHOT.jar "New York" "54321"

   
## How to tweak this project for your own uses

Since this is an example project, I'd encourage you to clone and rename this project to use for your own puposes. It's a good starter boilerplate

## Find a bug?

If you found an issue or would like to submit an improvement to this project, please submit an issue using the issues tab above. If you would like to submit a PR with a fix, reference the issue you created!

## Known issues (Work in progress)

This tutorial is till ongoing. The automation of the helm chart repo has not been completed yet. This is coming soon!

## Like this project?

If you are feeling generous, buy me a coffee! - https://www.buymeacoffee.com/askcloudtech

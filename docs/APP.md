# Weather Tracking App Specs

## Objective

- Build a weather app that demonstrates your skills in Kotlin, Jetpack Compose, and clean
  architecture.
- The app should allow users to search for a city, display its weather on the home screen, and
  persist the selected city across launches.
- Follow the Figma designs closely and integrate data from WeatherAPI.com.

## Task Description

- Home Screen
    - Displays weather for a single saved city, including
        - City name
        - Temperature
        - Weather condition (with corresponding icon from the API)
        - Humidity (%)
        - UV index
        - "Feels like" temperature
    - If no city is saved, prompt the user to search
    - Search bar for querying new cities
- Search Behavior
    - Show a search result card for the queried city
    - Tapping the result updates the Home Screen with the city's weather and persists the selection
- Local Storage
    - Use SharedPreferences, DataStore, or equivalent to persist the selected city
    - Reload the city's weather on app launch

## API Integration

- Use WeatherAPI.com to fetch weather data
    - ⭐️ 🔗API Documentation: WeatherAPI Documentation. 🔗⭐️
    - [Weather API Documentation](https://www.weatherapi.com/docs/)
    - Free tier includes current weather data with
        - Temperature
        - Weather condition (including an icon URL)
        - Humidity (%)
        - UV index
        - Feels like temperature

## Requirements

- Tech Stack
    - Use Kotlin and Jetpack Compose exclusively
    - Follow MVVM architecture with modular, testable code
    - Use dependency injection (e.g., Koin, Hilt) and interface-based abstractions
- API
    - Fetch weather data using WeatherAPI.com
    - Handle errors gracefully (e.g., invalid cities, no network)
- UI
    - ⭐️ 🔗Match the Figma designs here. 🔗⭐️
    - [Figma](https://www.figma.com/design/0zySCKWbyeRO805ifaz1lr/Weather-App-Test-Task?node-id=0-1&p=f)
- Time
    - This task should take no more than 5 hours
    - Please stop after 5 hours so that not only do you not overwork yourself but we get a feel for
      your speed

## Submission

- Send amin@10fc.tech an email and provide a public GitHub repo with
    - Complete source code
    - Setup instructions in the README
- Questions? Feel free to reach out for clarification.

## Evaluation Criteria

- Architecture & Code Quality
    - Clean, modular, and testable code
    - Proper separation of concerns (MVVM)
- Local Storage
    - Reliable persistence of the selected city
- Networking
    - Smooth API integration and error handling
- UI/UX
    - Matches Figma designs and feels intuitive
    - Includes all required weather details (e.g., icons, humidity, UV index, "feels like")
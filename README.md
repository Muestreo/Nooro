# Nooro

## Setup Instructions

- API KEY is set in local.properties
    - `WEATHER_API_KEY`
- build.gradle.kts enables BuildConfig
    - `buildConfig = true`
    - `fetchWeatherApiKey` gets API KEY from local.properties
        - defined in `:core:network` module
    - `BuildConfig` sets API KEY for use in network call

## Architecture/Design

- Clean Architecture distinct layers in Presentation, Domain, and Data
- MVVM distinct components for Model, View, and ViewModel
- MVI unidirectional data/events flow
- DI with Hilt
- SOLID

## Choice of Tools

- Room supports structured data and relationships
    - WeatherEntity
    - ConditionsEntity
    - Could have expanded for Location, Current, etc.
- Preferences DataStore
    - Persistence of selected city
- Retrofit/OkHttp
    - Network/API calls
    - Logging on network calls
- Dagger/Hilt
    - Dependency Management

## Areas for Improvement with more time

- `build-logic` module for managing gradle files and dependencies
- More robust testing in traditional triangle pyramid
    - Unit (70%), Integration (20%), UI (10%) tests
    - Test Fixture pattern for test data
- Paging for `n` cities
- Improve UI/UX, user preferences (i.e. Fahrenheit vs Celsius)
- Definitely has potential for growth with many more features & complexity

## Conclusion

- Clean Architecture is too much for a small/simple project
    - but showcases understanding of this architecture
    - weather app has potential for growth and complexity
    - good foundation for scalability and many software principles (i.e. `x`-ability)
    - also think it's a better fit for complex/enterprise systems
        - in this scenario Android would fit in the presentation layer
- MVVM is standard for Android projects
- MVI is recommended practice on Android `Guide to app architecture` page

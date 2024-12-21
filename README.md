# Nooro

## Setup Instructions

- API KEY is set in local.properties
    - `WEATHER_API_KEY`
- build.gradle.kts enables BuildConfig
    - `buildConfig = true`
    - `fetchWeatherApiKey` gets API KEY from local.properties
    - `BuildConfig` sets API KEY for use in network call

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
- Hilt
    - Dependency Injection

## Areas for Improvement with more time

- More robust testing in tradition triangle pyramid
    - Unit (70%), Integration (20%), UI (10%) tests
- Paging for `n` cities
- Improve UI/UX

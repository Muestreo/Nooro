# Domain Layer of Clean Architecture

## Kotlin Library

- No external dependencies
- No knowledge of Android System

## Responsibilities

- Drives domain requirements via use cases
- Contains pure Kotlin entities and abstracts repositories
- No external dependencies (Kotlin Library)

## Key Points

- Use Case Structure
    - each use case represents a single business action (e.g., `FetchWeatherByRegionUseCase`)
    - use `invoke()` operator for concise use case execution
- Repository Contracts
    - clearly define repository abstractions
- Testing
    - easily testable

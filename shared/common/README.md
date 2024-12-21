# Common Module

## Android Library

- Android System Dependencies

## Responsibilities

- Cross-cutting concerns
    - Common Screens
    - Navigation/Routing
    - Logging
    - Base State, ViewModel, Exception

## Key Points

- Error Handling
    - Use sealed classes or `Result` for standardized error propagation
- Logging
    - Use libraries like Timber for logging across layers
- Threading
    - Use coroutines for asynchronous operations, e.g. `viewModelScope` for UI tasks,
      `Dispatchers.IO` for data operations
- DI Configuration
    - Use DI framework to provide layer-specific dependencies, e.g. `RepositoryModule`,
      `UseCaseModule`
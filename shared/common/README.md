# Common Module

## Android Library

- Android System Dependent

## Cross-cutting concerns

- Common Screens
- Navigation/Routing
- Logging
- Base State
- Base ViewModel
- UseCase Exception

## Key Points

- Error Handling
    - Use sealed classes or `Result` for standardized error propagation
- Logging
- Threading
    - Use coroutines for asynchronous operations, e.g. `viewModelScope` for UI tasks,
      `Dispatchers.IO` for data operations
- DI Configuration
    - Use DI framework to provide layer-specific dependencies, e.g. `RepositoryModule`,
      `UseCaseModule`

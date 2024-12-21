# App Module: Presentation Layer of Clean Architecture (includes feature modules)

## Responsibilities

- Android specific
    - contains activities, fragments, view models, adapters, and so on
    - composables for modern development
- UI component responsibilities:
    - presentation/UI logic
    - adapting data to views typically by observing state change in activities and fragments
    - user interactions such as validating and/or submitting data input via UseCase(s)
    - communication with domain layer via DI to retrieve and display data
- Hilt DI
    - Service Locator if not an option

## Key Points

- UI State Management
    - use `State` and `StateFlow` for reactive UI
    - avoid tight coupling with `LiveData` to keep ViewModels clean
- Dependency Management
    - Dagger/Hilt or Service Locator as alternative
- ViewModel Responsibilities
    - acts as a mediator between the View and Domain layers
    - avoid placing any Android framework classes (like `Context`) or business logic in VM

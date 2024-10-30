# MyCryptoApp sample

MyCryptoApp is a sample app that displays cryptocurrencies and their related info.


## Screenshots

<img width="159" alt="Screenshot 2024-10-29 at 9 08 24 PM" src="https://github.com/user-attachments/assets/11f38380-5b0f-4a71-9cdf-2d49d6d57b4c">
<img width="157" alt="Screenshot 2024-10-29 at 9 11 43 PM" src="https://github.com/user-attachments/assets/6d22c21c-782a-4932-8c1e-6045d74043ed">
<img width="158" alt="Screenshot 2024-10-29 at 9 13 01 PM" src="https://github.com/user-attachments/assets/ca3a0fc2-ceb1-48a7-9fd7-c73f64285496">

## Features

This sample contains three screens: a splash screen, a list of cryptos and a detail page for each crypto.


## Architecture

The app follows best practices like:
- Separation of concerns (Clean Architecture)
- Drive UI from data models
- Single source of truth -> Room
- Coroutines and flows
- Unidirectional Data Flow (UDF)
- Model-View-Intent (MVI)
- Dependency Injection (DI) -> Hilt

## Testing

To facilitate UI and Navigation testing, MyCryptoApp uses dependency injection with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

In Unit Tests, [MockK](https://mockk.io/) is used to mock the dependencies.

In API Unit Tests, [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) is used to mock HTTP responses.

## UI

The app was designed using Material 3.

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/compose).

The app theme also supports dark mode.

## Continuous Integration (CI)

The project contains a CI workflow that uses Github Actions and automates the build and testing pipeline.



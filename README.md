# KMP Daily Pulse News Project
This project uses Kotlin Multiplatform(KMP) to share code between Android and iOS. The project was developed with a focus on scalability and implementing best development practices.

## Architecture
* **Clean Architecture:** Following Clean Architecture principles, the code is modularized into layers, facilitating maintenance and testing.
* **SOLID:** Following SOLID principles to ensure clean, easily extensible, and modifiable code.
* **Dependency Injection:** Koin to simplify dependency injection, promoting greater flexibility.

## Key Libraries
* **Kotlin Multiplatform (KMP):** Shared code between Android and iOS.
* **Jetpack Compose:** Declarative framework to build modern UIs on Android.
* **Swift UI:** Declarative framework to build modern UIs on iOS.
* **SQLDelight:** Multiplatform database management with SQL support.
* **Ktor:** Used for making network calls on both platforms.
* **Koin:** Multiplatform dependency injection framework.
* **Kotlinx Coroutines:** Asynchronous programming in Kotlin.
* **Kotlinx Serialization:** JSON data serialization.
* **BuildKonfig:** Stores sensitive settings, such as keys, without exposing them in the repository.

## Test Libraries
* **Turbine:** Testing flows with Flow.
* **Robolectric:** For Android UI tests without an emulator.
* **MockMP:** Used for creating mocks in multiplatform unit tests.
* **Kotlinx Coroutines Test:** Facilitates testing of asynchronous coroutines.
* All unit tests were implemented within the shared module, ensuring complete coverage of shared code between platforms.

## Scalability
This project is designed to be easily scalable, allowing new functionalities and modules to be added without compromising the existing architecture. The combination of clean architecture, SOLID principles, and multiplatform code with KMP is rare in a single project, making this an excellent starting point for robust projects.


## How to Install
 1. Clone the repository to your local machine
 2. Obtain the API key from [News API](https://newsapi.org)
 3. Create **API_KEY** variable in your `local.properties`
 ```kotlin
   API_KEY = {your_API_key} 
```
4. Open the project in Android Studio or your preferred IDE for KMM development and run the project on your preferred platform.

## Architectural diagram

The UI/Framework layers reside in the native apps, while everything from the View Model up to the Data layer is in the common KMP module.

![clean arch](https://github.com/user-attachments/assets/abc006e3-414f-4fa8-b9f2-a9cb69f42e91)

## Demonstration

### Android
https://github.com/user-attachments/assets/145b97b8-f92c-4879-8444-d302b1bf66d4


### iOS
https://github.com/user-attachments/assets/e9ad6f14-04e4-4758-a86e-c45139bef148

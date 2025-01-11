# Android Product App

A modern Android application showcasing products using Clean Architecture principles and the latest Android development tools and libraries.

## 🛠 Tech Stack & Open-source libraries
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native Android UI
- [Hilt](https://dagger.dev/hilt/) for dependency injection
- JetPack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes
  - ViewModel - Manages UI-related data holder and lifecycle aware
  - Room - Constructs Database by providing an abstraction layer over SQLite
  - App Startup - Provides a straightforward way to initialize components at application startup
- Architecture
  - MVI (Model-View-Intent) Architecture
  - Clean Architecture approach
  - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java
- [Material Design 3](https://m3.material.io/) - Latest Material design components for Android

## 🏗️ Architecture
This app follows Clean Architecture principles and is organized into the following modules:

### Architecture Components
- **Data Layer**: Handles data operations using Repository pattern
- **Domain Layer**: Contains business logic and use cases
- **Presentation Layer**: Implements MVI pattern with Compose UI

## 🌟 Features
- Display list of products
- Product details view
- Offline support with Room database
- Error handling and loading states
- Clean and Modern UI with Material Design 3


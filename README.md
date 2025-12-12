Seekho Anime App
================

Description
-----------

Seekho Anime App is a modern Android application built with Kotlin using MVVM architecture, Clean UseCase-based domain flow, Hilt Dependency Injection, Retrofit for API communication, and Room Database for offline caching. The app fetches Top Anime and Anime Details from the Jikan API and provides a smooth UI experience with Shimmer loading and YouTube trailer playback.

Features
--------

*   **Modern UI**: XML layouts with Material Design guidelines
    
*   **Top Anime List**: Fetches from Jikan REST API
    
*   **Anime Details**: Shows trailer, poster, genres, rating, episodes, and synopsis
    
*   **YouTube Trailer**: Playback with poster fallback option
        
*   **Offline Support**: Room Database for caching
    
*   **Shimmer Loading**: Better UI experience during loading
    
*   **Reactive UI**: Kotlin Flow and StateFlow for updates
    
*   **Error Handling**: For API, network, and playback issues
    
*   **Clean Architecture**: Proper separation of concerns
    
*   **Dependency Injection**: Using Hilt
    

Architecture
------------

*   **Data Layer**: Retrofit API service, Room Database with DAOs, Repository implementations, DTO and Entity models
    
*   **Domain Layer**: UseCases with business logic, Repository interfaces, Domain models
    
*   **UI Layer**: Activities with XML layouts, RecyclerView lists, Adapters, ViewModels with StateFlow, Shimmer, YouTube Player
    

Tech Stack
----------

*   **Kotlin**: Programming language
    
*   **XML + Material Components**: UI framework
    
*   **MVVM + Clean Architecture**: Architecture pattern
    
*   **Dagger Hilt**: Dependency Injection
    
*   **Retrofit + OkHttp + Gson**: For API calls
    
*   **Room Database**: Local database
    
*   **Kotlin Coroutines + Flow**: Async operations
    
*   **YouTube Player API**: Media integration
    
*   **Shimmer**: Skeleton loading
    
*   **StateFlow**: Reactive UI

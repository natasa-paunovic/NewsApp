<h1><b>Project Overview</b></h1>

This is a Kotlin-based Android news app built with Jetpack Compose.
It fetches news articles from an API, displays them in a paginated list, supports pull-to-refresh, error handling, and shows detailed screen for each article.
The app also supports deep links to open articles directly via URLs like `myapp://article/{id}`

This project demonstrates a modern architecture with focus on:

* MVVM + Clean Architecture
* Jetpack Compose
* Custom paging
* Coil for images
* Coroutine and Kotlin Flow
* Repository pattern
* Mapper pattern
* DI (Using Koin)
* Unit test for mapper

<h1><b>Architecture Decisions</b></h1>

* Single ViewModel (NewsViewModel) for both list and detail screen.
   * Simplifies state management.
   * Selected article is stored in the same ViewModel, avoiding the need to pass the full object through navigation.

* Jetpack Compose + Compose Navigation
   * Declarative UI and simplified state handling.
   * Infinite scroll handled with LazyListState and snapshotFlow.

* State management
  * ArticleState holds UI state: articles, pagination, errors.
  * MutableStateFlow + StateFlow ensures UI reacts to state changes.

* Deep link support
  * myapp://article/{id} opens the detail screen directly.
  * ViewModel handles fetching the article if it isn’t already in cache.

* DTO → Domain Mapper
  * ArticleDto.toDomain() maps API response to domain model.
  * Handles empty or null fields (author, sourceName, imageUrl) gracefully.

* Repository pattern
  * ArticleRepository separates API layer from ViewModel.
  * Supports Resource sealed class for success/error/loading handling.

<h1><b> Dependency Management </b></h1>

* Gradle Version Catalog (libs.versions.toml)
* Structured test dependency separation

<h1><b> Scalability </b></h1>

This architecture allows easy extension. Future additions could include:

* Room database for offline
* Multi-module separation (feature modules)

<h1><b> Future Improvements </b></h1>

For future potential improvements it can be integrated:

* Room for offline caching to persists articles locally
* Add more unit and integration tests for Compose screens, View Model and Repository.
* NavGraph3 
* CI pipeline (GitHub Actions) 
 
 


## How to Run
Open Android Studio -> New -> Project From Version Control -> put URL https://github.com/natasa-paunovic/NewsApp

or

**Clone the repository:**
   ```bash
   git clone <(https://github.com/natasa-paunovic/NewsApp)>

API Key configuration is located in build.gradle (app level)
Make sure BuildConfig.NEWS_API_KEY references this key in your app module.




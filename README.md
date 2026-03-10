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

## How to Run
Open Android Studio -> New -> Project From Version Control -> put URL https://github.com/natasa-paunovic/NewsApp

or

**Clone the repository:**
   ```bash
   git clone <(https://github.com/natasa-paunovic/NewsApp)>

API Key configuration is located in build.gradle (app level)
Make sure BuildConfig.NEWS_API_KEY references this key in your app module.




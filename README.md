<h1 align="center" id="title">YUKI GAMES</h1>

<p id="description">In the application where countless games are listed you can easily add your favorites and explore new games that suit your taste with the comprehensive search feature. By browsing games in different categories you can discover masterpieces that cater to every interest and with sorting options you can see the most popular and highly-rated games.</p>

<h2>Project Screenshots:</h2>

<div style="display: flex; gap: 10px;">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/intro_1.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/intro_2.png" alt="project-screenshot" width="201" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/intro_3.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/intro_4.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/splash.png" alt="project-screenshot" width="200" height="400/">
</div>



<div style="display: flex; gap: 10px;">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/home.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/favorites.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/search.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/categories.png" alt="project-screenshot" width="200" height="400/">
  <img src="https://raw.githubusercontent.com/kemalgeylaniyuki/CleanArchitectureGamesApp/master/app/details.png" alt="project-screenshot" width="200" height="400/">
</div>


<h2>🧐 Features</h2>

Here're some of the project's best features:

* 🔍 Home
  - On the home page, you'll find a list of the most popular and the newest games in the gaming world. Follow the trends or explore the latest releases.
    
* 🎮 Game Details
  - You can access detailed information of the game and learn more about the game. Also on the detail page of each game, you'll find a checkbox to add or remove it from your favorites.
    
* ⭐ Favorites
    - On the favorites page, your chosen games are listed in a special way. There's no chance of forgetting which games you love!
      
* 🔎 Search
  - Use the search page to easily find the game you're looking for. Search results dynamically update based on the keywords you type. Finding lost games is now easier than ever!
    
* 📂 Category
  - On the category page, you can filter games by categories. Click on a category you're interested in, and the games belonging to that category will be listed. Exciting thematic discoveries await you!  
  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Clean Architecture
*   MVVM
*   Single Activity
*   Kotlin Coroutines
*   Retrofit
*   Room Database
*   Flow
*   Stateflow
*   Hilt DI
*   Navigation Component
*   Viewbinding
*   Glide

<h2>💎Clean Architecture</h2>

<img src="https://raw.githubusercontent.com/kemalgeylaniyuki/YUKI-GAMES/master/app/cleanArc.png" alt="project-screenshot" width="400" height="300/">

Kotlin Clean Architecture is a design approach that aims to make software applications modular, sustainable, and testable. 
Clean Architecture divides software projects into layers, each with specific responsibilities. 
This enables better organization of code, management of dependencies, and facilitates easier implementation of changes.

<img src="https://raw.githubusercontent.com/kemalgeylaniyuki/YUKI-GAMES/master/app/clean.png" alt="project-screenshot" width="600" height="200/">

<h3>Data Layer</h3>
This layer is responsible for communication with databases, network requests, and other external resources. 
Data retrieval and sending processes occur in this layer.

<h3>Domain Layer</h3>
It is the layer that houses the core business logic of the application. Business rules, entities, and use cases are defined here.

<h3>Entity</h3>
Entity includes the fundamental data structures of the application. 
It typically represents objects retrieved from the database and interacts with the domain layer.

<h3>Presenter Layer</h3>
Serving as a bridge between the user interface and business logic, 
this layer takes on tasks such as presenting data to the user interface and handling user interactions.

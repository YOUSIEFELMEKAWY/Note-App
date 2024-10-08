﻿# Note-App

# Overview
NoteApp is a simple and intuitive Android application built using Jetpack Compose that allows users to create, edit, and delete notes. This application demonstrates the use of modern Android development tools and libraries, including Jetpack Compose, ViewModel, Room Database, and more.

# Features
* Create Notes: Users can easily create new notes with a title , description and color. 
* View Notes: All notes are displayed in a list format, allowing users to quickly browse through their notes.
* Edit Notes: Users can edit the title and content of existing notes.
* Delete Notes: Swipe to delete functionality allows users to remove notes with a simple gesture.
* Persistent Storage: Notes are stored locally using Room Database, ensuring that they persist across app restarts.

# Tech Stack
* Kotlin: The programming language used for the entire application.
* Jetpack Compose: Used for building the UI in a declarative manner.
* ViewModel: Manages UI-related data in a lifecycle-conscious way.
* Room Database: Used for local data storage, providing an abstraction layer over SQLite.
* Voyager: For navigation within the application.
* Material Design 3: For consistent UI/UX, making use of Material Components.

# Project Structure
* models/: Contains the data models, including the Note class.
* screens/: Contains all the composable functions representing different screens of the app, like HomeScreen, NoteDetailsScreen, etc.
* viewModel/: Contains the NoteViewModel, which handles the business logic and interacts with the Room database.
* database/: Contains the database setup, including DAOs and the Room database class.
* ui/theme/: Contains the theming files for light and dark modes

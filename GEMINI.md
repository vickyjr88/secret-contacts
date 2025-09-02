# Project: Secret Contacts

## Overview

Secret Contacts is an Android application designed to store and manage a list of contacts that are hidden from the device's main contact list. This provides a private and secure way for users to keep sensitive contact information.

## High-Level Plan

1.  **Project Setup:** Create a new Android project with modern technologies.
2.  **Core Functionality:** Implement the basic features for creating, reading, updating, and deleting (CRUD) contacts within the app.
3.  **UI/UX:** Design a simple and intuitive user interface for managing the secret contacts.
4.  **Data Persistence:** Use a local database (like Room) to store the contact information securely on the device.
5.  **Security:** Implement measures to protect the contact data, such as password/biometric protection.
6.  **Hiding Contacts:** Ensure that the contacts saved in the app are not visible in the default Android contacts application.

## Technology Stack

*   **Language:** Kotlin
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **UI:** Jetpack Compose
*   **Asynchronous Programming:** Kotlin Coroutines
*   **Database:** Room Persistence Library
*   **Dependency Injection:** Hilt

## Project Structure

The project will follow a standard Android project structure:

```
secret-contacts/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── secretcontacts/
│   │   │   │               ├── MainActivity.kt
│   │   │   │               ├── data/
│   │   │   │               │   ├── Contact.kt
│   │   │   │               │   └── ContactDao.kt
│   │   │   │               ├── di/
│   │   │   │               │   └── AppModule.kt
│   │   │   │               ├── ui/
│   │   │   │               │   ├── theme/
│   │   │   │               │   └── contacts/
│   │   │   │               │       ├── ContactsScreen.kt
│   │   │   │               │       └── ContactsViewModel.kt
│   │   │   └── res/
│   │   │       └── ...
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Next Steps

I will start by creating the basic Android project structure. I will use the `shengmtaaandroid` as a reference for the folder structure.
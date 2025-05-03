# 🐶 Dog Breed Explorer

Browse and explore information about dog breeds with this Java-based web application — built with Vaadin and powered by TheDogAPI and OpenAI.
    - this was a school project!

## 📚 Table of Contents

- [🚀 Features](#-features)
- [📦 Requirements](#-requirements)
- [🔧 Setup Instructions](#-setup-instructions)
- [🔑 Where to Get API Keys](#-where-to-get-api-keys)
- [💡 How It Works](#-how-it-works)
- [🏁 Starting the Application](#-starting-the-application)
- [🛠 Tech Stack](#-tech-stack)
- [📌 Notes](#-notes)

## 🚀 Features

- Fetches real-time dog breed data and images from TheDogAPI
- Displays breed-specific pages with images and information
- Can ask our AI chatbot (using OpenAI) for a suggested breed
- Clean, responsive UI built with Vaadin
- Pop-up dialogs for breed info with navigation to detail pages

## 📦 Requirements

- Java 17+
- Maven (included via `mvnw` wrapper)
- `.env` file with API keys:
  - TheDogAPI key
  - OpenAI API key

## 🔧 Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/dog-breed-explorer.git
   cd dog-breed-explorer
   ```

2. **No additional installation needed**
- Dependencies are handled via Maven. Just run:
    ```bash
    mvn clean install
    ```
- No frontend build steps required — Vaadin compiles everything via Java.

3. **Set Up .env**

    ```bash
    THE_DOG_API_KEY = 
    OPENAI_API_KEY = 
    ```

## 🔑 Where to Get API Keys
1. **TheDogAPI Key**
   - FREE: You can get your **TheDogAPI** key from [TheDogAPI](https://thedogapi.com/).
   
2. **OpenAI Key** 
   - COSTS MONEY: Sign up for an API key from [OpenAI](https://platform.openai.com/signup). 

## 💡 How It Works

1. **Select Breeds tab**, the app fetches a list of dog breeds from TheDogAPI.

2. **Click on Dog Breeds tab** in a scrollable card layout with image + description.

3. **Clicking a breed** opens a pop-up with a "Details" button.

4. The **Dog Selector** uses **OpenAI** to suggest breeds based on prompts. Then will give you a clickable route to more information on the breed.

6. **Breed data is stored** in-memory for fast access during runtime.

## 🏁 Starting the Application

1. **Using the Maven wrapper:**

    ```bash
    ./mvnw spring-boot:run
    ```

2. **Or, if Maven is installed globally:**

    ```bash
    mvn spring-boot:run
    ```

3. **Or, if in IntelliJ just click:** Run 'Main'

4. **Then navigate to**:

    ```
    http://localhost:8080
    ```

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot** – backend framework
- **Vaadin Flow** – frontend/UI framework (server-side rendering)
- **OkHttp** – HTTP client for API calls
- **Jackson** – JSON parsing
- **dotenv-java** – load .env config
- **TheDogAPI** – dog breed info and images
- **OpenAI API (optional)** – for breed suggestion logic

## 📌 Notes

- The `.env` file is required to run.
- This app is built entirely in Java with no JavaScript or frontend build tools.

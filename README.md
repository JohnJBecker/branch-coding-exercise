# Branch coding exercise
[![forthebadge](https://forthebadge.com/images/badges/powered-by-water.svg)](https://forthebadge.com)

## Description
This service is an API to fetch a github user and all their associated repos

---

## Dependencies

This project uses the following dependencies:

- **Java 17**: The application is built with Java 17.
- **Spring Boot**: For creating and managing the RESTful API.
- **Redis**: For caching API responses.
- **Gradle**: Build tools.
- **JUnit**: For testing.
- **Mockito**: For mocking during tests.
- **OpenFeign**: For external API calls.

You can find a full list of dependencies in the `build.gradle.kts` (Gradle) file.

---

## Project Structure

The project is structured by domain, meaning that packages are organized around specific business areas or entities (users, github, redis, etc.). Each domain includes related functionality, making the code easier to maintain, scale, and understand by grouping features logically.

---

## How to Run the Service

1. **Clone the repository**:
    ```bash
    git clone https://github.com/johnjbecker/branch-coding-exercise.git
    cd branch-coding-exercise
    ```

2. **Set up environment**:
    - Ensure you have Redis running locally
      - This can be done using docker and running `docker-compose up -d`.
    - Update the configuration in `application.yml` to match your Redis setup and any external API credentials.


3. **Build and Run**:

    ```bash
    ./gradlew clean build
    ./gradlew bootRun
    ```

4. **Verify the service is running**:  
   The application should now be running at `http://localhost:8080`. You can verify by hitting the users endpoint:
    ```bash
    curl http://localhost:8080/v1/users/githubuser
    ```

---

## How to Test the Service

1. **Run Tests**:
    ```bash
    ./gradlew test
    ```

   This will run all tests defined in the `src/test` folder. Mocking is done using **Mockito** to simulate interactions with external services.

2. **Manual Testing**:
   You can manually test the API using tools like **Postman** or **cURL**. Example API calls are listed in the next section.

---

## Example API Calls

Here are some example API calls to demonstrate how to interact with the service:

1. **Get User by ID**:
    ```bash
    GET /api/users/{id}
    curl -X GET http://localhost:8080/v1/users/johnjbecker
    ```
    Responses:

    `200`
    ```json
      {
        "user_name": "JohnJBecker",
        "display_name": "John",
        "avatar": "https://avatars.githubusercontent.com/u/14862846?v=4",
        "geo_location": null,
        "email": null,
        "url": "https://github.com/JohnJBecker",
        "created_at": "2015-09-27T18:56:11",
        "repos": [
            {
                "name": "base",
                "url": "https://github.com/JohnJBecker/base"
            },
            {
                "name": "cs368_final",
                "url": "https://github.com/JohnJBecker/cs368_final"
            },
            {
                "name": "k-means-clustering-implementation",
                "url": "https://github.com/JohnJBecker/k-means-clustering-implementation"
            }
        ]
      }
    ```
   
    `404`
     ```json
      {
        "message": "github user not found"
      }
    ```
---

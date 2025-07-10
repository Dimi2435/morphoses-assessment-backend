# Morphoses Senior Backend Assessment

A Spring Boot backend for managing classroom sessions, soft skill assessments, and review reporting for kids and instructors.

## Technologies Used

* **Java 17+**
* **Spring Boot 3.3.1**
* **Spring Data JPA**
* **PostgreSQL**
* **Gradle** for build automation
* **Flyway** for database migrations

## Prerequisites

* Java Development Kit (JDK) 17 or higher
* Gradle (usually bundled with Spring Boot projects, but ensure it's available or installed)
* PostgreSQL database server installed and running locally

## Database Setup (PostgreSQL with Flyway)

This project uses Flyway for database schema and initial data management. Please ensure you have PostgreSQL installed and configured before running the application.

1.  **Install PostgreSQL:**
    If you don't have PostgreSQL installed, download it from the official website: [https://www.postgresql.org/download/](https://www.postgresql.org/download/). Follow the installation instructions for your operating system.

2.  **Create Database and User:**
    Open your PostgreSQL client (e.g., `psql` command line tool or `pgAdmin`).
    Create a new database named `morphoses_db`.
    Create a new user named `morphoses_user` with the password `password`.
    Grant all privileges on `morphoses_db` to `morphoses_user`.

    **Example SQL commands (if using psql):**
    ```sql
    CREATE DATABASE morphoses_db;
    CREATE USER morphoses_user WITH PASSWORD 'password';
    GRANT ALL PRIVILEGES ON DATABASE morphoses_db TO morphoses_user;
    -- For UUID generation in V2 script, ensure 'uuid-ossp' extension is available if not already.
    -- You might need to run this *once* in your morphoses_db if you encounter issues with gen_random_uuid():
    -- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
    ```
    (Ensure you are connected as a superuser, e.g., `postgres`, when running these commands.)

3.  **Database Connection Details:**
    The application is configured to connect to `jdbc:postgresql://localhost:5432/morphoses_db` with `username=morphoses_user` and `password=password`. These settings are in `src/main/resources/application.properties`. If your PostgreSQL setup uses different credentials or port, please update this file accordingly.

## How to Run the Application

1.  **Clone the Repository:**
    ```bash
    git clone <your-repository-url>
    cd morphoses-assessment-backend # Or your chosen project name
    ```

2.  **Build the Project:**
    Open a terminal in the project's root directory (where `build.gradle` is located).
    ```bash
    ./gradlew clean build
    ```
    (On Windows, use `gradlew.bat clean build`)

3.  **Run the Application:**
    ```bash
    ./gradlew bootRun
    ```
    (On Windows, use `gradlew.bat bootRun`)

    **Flyway Migration:** Upon the first run, Flyway will automatically execute the SQL scripts located in `src/main/resources/db/migration` to create the database schema and insert initial data. Subsequent runs will only apply new migrations if any are added.

    The application will start on `http://localhost:8080` (or the port specified in `application.properties`).

## API Endpoints

(Keep the API endpoint documentation as it was previously provided.)
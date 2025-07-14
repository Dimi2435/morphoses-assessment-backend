# Author: Dimitrios Milios

# Morphoses Assessment Backend

## Technical Assessment Overview
This project is a minimal implementation of the Morphoses platform, focusing on classroom and session review functionality. It is built with Spring Boot, uses H2 (in-memory) for quick testing, Flyway for database migrations, and provides a REST API documented with Swagger.

### Key Features
- Two user types: kids and instructors
- Many-to-many participation in classrooms
- Classrooms contain sessions, each targeting up to 3 soft skills
- Session review: instructors and kids answer questions per soft skill (scale 1-5)
- Absent kids are not eligible for review
- Users can retrieve and compare review results after session completion
- Database schema managed by Flyway
- API documentation via Swagger UI
- In-memory H2 database for fast local testing (PostgreSQL can be used in production)
- Prepopulated with test users, classrooms, sessions, and questions
- JUnit tests with Jacoco coverage reports

---

## Getting Started

### 1. Clone the Repository
```bash
   git clone <repository-url>
   cd morphoses-assessment-backend
```

### 2. Database Setup
- **Default (Recommended for Testing):** Uses a file-based H2 database (`jdbc:h2:file:./data/morphosesdb`) as configured in `application.properties`. This database persists data across restarts and requires no external setup. The H2 web console is available at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (username: `sa`, password: empty).
- **Production:** To use PostgreSQL, update `application.properties` accordingly and run a PostgreSQL instance (see commented dependency in `build.gradle`).

### 3. Run Database Migrations
- Flyway is integrated and will automatically apply migration scripts on application startup, using the same datasource as the application.
- Migration scripts are located in `src/main/resources/db/migration/`.

### 4. Run the Application
```bash
   ./gradlew bootRun
```
- The API will be available at: `http://localhost:8080/api`

### 5. Access API Documentation (Swagger)
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 6. Running Tests & Generating Coverage Reports
- To run all tests:
```bash
   ./gradlew test
```
- To generate a Jacoco coverage report:
```bash
   ./gradlew jacocoTestReport
```
- The HTML report will be available at: `build/reports/jacoco/test/html/index.html`

---

## Prepopulating the Database
- On startup, the application prepopulates the H2 database with test users, classrooms, sessions, and questions.
- Check application logs for any errors during startup.

---

## Notes
- Authentication and authorization are **not** included in this assessment.
- For production, switch to PostgreSQL and update configuration as needed.
- For any schema changes, update Flyway migration scripts.

---


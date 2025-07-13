# Morphoses Assessment

## Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd morphoses-assessment-backend
   ```

2. **Set up the database**:
   - Ensure you have Docker installed.
   - Run the following command to start the PostgreSQL database:
   ```bash
   docker-compose up -d
   ```

3. **Run the application**:
   - Use your preferred method to run the Spring Boot application (e.g., using an IDE or via command line).
   ```bash
   ./gradlew bootRun
   ```

4. **Access the API**:
   - The API will be available at `http://localhost:8080/api`.

## Prepopulating the Database
- The application can be prepopulated with test users, classrooms, sessions, and questions. Ensure to check the application logs for any errors during startup.
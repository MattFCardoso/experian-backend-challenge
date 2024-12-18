# Simple Device Matching Backend Service

## Prerequisites
- Java 17 or higher
- Maven
- Run docker-compose.yml to start Aerospike database
```shell

## Setup
1. Clone the repository.
2. Navigate to the project directory.
3. Update `src/main/resources/application.properties` with your Aerospike configuration.
4. Run `mvn clean install` to build the project.
5. Start the application using `mvn spring-boot:run`.

## Running Tests
Run `mvn test` to execute unit tests.

## Endpoints
- `POST /devices`: Create or update a device.
- `GET /devices/{id}`: Get a device by ID.
- `GET /devices`: Get devices by OS name.
- `DELETE /devices`: Delete devices by IDs.

## Swagger Documentation
Swagger documentation is available to help you explore and test the API endpoints. Once the application is running, you can access the Swagger UI at:
http://localhost:8080/swagger-ui.html

This will provide a user-friendly interface to interact with the API and view the available endpoints and their details.
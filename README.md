# Employee Management API

This is a Spring Boot-based Employee Management API that allows CRUD operations on employee data. It integrates with third-party services to validate emails and departments and sends email notifications on successful employee creation.

## Features
- **Create Employee**: Validates employee details with third-party APIs and saves to the database.
- **Get Employee by ID**: Retrieves employee details by ID.
- **Update Employee**: Updates an employee's details.
- **Delete Employee**: Deletes an employee from the system.
- **List Employees**: Retrieves a list of all employees.
- **Third-Party Integration**: Email validation using external APIs (SendGrid, Mailgun, etc.) and department verification.
- **Email Notifications**: Sends an email notification after creating a new employee.
- **Exception Handling**: Graceful error handling with specific exception responses.
- **Async Processing**: Email notifications are sent asynchronously.

---

## Requirements
- **JDK 17+** (or higher)
- **Maven** (or Gradle) for dependency management
- **Spring Boot 2.x+** for REST API framework
- **H2 Database** (for in-memory storage)
- **Third-Party API Keys** for email validation and email sending service (e.g., SendGrid, Mailgun, etc.)
- **Postman or cURL** (for testing API endpoints)

---

## Setup Instructions

### 1. **Clone the Repository**

Clone the project to your local machine:

```bash
git clone https://github.com/your-repository/employee-management-api.git
cd employee-management-api
```

### 2. **Configure Email Provider (SendGrid / Mailgun)**

Before running the application, you'll need to configure the third-party services for email validation and notifications.

- **SendGrid**:
  1. Sign up at [SendGrid](https://sendgrid.com/).
  2. Create an API key.
  3. Set up your sender email address.
  4. Add your API key and sender email to the `application.properties` file.

- **Mailgun**:
  1. Sign up at [Mailgun](https://www.mailgun.com/).
  2. Get your API key and domain.
  3. Add your API key and domain to the `application.properties` file.

### 3. **Database Configuration**

By default, the application uses an **H2 in-memory database** for storing employee data. You can configure it in the `application.properties`:

```properties
# H2 Database settings
spring.datasource.url=jdbc:h2:mem:employee_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

This configuration will create the database in memory on each application startup.

### 4. **Configure Application Properties**

Configure the email service provider and other application settings in `src/main/resources/application.properties`.

```properties
# Application Settings
server.port=8080

# SendGrid API Configuration (if using SendGrid)
sendgrid.api-key=your-sendgrid-api-key-here
sendgrid.from-email=no-reply@yourdomain.com
sendgrid.from-name=Employee Management System

# Mailgun API Configuration (if using Mailgun)
mailgun.api-key=your-mailgun-api-key-here
mailgun.domain=your-mailgun-domain
mailgun.from-email=no-reply@yourdomain.com
mailgun.from-name=Employee Management System
```

Alternatively, you can use **environment variables** for better security:

```bash
SENDGRID_API_KEY=your-sendgrid-api-key-here
MAILGUN_API_KEY=your-mailgun-api-key-here
```

---

## Running the Application

1. **Build the Application**:

   If you're using **Maven**, you can build the project by running:

   ```bash
   mvn clean install
   ```

   If you're using **Gradle**, run:

   ```bash
   ./gradlew build
   ```

2. **Run the Application**:

   To run the application, use the following command:

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, if you are using Gradle:

   ```bash
   ./gradlew bootRun
   ```

   The application will start on port `8080` by default.

---

## API Endpoints

### 1. **Create Employee**  
- **Endpoint**: `POST /api/employees`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": "Engineering",
    "salary": 70000
  }
  ```
- **Response**:
  - **Status**: `201 Created`
  - **Body**: The newly created employee object with ID.

### 2. **Get Employee by ID**  
- **Endpoint**: `GET /api/employees/{id}`
- **Response**:
  - **Status**: `200 OK`
  - **Body**: Employee details with the specified `id`.

### 3. **Update Employee**  
- **Endpoint**: `PUT /api/employees/{id}`
- **Request Body**:
  ```json
  {
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "department": "HR",
    "salary": 75000
  }
  ```
- **Response**:
  - **Status**: `200 OK`
  - **Body**: The updated employee object.

### 4. **Delete Employee**  
- **Endpoint**: `DELETE /api/employees/{id}`
- **Response**:
  - **Status**: `200 OK`
  - **Body**: Confirmation message.

### 5. **List All Employees**  
- **Endpoint**: `GET /api/employees`
- **Response**:
  - **Status**: `200 OK`
  - **Body**: A list of all employees.

---

## Exception Handling

The API includes custom exception handling for the following scenarios:

1. **EmployeeNotFoundException**: If an employee is not found by ID.
2. **InvalidInputException**: For invalid input, such as incorrect email format.
3. **DatabaseException**: For database errors.
4. **ThirdPartyServiceException**: For failures in third-party service integrations (email or department verification).

---

## Asynchronous Email Notifications

The application sends **email notifications asynchronously** upon employee creation. This ensures that the API response is not delayed while waiting for email delivery.

### How It Works:
- Upon creating a new employee, the system validates the email and department through third-party services.
- If valid, it saves the employee to the database.
- Then, it triggers an asynchronous email notification to the provided email address.

---

## Testing the API

You can test the API using tools like **Postman** or **cURL**.

**Example cURL Request for Creating an Employee**:
```bash
curl -X POST "http://localhost:8080/api/employees" \
-H "Content-Type: application/json" \
-d '{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "department": "Engineering",
  "salary": 70000
}'
```

---

## Conclusion

This API enables basic employee management functionalities with email validation, department verification, and email notifications, and it integrates with third-party services like **SendGrid** or **Mailgun**. You can extend this API further with additional features like rate-limiting, audit logging, or circuit breaker patterns.

---

Feel free to modify

 and extend the project to meet your specific requirements!


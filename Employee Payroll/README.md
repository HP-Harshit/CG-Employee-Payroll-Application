# Employee Payroll Project

The Employee Payroll project is a comprehensive application designed to manage employee payroll information effectively. This project incorporates both a backend and frontend, offering features for creating, reading, updating, and deleting (CRUD) employee details. The project is developed using Angular for the frontend and Spring Boot for the backend, ensuring a robust and scalable solution.

## Features

- CRUD Operations: Add, view, update, and delete employee details.
- Validation: Comprehensive validation for employee details, ensuring data integrity.
- Responsive Design: User-friendly and responsive interface, built with Angular.
- Exception Handling: Robust error handling with meaningful error messages.
- MySQL Database Integration: Persistent storage of employee information using MySQL.
- Logging: Detailed logging for tracking application behavior and debugging.
- Hover Effects: Interactive hover effects for buttons and other UI elements.
- Detailed Readme: This detailed README provides an overview of the project and how to get started.

## Technologies Used

### Frontend

- Angular
- HTML5, CSS3, SCSS
- TypeScript

### Backend

- Spring Boot
- Java
- RESTful API

### Database

- MySQL

### Tools

- Maven
- Lombok
- Postman (for API testing)

## Prerequisites

- Node.js and npm
- Angular CLI
- Java Development Kit (JDK)
- MySQL Server
- Maven

## Setup and Installation

### Backend Setup

1. Clone the Repository:

    ```bash
    git clone <repository-url>
    cd Employee-Payroll-Backend
    ```

2. Configure the Database: Update the `application.properties` file with your MySQL database details:

    ```properties
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/employee_payroll_db
    spring.datasource.username=${DB_USERNAME:your-username}
    spring.datasource.password=${DB_PASSWORD:your-password}
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    spring.jpa.properties.hibernate.format_sql=true
    ```

3. Build and Run the Backend:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### Frontend Setup

1. Navigate to the Angular Project Directory:

    ```bash
    cd Employee-Payroll-Frontend
    ```

2. Install the Required Dependencies:

    ```bash
    npm install
    ```

3. Run the Frontend:

    ```bash
    ng serve
    ```

4. Access the Application: Open your browser and navigate to `http://localhost:4200`

### API Endpoints

- **Get All Employees**:
    ```
    GET /employeepayrollservice/
    ```

- **Get Employee by ID**:
    ```
    GET /employeepayrollservice/get/{id}
    ```

- **Create Employee**:
    ```
    POST /employeepayrollservice/create
    ```

- **Update Employee**:
    ```
    PUT /employeepayrollservice/update/{id}
    ```

- **Delete Employee**:
    ```
    DELETE /employeepayrollservice/delete/{id}
    ```

- **Get Employees by Department**:
    ```
    GET /employeepayrollservice/department/{department}
    ```

### Example JSON for Creating/Updating an Employee

```json
{
    "name": "Divit Smith",
    "gender": "Male",
    "department": "IT",
    "salary": 60000,
    "startDate": "2023-01-15"
}

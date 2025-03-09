# Employee Payroll Backend
---

This is the backend for the Employee Payroll application, built using Spring Boot. The backend provides RESTful APIs for managing employee records, including operations like creating, retrieving, updating, and deleting employee details.

## Features

- Add new employee records
- Retrieve all employee records
- Update existing employee records
- Delete employee records

## Technologies Used

- Spring Boot
- Spring Data JPA
- H2 Database (for development)
- MySQL (for production)
- Maven

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL (for production environment)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/employee-payroll-backend.git
    cd employee-payroll-backend
    ```

2. Configure the database:

    For development, the project uses H2 Database (in-memory). For production, configure MySQL database in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/employeepayroll
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

5. The application will start and run on `http://localhost:8080`.

### API Endpoints

- **Get all employees**:
    ```
    GET /employeepayrollservice/
    ```
    Retrieves a list of all employees.

- **Create a new employee**:
    ```
    POST /employeepayrollservice/create
    ```
    Creates a new employee. Request body should be in JSON format.

- **Update an employee**:
    ```
    PUT /employeepayrollservice/update/{id}
    ```
    Updates an existing employee by ID. Request body should be in JSON format.

- **Delete an employee**:
    ```
    DELETE /employeepayrollservice/delete/{id}
    ```
    Deletes an employee by ID.

### Example JSON for Creating/Updating an Employee

```json
{
    "name": "Divit Smith",
    "gender": "Male",
    "department": ["HR"],
    "salary": 50000,
    "day": 10,
    "month": "January",
    "year": 2023,
    "profileImage": "../assets/user1.png",
    "note": "Sample note"
}
```

## Contributing

We welcome contributions to improve this project. To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Make your changes.
4. Submit a pull request with a description of your changes.
---
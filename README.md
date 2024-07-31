![303569003-da6ce16c-66b4-40dd-bb1a-21eae1681f40](https://github.com/user-attachments/assets/10cb3a31-b6bd-492e-88ea-59d887412765 = 200*100)

# Shopping App Microservices

## Overview
The Shopping App Microservices project is a collection of microservices designed to handle various functionalities of an online shopping application. The project leverages Spring Boot for application development, Spring Cloud for service discovery and configuration management, and MySQL for database operations.

## Microservices

### 1. OrderService
- **Technology Used**: Spring Boot, REST API, JPA, MySQL
- **Description**: Handles order-related operations, providing CRUD functionalities via REST APIs.
- **Key Features**:
  - Create, update, and retrieve orders
  - Integration with other microservices
  - JPA for database operations

### 2. ServiceRegistry
- **Technology Used**: Spring Cloud Netflix Eureka
- **Description**: Acts as a Eureka server for service discovery.
- **Key Features**:
  - Registers and manages microservices
  - Facilitates load balancing and failover

### 3. ConfigServer
- **Technology Used**: Spring Cloud Config
- **Description**: Manages external configurations for applications.
- **Key Features**:
  - Centralized configuration management
  - Supports multiple environments
  - Uses Git for configuration storage

### 4. PaymentService
- **Technology Used**: Spring Boot, REST API, JPA, MySQL
- **Description**: Handles payment processing functionalities.
- **Key Features**:
  - Process payments
  - Manage transactions
  - REST APIs for payment operations

### 5. ProductService
- **Technology Used**: Spring Boot, REST API, JPA, MySQL
- **Description**: Manages product-related operations.
- **Key Features**:
  - CRUD operations for products
  - REST API for product management
  - JPA for database operations

## Getting Started

### Prerequisites
- Java 17
- Maven
- MySQL

### Setup Development Workspace
1. Install [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Install [Maven](https://maven.apache.org/install.html).
3. Install MySQL.
4. Clone this repository to your local machine.
5. Open the `pom.xml` file in your preferred IDE (e.g., IntelliJ IDEA).

### Running the Microservices
1. **OrderService**: `mvn spring-boot:run` (port: 8081)
2. **ServiceRegistry**: `mvn spring-boot:run` (port: 8761)
3. **ConfigServer**: `mvn spring-boot:run` (port: 8888)
4. **PaymentService**: `mvn spring-boot:run` (port: 8082)
5. **ProductService**: `mvn spring-boot:run` (port: 8083)

### Configuration
Ensure you have a MySQL instance running and update the `application.properties` or `application.yml` files in each microservice with your MySQL configurations.

## Contributing
Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.

## Contact
For any questions or support, please contact [your-email@example.com](mailto:your-email@example.com).

---

**Note**: This document is a brief summary. For more detailed information, please refer to the individual microservice's documentation within their respective directories.


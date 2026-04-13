# Banking Application - Java Spring Boot

A complete starter banking website built using Java 17, Spring Boot, Thymeleaf, REST APIs, Spring Data JPA, and H2 Database.

## Features
- Customer creation
- Account creation
- Deposit money
- Withdraw money
- View recent transactions
- REST APIs for banking operations
- H2 in-memory database
- Responsive dashboard UI

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Thymeleaf
- H2 Database
- HTML, CSS, JavaScript
- Maven

## REST APIs
### Customers
- `GET /api/customers`
- `POST /api/customers`

Example payload:
```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "phone": "9876543210"
}
```

### Accounts
- `GET /api/accounts`
- `POST /api/accounts`
- `POST /api/accounts/{accountId}/deposit`
- `POST /api/accounts/{accountId}/withdraw`
- `GET /api/accounts/{accountId}/transactions`

Create account payload:
```json
{
  "customerId": 1,
  "accountType": "SAVINGS",
  "initialDeposit": 1000.00
}
```

Transaction payload:
```json
{
  "amount": 100.00,
  "description": "Cash deposit"
}
```

## How to Run
```bash
mvn clean spring-boot:run
```

Open:
- App: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`

H2 JDBC URL:
```text
jdbc:h2:mem:bankdb
```

## GitHub Steps
```bash
git init
git add .
git commit -m "Initial banking application"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/banking-app.git
git push -u origin main
```

## Suggested Future Improvements
- Spring Security with JWT authentication
- Transfer between accounts
- Role-based admin/user access
- Swagger/OpenAPI documentation
- MySQL or PostgreSQL integration
- Dockerfile and Kubernetes deployment

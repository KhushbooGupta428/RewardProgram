# Customer Reward System

## Overview
The Customer Reward System is designed to calculate and manage reward points for customers based on their transactions. It provides an API to fetch monthly reward points for individual customers or all customers within a specified date range.

## Project Structure
The project is structured as follows:

- com.CustomerReward.customerController: Contains the `CustomerController` class which handles API requests.
- com.CustomerReward.dto: Contains data transfer objects such as `CustomerReward` and `RewardPoints`.
- com.CustomerReward.exception: Contains custom exceptions and global exception handling.
- com.CustomerReward.model: Contains entity classes such as `Customer` and `Transaction`.
- com.CustomerReward.repository: Contains repository interfaces for database operations.
- com.CustomerReward.service: Contains service classes for business logic.

## Technologies Used
- Java: Programming language used for development.
- Spring Boot: Framework used for building the application.
- Spring Data JPA: Used for database interactions.
- H2 Database: In-memory database used for development and testing.
- Maven: Build tool used for dependency management.
- REST API: Used for communication between client and server.

## Getting Started
### Prerequisites
- Java 11 or higher
- Maven
  ## API
  http://localhost:8080/api/v1/rewards?startDate=2025-01-01&endDate=2025-03-31
  http://localhost:8080/api/v1/rewards?customerId=1&startDate=2025-01-01&endDate=2025-03-31


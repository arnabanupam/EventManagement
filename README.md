# Event Management API

## Overview
This is a backend API built with **Spring Boot** for managing events, including event registration and participant management.

## Features
- **User Authentication**: Sign-up and login using JWT, with password hashing via bcrypt.
- **Event Management**: CRUD operations for events with attributes like name, description, date, location, and capacity.
- **Participant Management**: Users can register for events, view participants, and cancel registrations.
- **Database**: Uses **MongoDB** to store users, events, and registrations.
- **Error Handling**: Gracefully handles over-capacity registrations.

## Installation & Setup
### Prerequisites
- Java 17+
- Maven
- MongoDB
- Git

### Steps

Follow these steps to set up and run the Event Management application:

1. **Clone the repository**

    ```bash
    git clone https://github.com/arnabanupam/EventManagement.git
    cd EventManagement
    ```

2. **Configure MongoDB**

    Edit the `src/main/resources/application.properties` file to set your MongoDB connection:

    ```properties
    spring.data.mongodb.uri = mongodb://localhost:27017/event_management
    spring.data.mongodb.database = event_management
    ```

3. **Install dependencies**

    Run the following command to install all dependencies:

    ```bash
    mvn clean install
    ```

4. **Run the application**

    Start the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```


## API Endpoints

| Method  | Endpoint                  | Description                      |
|---------|---------------------------|----------------------------------|
| `POST`  | `/signup`                 | Register a new user             |
| `POST`  | `/login`                  | Login and get a JWT             |
| `POST`  | `/events`                 | Create a new event (JWT required) |
| `GET`   | `/events`                 | Fetch all events                |
| `POST`  | `/events/:id/register`    | Register for an event (JWT required) |
| `DELETE` | `/events/:id/register`   | Cancel registration (JWT required) |


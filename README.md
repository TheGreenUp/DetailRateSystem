# Java Client-Server Application with TCP/IP Sockets, MySQL, and JavaFX

This project is a client-server application built in Java, featuring TCP/IP socket communication, a MySQL database for persistent data storage, and a JavaFX-based user interface. The application uses serialized data transfer and implements role-based access control for secure functionality management.

## Features

- **Client-Server Communication**: Established over TCP/IP sockets for reliable data transmission.
- **Data Persistence**: MySQL database integration for storing and managing application data.
- **Role-Based Access Control**: Different functionalities available to users based on their assigned roles (e.g., admin, user, guest).
- **User Interface**: Responsive and intuitive UI developed with JavaFX for a better user experience.
- **Data Serialization**: Efficient data transfer through Java object serialization.

## Architecture

The application is divided into the following main components:

1. **Client**: 
   - Connects to the server via TCP/IP sockets.
   - Sends and receives serialized Java objects.
   - Provides an interactive UI for end-users built using JavaFX.

2. **Server**:
   - Listens for client connections and manages communication over TCP/IP.
   - Processes client requests, interacts with the MySQL database, and enforces role-based access.
   - Ensures secure data handling and controls access based on user roles.

3. **Database (MySQL)**:
   - Stores user data, roles, and other application data.
   - Enables persistent storage and retrieval of information across sessions.
   - Enforced through SQL queries within the server application.

## Installation and Setup

### Prerequisites

- **Java 8+**
- **MySQL Server**
- **JavaFX SDK**
- **Maven** (optional, for building the project)

## Usage
- Login: Users log in through the client UI. Access to features is determined by their role.
- Data Management: Users with appropriate roles can view, add, edit, or delete records in the database.
- Communication: The client and server exchange serialized data to ensure a secure, lightweight data transmission.

## Technologies Used
- **Java SE**: Core language and TCP/IP socket communication.
- **JavaFX**: User interface framework for the client.
- **MySQL**: Relational database for data storage.
- **Socket Programming**: TCP/IP-based client-server connection.
- **Serialization**: Efficient data exchange between client and server.

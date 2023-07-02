# astica(License Management System)

astica is a license management system implemented in Java using UDP communication.

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Description

The License Management System is a software application that enables the management and distribution of licenses for a specific product or software. It provides a centralized server-client architecture, where the server handles license management and the clients interact with the server to request and validate licenses.

This implementation uses Java and UDP communication protocols to facilitate communication between the server and clients. The server maintains a database of licenses and responds to client requests for license validation or acquisition.

## Features

- License generation: The system allows the server to generate and store licenses for a specific product or software.
- License validation: Clients can request license validation by sending license keys to the server, which will verify their authenticity and validity.
- License acquisition: Clients can request and acquire licenses from the server based on availability and specific requirements.

## Installation

1. Clone the repository to your local machine.
2. Ensure you have Java Development Kit (JDK) installed. This project requires Java 8 or higher.
3. Open a terminal or command prompt and navigate to the project directory.
4. Set up following databases: clientdb,serverdb and logindb with table name as userdb in mysqlworkbench
5. Change the username and password of your connection in all connection files in /src/database folder

## Usage

1. Start the server by running the server file in /src/UDP folder
2. Start the client by running the login file in /src/login folder
3. Follow the on-screen prompts on the client to interact with the server, such as requesting license validation or acquisition.



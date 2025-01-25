# ContactManagement

## Description

ContactManagement is a Java application for managing student contacts. It allows adding, editing, deleting, and searching for contacts using a graphical user interface (GUI) built with Swing. Features include managing personal information such as first name, last name, email, phone number, CIN, and CNE.

## Project Structure

The main file is located in the `com.esi.contactManagement` package. The `ContactManagementGUI.java` file contains the user interface and the logic for interacting with the student contacts.

### Main Package

The main file of the application is located in the following package:
package com.esi.contactManagement;

The `ContactManagementGUI.java` file defines the user interface, allowing users to:
- Add, delete, and update contacts.
- Search contacts by criteria (ID, name, email, etc.).
- Undo and redo actions.

### Features

- **Add Contact**: Opens a form to add new contacts.
- **Delete Contact**: Allows deleting a selected contact from the list.
- **Update Contact**: Allows updating a selected contact.
- **Undo/Redo**: Enables undoing and redoing actions.
- **Search**: Search contacts by criteria (ID, first name, last name, etc.).

## Installation and Running

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/C9White4/ContactManagement.git
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

Ensure you have the appropriate JDK (Java Development Kit) configured.

Run the ContactManagementGUI class via the main method to start the application.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagementGUI::new);
    }

Authors

    C9White4 - Lead Developer

License

This project is licensed under the MIT License. See the LICENSE file for more details.


This markdown format will work perfectly in GitHub, GitLab, or any other platform that supports markdown rendering. You can directly copy and paste it into the README file in your repository.

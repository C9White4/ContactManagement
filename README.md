# Contact Management System

Contact Management System is a Java-based application that provides a graphical user interface (GUI) for managing student information. It allows users to perform CRUD (Create, Read, Update, Delete) operations, as well as search and manage student data efficiently.

## Features

- **Add Students**: Add new students with details such as first name, last name, CIN, CNE, email, and phone number.
- **Update Students**: Update existing student records directly from the table.
- **Delete Students**: Delete selected student records from the database.
- **Search Students**: Search for students based on criteria like ID, first name, last name, CIN, CNE, or email.
- **Undo/Redo**: Undo or redo actions for better data management.
- **Table View**: Displays all student data in a tabular format with scrollable functionality.

## Technologies Used

- **Java Swing**: For creating the graphical user interface (GUI).
- **Java**: Core programming language used for application logic.

## Project Structure

- `package com.esi.contactManagement`
  - Main package containing the primary classes for managing the application logic and GUI.
- `import com.esi.student.Student`
  - Represents the `Student` entity with attributes such as ID, first name, last name, CIN, CNE, email, and phone number.

## How to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/C9White4/ContactManagement.git
    cd ContactManagement
    ```
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans).
3. Ensure the `com.esi.contactManagement` package is set as the main entry point.
4. Run the `main` method located in `ContactManagementGUI` class:
    ```java
    package com.esi.contactManagement;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagementGUI::new);
    }
    ```

## Usage

1. **Launch the Application**:
   Upon running the application, a window titled "Student Management" will appear.

2. **Add a Student**:
   - Click the **Add** button.
   - Fill in the student details in the dialog box.
   - Click **Save** to add the student.

3. **Update a Student**:
   - Select a student from the table.
   - Click the **Update** button.
   - Modify the details in the dialog box.
   - Click **Update** to save changes.

4. **Delete a Student**:
   - Select a student from the table.
   - Click the **Delete** button to remove the record.

5. **Search for Students**:
   - Select a criterion from the dropdown menu (e.g., ID, First Name).
   - Enter the search query in the text field.
   - Click **Search** to filter results.

6. **Undo/Redo Actions**:
   - Use the **Undo** and **Redo** buttons to navigate through previous and reverted actions.

## Contributions

Contributions are welcome! Feel free to fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any inquiries or issues, feel free to reach out:
- **Author**: Salah-Eddine Zitouni
- **Email**: salaheddinezitouni89@gmail.com


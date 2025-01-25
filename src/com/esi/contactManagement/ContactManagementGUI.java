package com.esi.contactManagement;
import com.esi.student.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class ContactManagementGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private ContactManagement contactManagement;
    public ContactManagementGUI() {
        contactManagement=new ContactManagement();
        frame=new JFrame("Student Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton=new JButton("Add");
        JButton deleteButton=new JButton("Delete");
        JButton updateButton=new JButton("Update");
        JButton undoButton=new JButton("Undo");
        JButton redoButton=new JButton("Redo");
        JTextField searchField=new JTextField(20);
        JComboBox<String> searchCriteria=new JComboBox<>(new String[]{"ID","First Name","Last Name","CIN","CNE","Email"});
        JButton searchButton=new JButton("Search");
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(updateButton);
        topPanel.add(undoButton);
        topPanel.add(redoButton);
        topPanel.add(searchCriteria);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        String[] columnNames={"ID","First Name","Last Name","CIN","CNE","Email","Phone Number"};
        tableModel=new DefaultTableModel(columnNames, 0);
        table=new JTable(tableModel);
        JScrollPane tableScrollPane=new JScrollPane(table);
        loadStudentsIntoTable();
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        addButton.addActionListener(e ->openAddStudentDialog());
        deleteButton.addActionListener(e ->deleteSelectedStudent());
        updateButton.addActionListener(e ->openUpdateStudentDialog());
        undoButton.addActionListener(e ->undoAction());
        redoButton.addActionListener(e ->redoAction());
        searchButton.addActionListener(e ->searchStudents(searchCriteria.getSelectedItem().toString(),searchField.getText()));
        frame.setVisible(true);
    }
    private void loadStudentsIntoTable() {
        tableModel.setRowCount(0);
        List<Student> students=contactManagement.getAllStudents();
        for (Student student:students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getCin(),
                    student.getCne(),
                    student.getEmail(),
                    student.getPhoneNumber()
            });
        }
    }
    private void openAddStudentDialog() {
        JDialog dialog=new JDialog(frame, "Add Student", true);
        dialog.setLayout(new GridLayout(7, 2));
        JTextField firstNameField=new JTextField();
        JTextField lastNameField=new JTextField();
        JTextField cinField=new JTextField();
        JTextField cneField=new JTextField();
        JTextField emailField=new JTextField();
        JTextField phoneNumberField=new JTextField();
        dialog.add(new JLabel("First Name:"));
        dialog.add(firstNameField);
        dialog.add(new JLabel("Last Name:"));
        dialog.add(lastNameField);
        dialog.add(new JLabel("CIN:"));
        dialog.add(cinField);
        dialog.add(new JLabel("CNE:"));
        dialog.add(cneField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Phone Number:"));
        dialog.add(phoneNumberField);
        JButton saveButton=new JButton("Save");
        dialog.add(saveButton);
        saveButton.addActionListener(e -> {
            try {
                String firstName=firstNameField.getText();
                String lastName=lastNameField.getText();
                String cin=cinField.getText();
                String cne=cneField.getText();
                String email=emailField.getText();
                int phoneNumber=Integer.parseInt(phoneNumberField.getText());
                Student newStudent=new Student(firstName, lastName, cin, cne, email, phoneNumber);
                contactManagement.addStudent(newStudent);
                loadStudentsIntoTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error adding student: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.pack();
        dialog.setVisible(true);
    }
    private void deleteSelectedStudent() {
        int selectedRow=table.getSelectedRow();
        if (selectedRow>=0) {
            int studentId=(int) tableModel.getValueAt(selectedRow, 0);
            contactManagement.deleteStudent(studentId);
            loadStudentsIntoTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a student to delete.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openUpdateStudentDialog() {
        int selectedRow=table.getSelectedRow();
        if (selectedRow>=0) {
            int studentId=(int) tableModel.getValueAt(selectedRow, 0);
            String firstName=(String) tableModel.getValueAt(selectedRow, 1);
            String lastName=(String) tableModel.getValueAt(selectedRow, 2);
            String cin=(String) tableModel.getValueAt(selectedRow, 3);
            String cne=(String) tableModel.getValueAt(selectedRow, 4);
            String email=(String) tableModel.getValueAt(selectedRow, 5);
            int phoneNumber=(int) tableModel.getValueAt(selectedRow, 6);
            JDialog dialog=new JDialog(frame, "Update Student", true);
            dialog.setLayout(new GridLayout(7, 2));
            JTextField firstNameField=new JTextField(firstName);
            JTextField lastNameField=new JTextField(lastName);
            JTextField cinField=new JTextField(cin);
            JTextField cneField=new JTextField(cne);
            JTextField emailField=new JTextField(email);
            JTextField phoneNumberField=new JTextField(String.valueOf(phoneNumber));
            dialog.add(new JLabel("First Name:"));
            dialog.add(firstNameField);
            dialog.add(new JLabel("Last Name:"));
            dialog.add(lastNameField);
            dialog.add(new JLabel("CIN:"));
            dialog.add(cinField);
            dialog.add(new JLabel("CNE:"));
            dialog.add(cneField);
            dialog.add(new JLabel("Email:"));
            dialog.add(emailField);
            dialog.add(new JLabel("Phone Number:"));
            dialog.add(phoneNumberField);
            JButton saveButton = new JButton("Update");
            dialog.add(saveButton);
            saveButton.addActionListener(e ->{
                try {
                    Student updatedStudent=new Student(
                            firstNameField.getText(),
                            lastNameField.getText(),
                            cinField.getText(),
                            cneField.getText(),
                            emailField.getText(),
                            Integer.parseInt(phoneNumberField.getText())
                    );
                    updatedStudent.setId(studentId);
                    contactManagement.updateStudent(studentId, updatedStudent);
                    loadStudentsIntoTable();
                    dialog.dispose();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(dialog, "Error updating student: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.pack();
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a student to update.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void undoAction() {
        contactManagement.undo();
        loadStudentsIntoTable();
    }
    private void redoAction() {
        contactManagement.redo();
        loadStudentsIntoTable();
    }
    private void searchStudents(String criteria,String query) {
        List<Student> students=contactManagement.searchStudents(criteria.toLowerCase(),query);
        tableModel.setRowCount(0);
        for (Student student:students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getCin(),
                    student.getCne(),
                    student.getEmail(),
                    student.getPhoneNumber()
            });
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagementGUI::new);
    }
}
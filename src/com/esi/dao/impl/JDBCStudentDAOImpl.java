package com.esi.dao.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.esi.dao.IStudentDAO;
import com.esi.student.Student;
import com.esi.util.DatabaseConnection;
public class JDBCStudentDAOImpl  implements IStudentDAO{
    public void addStudent(Student s) {
        String checkQuery="SELECT COUNT(*) FROM Students WHERE id = ?";
        String insertQuery="INSERT INTO Students (id,firstName,lastName,cin,cne,email,phoneNumber) VALUES (?,?,?,?,?,?,?)";
        String updateQuery="UPDATE Students SET firstName=?,lastName=?,cin=?,cne=?,email=?,phoneNumber=? WHERE id=?";
        try (PreparedStatement checkStatement=DatabaseConnection.getInstance().getConnection().prepareStatement(checkQuery)) {
            checkStatement.setInt(1, s.getId());
            ResultSet resultSet=checkStatement.executeQuery();
            resultSet.next();
            int count=resultSet.getInt(1);
            if(count>0) {
                try (PreparedStatement updateStatement=DatabaseConnection.getInstance().getConnection().prepareStatement(updateQuery)) {
                    updateStatement.setString(1,s.getFirstName());
                    updateStatement.setString(2,s.getLastName());
                    updateStatement.setString(3,s.getCin());
                    updateStatement.setString(4,s.getCne());
                    updateStatement.setString(5,s.getEmail());
                    updateStatement.setInt(6,s.getPhoneNumber());
                    updateStatement.setInt(7,s.getId());
                    updateStatement.executeUpdate();
                }
            }else {
                try (PreparedStatement insertStatement=DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, s.getId());
                    insertStatement.setString(2, s.getFirstName());
                    insertStatement.setString(3, s.getLastName());
                    insertStatement.setString(4, s.getCin());
                    insertStatement.setString(5, s.getCne());
                    insertStatement.setString(6, s.getEmail());
                    insertStatement.setInt(7, s.getPhoneNumber());
                    insertStatement.executeUpdate();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while adding or updating student: "+e.getMessage());
        }
    }
    public void deleteStudent(Student s) {
        String checkQuery="SELECT COUNT(*) FROM Students WHERE id = ?";
        String query="DELETE FROM Students WHERE id=?";
        try (PreparedStatement checkStatement=DatabaseConnection.getInstance().getConnection().prepareStatement(checkQuery)) {
            checkStatement.setInt(1, s.getId());
            ResultSet resultSet=checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count >0) {
                try (PreparedStatement preparedStatement=DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
                    preparedStatement.setInt(1, s.getId());
                    preparedStatement.executeUpdate();
                }
            }else {
                System.out.println("No student found with ID: "+s.getId());
            }
        }catch (SQLException e) {
            System.err.println("Error during student deletion: "+e.getMessage());
        }
    }
    public List<Student> getAllStudents(){
        List<Student> students=new ArrayList<>();
        String query="SELECT * FROM Students";
        try (PreparedStatement statement=DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                String fN=resultSet.getString("firstName");
                String lN=resultSet.getString("lastName");
                String cin=resultSet.getString("cin");
                String cne=resultSet.getString("cne");
                String email=resultSet.getString("email");
                int phN=resultSet.getInt("phoneNumber");
                Student U=new Student(fN,lN,cin,cne,email,phN);
                U.setId(id);
                students.add(U);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
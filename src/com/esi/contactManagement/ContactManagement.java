package com.esi.contactManagement;
import com.esi.dao.IStudentDAO;
import com.esi.dao.impl.JDBCStudentDAOImpl;
import com.esi.dao.impl.TextFileStudentDAOImpl;
import com.esi.student.Student;
import java.util.ArrayList;
import java.util.List;
public class ContactManagement {
    private List<Student> students;
    private List<List<Student>> history;
    private int Index;
    private final IStudentDAO jdbcDAO;
    private final IStudentDAO textFileDAO;
    public ContactManagement() {
        students=new ArrayList<>();
        history=new ArrayList<>();
        Index=-1;
        jdbcDAO=new JDBCStudentDAOImpl();
        textFileDAO=new TextFileStudentDAOImpl();
        students=jdbcDAO.getAllStudents();
        saveState();
    }
    public void addStudent(Student student) {
        try {
            students.add(student);
            jdbcDAO.addStudent(student);
            textFileDAO.addStudent(student);
            saveState();
        } catch (Exception e) {
            System.err.println("Error adding student: "+e.getMessage());
        }
    }
    public void updateStudent(int id, Student updatedStudent) {
        try {
            for (int i=0;i<students.size();i++) {
                if (students.get(i).getId()==id) {
                    students.set(i,updatedStudent);
                    jdbcDAO.addStudent(updatedStudent);
                    textFileDAO.addStudent(updatedStudent);
                    saveState();
                    return;
                }
            }
            System.out.println("Student with ID "+id+" not found.");
        } catch (Exception e) {
            System.err.println("Error updating student: "+e.getMessage());
        }
    }
    public void deleteStudent(int id) {
        try {
            students.removeIf(student->student.getId()==id);
            Student studentToDelete=new Student();
            studentToDelete.setId(id);
            jdbcDAO.deleteStudent(studentToDelete);
            textFileDAO.deleteStudent(studentToDelete);
            saveState();
        } catch (Exception e) {
            System.err.println("Error deleting student: "+e.getMessage());
        }
    }
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    public List<Student> searchStudents(String attribute,String value) {
        List<Student> result=new ArrayList<>();
        for (Student student:students) {
            switch (attribute.toLowerCase()) {
                case "id":
                    if (String.valueOf(student.getId()).equals(value)) {
                        result.add(student);
                    }
                    break;
                case "firstname":
                    if (student.getFirstName().equalsIgnoreCase(value)) {
                        result.add(student);
                    }
                    break;
                case "lastname":
                    if (student.getLastName().equalsIgnoreCase(value)) {
                        result.add(student);
                    }
                    break;
                case "cin":
                    if (student.getCin().equalsIgnoreCase(value)) {
                        result.add(student);
                    }
                    break;
                case "cne":
                    if (student.getCne().equalsIgnoreCase(value)) {
                        result.add(student);
                    }
                    break;
                case "email":
                    if (student.getEmail().equalsIgnoreCase(value)) {
                        result.add(student);
                    }
                    break;
                case "phonenumber":
                    if (String.valueOf(student.getPhoneNumber()).equals(value)) {
                        result.add(student);
                    }
                    break;
                default:
                    System.out.println("Invalid attribute for search: "+attribute);
            }
        }
        return result;
    }
    private void saveState() {
        if (Index<history.size() - 1) {
            history=history.subList(0,Index+1);
        }
        history.add(new ArrayList<>(students));
        Index++;
    }
    public void undo() {
        if (Index>0) {
            Index--;
            students=new ArrayList<>(history.get(Index));
        } else {
            System.out.println("No more undo operations available.");
        }
    }
    public void redo() {
        if (Index<history.size() - 1) {
            Index++;
            students=new ArrayList<>(history.get(Index));
        } else {
            System.out.println("No more redo operations available");
        }
    }
}
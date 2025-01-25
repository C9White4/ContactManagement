package com.esi.dao;
import java.util.List;
import com.esi.student.Student;
public interface IStudentDAO {
    public void addStudent(Student s);
    public void deleteStudent(Student s);
    public List<Student> getAllStudents();

}
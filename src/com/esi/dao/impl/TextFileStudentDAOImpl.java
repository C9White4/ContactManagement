package com.esi.dao.impl;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import com.esi.dao.IStudentDAO;
import com.esi.student.Student;
public class TextFileStudentDAOImpl implements IStudentDAO {
    private static final String FILE_TXT=Paths.get("Database&Files", "Students.txt").toString();
    public void addStudent(Student s) {
        List<Student> students=getAllStudents();
        boolean studentExists=false;
        for (Student student:students) {
            if (student.getId()==s.getId()) {
                student.setFirstName(s.getFirstName());
                student.setLastName(s.getLastName());
                student.setCin(s.getCin());
                student.setCne(s.getCne());
                student.setEmail(s.getEmail());
                student.setPhoneNumber(s.getPhoneNumber());
                studentExists=true;
                break;
            }
        }
        if (!studentExists) {
            students.add(s);
        }
        try (FileWriter writer=new FileWriter(FILE_TXT, false)) {
            for (Student student:students) {
                writer.write(student.getId()+","+student.getFirstName()+","+student.getLastName()+","+student.getCin()+","+student.getCne()+","+student.getEmail()+","+student.getPhoneNumber()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while adding/updating student in file: "+e.getMessage());
        }
    }
    public void deleteStudent(Student s) {
        List<Student> students=getAllStudents();
        students.removeIf(student -> student.getId()==s.getId());
        try (FileWriter writer=new FileWriter(FILE_TXT, false)) {
            for (Student student : students) {
                writer.write(student.getId()+","+student.getFirstName()+","+student.getLastName()+","+student.getCin()+","+student.getCne()+","+student.getEmail()+","+student.getPhoneNumber()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while deleting student from the file: "+e.getMessage());
        }
    }
    public List<Student> getAllStudents() {
        List<Student> students=new ArrayList<>();
        try (FileReader reader=new FileReader(FILE_TXT);
             Scanner scanner=new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine().trim();
                line=line.replace("\uFEFF", "");
                String[] data=line.split(",");
                if (data.length==7) {
                    try {
                        int id=Integer.parseInt(data[0].trim());
                        String firstName=data[1].trim();
                        String lastName=data[2].trim();
                        String cin=data[3].trim();
                        String cne=data[4].trim();
                        String email=data[5].trim();
                        int phoneNumber=Integer.parseInt(data[6].trim());
                        Student student=new Student(firstName,lastName,cin,cne,email,phoneNumber);
                        student.setId(id);
                        students.add(student);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid line: "+line);
                    }
                } else {
                    System.err.println("Skipping invalid line: "+line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading students from the file: "+e.getMessage());
        }
        return students;
    }
}
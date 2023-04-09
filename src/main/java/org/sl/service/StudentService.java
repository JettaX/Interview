package org.sl.service;

import org.sl.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(long studentId);

    Optional<Student> getStudentById(long studentId);

    List<Student> getAllStudents();
}

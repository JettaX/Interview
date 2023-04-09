package org.sl.service;

import lombok.RequiredArgsConstructor;
import org.sl.model.Student;
import org.sl.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        if (student.getId() != null) {
            Optional<Student> existingStudent = studentRepository.findById(student.getId());
            if (existingStudent.isPresent()) {
                existingStudent.get().setName(student.getName());
                existingStudent.get().setAge(student.getAge());
                studentRepository.save(existingStudent.get());
            } else {
                throw new RuntimeException("Student not found for update. id - " + student.getId());
            }
        } else {
            throw new RuntimeException("Student id is null. Cannot update student without id.");
        }
    }

    @Override
    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Optional<Student> getStudentById(long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

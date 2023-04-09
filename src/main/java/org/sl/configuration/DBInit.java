package org.sl.configuration;

import org.sl.model.Student;
import org.sl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBInit {

    @Autowired
    public DBInit(StudentService studentService) {
        studentService.addStudent(Student.builder()
                .age(14)
                .name("Mark")
                .build());

        studentService.addStudent(Student.builder()
                .age(17)
                .name("Angelina")
                .build());

        studentService.addStudent(Student.builder()
                .age(17)
                .name("Sasha")
                .build());

        studentService.addStudent(Student.builder()
                .age(16)
                .name("Marina")
                .build());

        studentService.addStudent(Student.builder()
                .age(17)
                .name("Fedor")
                .build());
    }
}

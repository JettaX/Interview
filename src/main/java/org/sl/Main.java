package org.sl;

import org.sl.DTO.StudentDtoImpl;
import org.sl.entity.Student;
import org.sl.util.StudentCreator;

public class Main {
    public static void main(String[] args) {
        StudentDtoImpl studentDto = new StudentDtoImpl();

        System.out.println(studentDto.size());

        studentDto.saveAll(StudentCreator.create(1000));

        System.out.println(studentDto.size());

        studentDto.deleteById(1);

        System.out.println(studentDto.size());
        System.out.println(studentDto.findById(1));

        studentDto.deleteById(2);

        System.out.println(studentDto.size());
        System.out.println(studentDto.findById(2));

        System.out.println(studentDto.findByMark(5));
        System.out.println(studentDto.findByMark(2));
    }
}
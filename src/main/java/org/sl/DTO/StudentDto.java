package org.sl.DTO;

import org.sl.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDto {
    void save(Student student);
    void saveAll(List<Student> students);
    Optional<Student> findById(long id);
    List<Student> findByMark(int mark);
    List<Student> findAll();
    boolean deleteById(long id);
    long size();
}

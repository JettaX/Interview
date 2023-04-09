package org.sl.controller;

import lombok.RequiredArgsConstructor;
import org.sl.model.Student;
import org.sl.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/index")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/index/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        studentService.deleteStudent(id);
        return "redirect:/index";
    }

    @GetMapping("/index/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            model.addAttribute("path", "/index/update");
            model.addAttribute("isReadOnlyId", true);
        } else {
            return "redirect:/index";
        }
        return "edit";
    }

    @GetMapping("/index/create")
    public String createStudent(Model model) {
        model.addAttribute("isReadOnlyId", false);
        model.addAttribute("path", "/index/create");
        model.addAttribute("student", new Student());
        return "edit";
    }

    @PostMapping("/index/create")
    public String createStudent(Student student, Model model) {
        if (isValid(student)) {
            studentService.addStudent(student);
        } else {
            return "redirect:/index/create";
        }
        return "redirect:/index";
    }

    @PostMapping("/index/update")
    public String updateStudent(Student student, Model model) {
        if (isValid(student)) {
            studentService.updateStudent(student);
        } else {
            return "redirect:/index/edit/" + student.getId();
        }
        return "redirect:/index";
    }

    private boolean isValid(Student student) {
        if (!student.getName().isBlank() && student.getAge() > 0 && student.getAge() < 150) {
            return true;
        }
        return false;
    }


}

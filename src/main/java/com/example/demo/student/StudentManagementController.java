package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")  // hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'), hasAnyAuthority('permission')
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @DeleteMapping(path = "{studentId}")
//    @PreAuthorize("hasAuthority('course:write')")
    public void deleteStudentById(@PathVariable("studentId") int studentId) {
        System.out.println(studentId);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        System.out.printf("%s %s", studentId, student);
    }
}

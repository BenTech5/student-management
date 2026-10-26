package com.school.student_management.controller;

import com.school.student_management.model.Student;
import com.school.student_management.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/students")
    public class StudentViewController {

        private final StudentService studentService;

        public StudentViewController(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping
        public String showStudents(Model model) {
            model.addAttribute("students", studentService.getAllStudents());
            return "students";
        }
        @PostMapping("/add")
        public String addStudent(@ModelAttribute Student student, Model model) {
            try {
                studentService.addStudent(student);
                model.addAttribute("success", "Student added successfully!");
            } catch (Exception e) {
                model.addAttribute("error", "Error: " + e.getMessage());
            }
            model.addAttribute("students", studentService.getAllStudents());
            return "students";
        }
        @PostMapping("/delete/{id}")
        public String deleteStudent(@PathVariable Long id) {
            studentService.deleteStudent(id);
            return "redirect:/students";
        }
    }


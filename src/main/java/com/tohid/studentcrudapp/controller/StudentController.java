package com.tohid.studentcrudapp.controller;

import com.tohid.studentcrudapp.dao.StudentDao;
import com.tohid.studentcrudapp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mvc/students")
public class StudentController {
        @Autowired
        private StudentDao studentDao;

        @GetMapping
        public List<Student> getAllStudents() {
            return studentDao.findAll();
        }

        @GetMapping("/{id}")
        public Student getStudentById(@PathVariable int id) {
            return studentDao.findById(id).orElse(null);
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student) {
            return studentDao.save(student);
        }

        @PutMapping("/{id}")
        public Student updateStudent(@PathVariable int id, @RequestBody Student updateStudent) {
            Student existStudent = studentDao.findById(id).orElse(null);
            if (existStudent != null) {
                existStudent.setId(updateStudent.getId());
                existStudent.setName(updateStudent.getName());
                existStudent.setAge(updateStudent.getAge());
                existStudent.setCourse(updateStudent.getCourse());
                existStudent.setAddress(updateStudent.getAddress());
                return studentDao.save(existStudent);
            }
            return null;
        }

        @DeleteMapping("/{id}")
        public void deleteStudent(@PathVariable int id) {
            studentDao.deleteById(id);
        }

    }

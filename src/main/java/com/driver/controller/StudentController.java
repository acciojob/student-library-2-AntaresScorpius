package com.driver.controller;

import com.driver.models.Student;
import com.driver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
public class StudentController {

    //Add required annotations
    @Autowired
    StudentService studentService;

    @GetMapping("student/studentByEmail")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam("email") String email){
        System.out.println("Get Student by email = " + email);
        Student student = studentService.getDetailsByEmail(email);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //Add required annotations
    @GetMapping("student/studentById")
    public ResponseEntity<Student> getStudentById(@RequestParam("id") int id){
        System.out.println("Get student by id = " + id);
        Student student = studentService.getDetailsById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("student")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        System.out.println("Create student = " + student);
        studentService.createStudent(student);
        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    @PutMapping("student")
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
        System.out.println("Put Mapping student = " + student);
        studentService.updateStudent(student);
        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    @DeleteMapping("student")
    public ResponseEntity<String> deleteStudent(@RequestParam("id") int id){
        System.out.println("Delete student by id = " + id);
        studentService.deleteStudent(id);
        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }

}

package com.studentDB.studentSpringBootApplication.controller;


import com.studentDB.studentSpringBootApplication.model.student;
import com.studentDB.studentSpringBootApplication.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class studentController {

    @Autowired
    private studentService studentService;

    @PostMapping("/addition-of-students")
    List<student> add(@RequestBody student studentList){
        return studentService.add(studentList);
    }

    @GetMapping("/Search-Student-By-Name/{name}")
    student get(@PathVariable String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/Search-Student-By-Year/{year}")
    List<student> getByFirstYear(){
        return studentService.getStudentsByFirstYear();
    }
}

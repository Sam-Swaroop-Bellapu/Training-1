package com.Validation.lecturerDB.controller;

import com.Validation.lecturerDB.model.LecturerModel;
import com.Validation.lecturerDB.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.*;

@RestController
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @PostMapping("/new-lecturer")
    List<LecturerModel> add(@Valid @RequestBody LecturerModel newLecturer){
        return lecturerService.addLecturer(newLecturer);
    }

    @GetMapping("/get/{id}")
    LecturerModel get(@PathVariable Integer id){
        return  lecturerService.get(id);
    }
}

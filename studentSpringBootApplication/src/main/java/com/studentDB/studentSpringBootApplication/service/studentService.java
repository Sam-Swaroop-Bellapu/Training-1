package com.studentDB.studentSpringBootApplication.service;


import com.studentDB.studentSpringBootApplication.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class studentService {


    public List<student> studentsList;

    private List students;

//    student student1=new student("100","Sam","IT","first",2000,"3/4/21","M1","Math");


    public List<student> add(student studentModel){
        if (studentsList==null){
            studentsList=new ArrayList<>();
        }
        studentsList.add(studentModel);

        return studentsList;
    }


    public  List<student> getStudentsByFirstYear(){
        List<student> first=studentsList.stream().filter(x->x.getYear().equals("first")).collect(Collectors.toList());
        return first;
    }

    public student getStudentsByRno(String rno){

        Optional<student> first= studentsList.stream().filter(x->x.getRno().equals(rno)).findFirst();

        return first.isPresent()?first.get():null;
    }









}

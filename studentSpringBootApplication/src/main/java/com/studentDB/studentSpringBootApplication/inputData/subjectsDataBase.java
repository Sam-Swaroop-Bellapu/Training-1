package com.studentDB.studentSpringBootApplication.inputData;

import com.studentDB.studentSpringBootApplication.model.Subjects;

import java.util.ArrayList;
import java.util.List;

public class subjectsDataBase {
    public List subjects(){
        List<Subjects> sub= new ArrayList<>();
        Subjects sub1= new Subjects("subject1","English");
        Subjects sub2=new Subjects("subject2","Physics");
//        Subjects sub3=new Subjects("subject3","Maths");

        sub.add(sub1);
        sub.add(sub2);

        return sub;
    }
}

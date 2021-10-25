package com.studentDB.studentSpringBootApplication.inputData;

import com.studentDB.studentSpringBootApplication.model.StudentFee;
import com.studentDB.studentSpringBootApplication.model.StudentMarks;
import com.studentDB.studentSpringBootApplication.model.Subjects;
import com.studentDB.studentSpringBootApplication.model.student;

import java.util.ArrayList;
import java.util.List;

public class studentDataBase {
    public List students(){
        List<student> stu=new ArrayList<>();
        student stu1=new student("roll1","abc","CSE","first");
        student stu2=new student("roll2","def","ECE","second");
        student stu3=new student("roll3","ghi","EEE","first");
        student stu4=new student("roll4","jkl","MME","second");
        student stu5=new student("roll5","mno","IT","first");


        stu.add(stu1);
        stu.add(stu2);
        stu.add(stu3);
        stu.add(stu4);
        stu.add(stu5);

        return stu;

    }

}

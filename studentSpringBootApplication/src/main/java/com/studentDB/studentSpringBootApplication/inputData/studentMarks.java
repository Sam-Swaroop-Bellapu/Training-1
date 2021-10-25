package com.studentDB.studentSpringBootApplication.inputData;

import com.studentDB.studentSpringBootApplication.model.StudentMarks;

import java.util.ArrayList;
import java.util.List;

public class studentMarks {
    public List studentsmarks(){
        List<StudentMarks> smarks=new ArrayList<>();

        StudentMarks smarks1=new StudentMarks("roll1","first","subject1","80");
        StudentMarks smarks2=new StudentMarks("roll1","first","subject2","76");

        StudentMarks smarks3=new StudentMarks("roll2","second","subject1","84");
        StudentMarks smarks4=new StudentMarks("roll2","second","subject2","89");

        StudentMarks smarks5=new StudentMarks("roll3","first","subject1","85");
        StudentMarks smarks6=new StudentMarks("roll3","first","subject2","90");

        StudentMarks smarks7=new StudentMarks("roll4","second","subject1","94");
        StudentMarks smarks8=new StudentMarks("roll4","second","subject2","92");

        StudentMarks smarks9=new StudentMarks("roll5","first","subject1","98");
        StudentMarks smarks10=new StudentMarks("roll5","first","subject2","99");

        smarks.add(smarks1);
        smarks.add(smarks2);
        smarks.add(smarks3);
        smarks.add(smarks4);
        smarks.add(smarks5);
        smarks.add(smarks6);
        smarks.add(smarks7);
        smarks.add(smarks8);
        smarks.add(smarks9);
        smarks.add(smarks10);

        return smarks;
    }
}

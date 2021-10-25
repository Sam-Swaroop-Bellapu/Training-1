package com.studentDB.studentSpringBootApplication.inputData;

import com.studentDB.studentSpringBootApplication.model.StudentFee;

import java.util.ArrayList;
import java.util.List;

public class studentFeeDataBase {

    public List studentFees(){
        List<StudentFee> studentFees = new ArrayList<>();
        StudentFee sf1=new StudentFee("roll1",2000,"1/10/21");
        StudentFee sf2=new StudentFee("roll2",4000,"2/10/21");
        StudentFee sf3=new StudentFee("roll3",8000,"3/12/21");
        StudentFee sf4=new StudentFee("roll4",9000,"4/12/21");
        StudentFee sf5=new StudentFee("roll5",10000,"5/12/21");

        studentFees.add(sf1);
        studentFees.add(sf2);
        studentFees.add(sf3);
        studentFees.add(sf4);
        studentFees.add(sf5);

        return studentFees;
    }
}

package com.studentDB.studentSpringBootApplication.model;



//StudentMarks(rno, year, subjectNo, marks)
public class StudentMarks {

    private String rno;
    private  String year;
    private String subjectNo;
    private String marks;

    public StudentMarks(String rno, String year, String subjectNo, String marks) {
        this.rno = rno;
        this.year = year;
        this.subjectNo = subjectNo;
        this.marks = marks;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}

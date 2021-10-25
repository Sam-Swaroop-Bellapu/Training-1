package com.studentDB.studentSpringBootApplication.model;

public class student {
    private String rno;
    private String name;
    private String branch;
    private String year;

    public student(String rno, String name, String branch, String year) {
        this.rno = rno;
        this.name = name;
        this.branch = branch;
        this.year = year;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

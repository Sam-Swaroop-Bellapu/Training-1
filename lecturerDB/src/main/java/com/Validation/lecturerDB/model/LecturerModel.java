package com.Validation.lecturerDB.model;

public class LecturerModel {
    @Min(value = 1, message = "id should have min value of 1")
    @Max(value = 10, message = "id should have max value as 10")
    private int id;
    @NotNull(message = "Name should not be null")
    private String name;
    @Pattern(regexp="^[a-zA-Z0-9]{3}",message="designation length must be 3")
    private String department;

    public LecturerModel(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

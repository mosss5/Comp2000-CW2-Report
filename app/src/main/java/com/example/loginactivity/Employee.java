package com.example.loginactivity;

public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private float salary;
    private String joiningDate;
    private int leaves;

    // Constructor
    public Employee(String firstName, String lastName, String email, String department,
                    float salary, String joiningDate, int leaves) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.leaves = leaves;
        System.out.println("Employee Info: " + firstName + " " + lastName + ", " + email);
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    public String getJoiningDate() { return joiningDate; }
    public void setJoiningDate(String joiningDate) { this.joiningDate = joiningDate; }

    public int getLeaves() { return leaves; }
    public void setLeaves(int leaves) { this.leaves = leaves; }
}

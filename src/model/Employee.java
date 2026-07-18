/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/** 
 *
 * @author ACER
 */
public class Employee {
    private String id;
    private String name;
    private String role;
    private double salary;
    private int workingDays;
    private double bonus;
    private String status;

    public Employee() {
    }

    public Employee(String id, String name, String role, double salary, int workingDays, double bonus, String status) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.workingDays = workingDays;
        this.bonus = bonus;
        this.status = status;
    }
    
    public double getTotalSalary(){
        return (this.salary * this.workingDays)+ this.bonus;
    }
    
    public void showPayroll(){
        System.out.printf("| %-6s | %-18s | %-12s | %10.2f | %12d | %10.2f | %12.2f |\n", 
            id, name, role, salary, workingDays, bonus, getTotalSalary());
    }
    public void showInfo(){
        System.out.printf("| %-6s | %-18s | %-12s | %10.2f | %12d | %10.2f | %-10s |\n", 
            id, name, role, salary, workingDays, bonus, status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String toFileString() {
    return id + ", " + name + ", " + role + ", " + salary + ", " + workingDays + ", " + bonus + ", " + status;
    }
    
    
}

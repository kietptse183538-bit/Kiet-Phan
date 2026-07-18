/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

/**
 *
 * @author ACER
 */
public class Manager implements IEmployeeManager {

    private List<Employee> listEmployee = new ArrayList<>();
    private boolean hasChange = false;

    private Employee getEmployeeById(String id) {
        for (Employee employee : listEmployee) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

  @Override
public void loadFromFile() {
    try {
        listEmployee.clear();
        List<String> lines = Files.readAllLines(Paths.get("employees.txt"));
        int successCount = 0;

        for (String line : lines) {
            line = line.replace("\uFEFF", "").trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] p = line.split("\\s*,\\s*");

            if (p.length == 7) {
                try {
                    String id = p[0].trim();
                    String name = p[1].trim();
                    String role = p[2].trim();
                    double salary = Double.parseDouble(p[3].trim());
                    int workingDays = Integer.parseInt(p[4].trim());
                    double bonus = Double.parseDouble(p[5].trim());
                    String status = p[6].trim();

                    listEmployee.add(new Employee(id, name, role, salary, workingDays, bonus, status));
                    successCount++;

                } catch (Exception e) {
                    System.out.println("Skipping invalid line data: " + line);
                    System.out.println("-> Error reason (Debug): " + e.getMessage());
                }
            } else {
                System.out.println("-> Skipping invalid column count (Expected 7 columns): " + line);
            }
        }
        System.out.println("Load successfully! Successfully loaded " + successCount + " employee(s) into the list.");

    } catch (Exception e) {
        System.out.println("File employees.txt does not exist or cannot be read!!!");
    }
    hasChange = false;
}

    @Override
    public void addNewEmployee() {
        String id, name, role, status;
        double salary, bonus;
        int workingDays;
        while (true) {
            id = Inputter.inputRequired("Enter Employee ID (E00X): ", Inputter.ID_VALIDATE);
            if (getEmployeeById(id) != null) {
                System.out.println("This ID is alredy existed!!!");
            } else {
                break;
            }
        }
        name = Inputter.inputRequired("Enter name: ", Inputter.NAME_VALIDATE);
        role = Inputter.inputRequired("Enter role (Developer, Tester, Manager, HR): ", Inputter.ROLE_VALIDATE);
        salary = Inputter.inputDouble("Enter Salary(Must be positive number): ", 0);
        workingDays = Inputter.inputInt("Enter Working Days: ", 0, 26);
        bonus = Inputter.inputDouble("Enter bonus: ", -0.1);
        status = Inputter.inputRequired("Enter Status (inactive or active): ", Inputter.STATUS_VALIDATE);

        Employee newEmployee = new Employee(id, name, role, salary, workingDays, bonus, status);
        listEmployee.add(newEmployee);
        System.out.println("Add New Employee Successfully");
        
        hasChange = true;
    }

    @Override
    public void updateEmployee() {
        String id, role, status;
        double salary, bonus;
        id = Inputter.inputRequired("Enter Employee ID: ", Inputter.ID_VALIDATE);
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            System.out.println("This employee does not existed!!!");
            return;
        }
        role = Inputter.inputOptional("Enter new role(Developer, Tester, Manager, HR): ", Inputter.ROLE_VALIDATE);
        if (!role.isEmpty()) {
            employee.setRole(role);
        }
        salary = Inputter.inputOptionalDouble("Enter new salary (Must be positive number): ", 0);
        if (salary != -1.0) {
            employee.setSalary(salary);
        }
        bonus = Inputter.inputOptionalDouble("Enter new bonus: ", -0.1);
        if (bonus != -1.0) {
            employee.setBonus(bonus);
        }
        status = Inputter.inputOptional("Enter new status (inactive or active): ", Inputter.STATUS_VALIDATE);
        if (!status.isEmpty()) {
            employee.setStatus(status);
        }
        System.out.println("Update successfully!!!");
        
        hasChange = true;
    }

    @Override
    public void removeEmployee() {
        String id = Inputter.inputRequired("Enter Employee ID: ", Inputter.ID_VALIDATE);
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            System.out.println("This employee does not existed!!!");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-12s | %-10s | %-12s | %-10s | %-10s |\n",
                "ID", "Name", "Role", "Salary", "Working Days", "Bonus", "Status");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        employee.showInfo();
        System.out.println("-----------------------------------------------------------------------------------------------------");

        boolean confirm = Inputter.inputYesNo("Are you sure you want to delete this employee? (Y/N): ");
        if (confirm) {
            listEmployee.remove(employee);
            System.out.println("Remove successfully!!!");
        } else {
            System.out.println("Remove action cancelled.");
        }

        hasChange = true;
    }

    @Override
    public void searchEmployee() {
        String keyword = Inputter.inputRequired("Enter keyword to search (ID, Name, Role, Status): ", Inputter.NAME_VALIDATE).toLowerCase();
        List<Employee> resultList = new ArrayList<>();

        for (Employee employee : listEmployee) {
            if (employee.getId().toLowerCase().contains(keyword)
                    || employee.getName().toLowerCase().contains(keyword)
                    || employee.getRole().toLowerCase().contains(keyword)
                    || employee.getStatus().toLowerCase().contains(keyword)) {

                resultList.add(employee);
            }
        }
        if (resultList.isEmpty()) {
            System.out.println("Cannot find with this keyword");
        } else {
            System.out.println("Found employees");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.printf("| %-6s | %-18s | %-12s | %-10s | %-12s | %-10s | %-10s |\n",
                    "ID", "Name", "Role", "Salary", "Working Days", "Bonus", "Status");
            System.out.println("-----------------------------------------------------------------------------------------------------");

            for (Employee emp : resultList) {
                emp.showInfo();
            }
            System.out.println("-----------------------------------------------------------------------------------------------------");
        }
        
    }

    @Override
    public void calculatePayroll() {
        if (listEmployee.isEmpty()) {
            System.out.println("Employee List is empty!!!");
            return;
        }
        int activeCount = 0;
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-12s | %-10s | %-12s | %-10s | %-12s |\n",
                "ID", "Name", "Role", "Base Salary", "Working Days", "Bonus", "Total Salary");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Employee employee : listEmployee) {
            if (employee.getStatus().equalsIgnoreCase("active")) {
                employee.showPayroll();
                activeCount++;
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        if (activeCount == 0) {
            System.out.println("There are currently no active employees to calculate payroll!");
        } else {
            System.out.println("Calculated Successfully");
        }

    }

    @Override
    public void displayEmployeeList() {
        if (listEmployee.isEmpty()) {
            System.out.println("Employee List is empty!!!");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-12s | %-10s | %-12s | %-10s | %-10s |\n",
                "ID", "Name", "Role", "Salary", "Working Days", "Bonus", "Status");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for (Employee employee : listEmployee) {
            employee.showInfo();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println(listEmployee.size() + " in the list");

    }

    @Override
    public void saveToFile() {
        if (listEmployee.isEmpty()) {
            System.out.println("Employee list is empty! Nothing to save.");
            return;
        }
        try (java.io.PrintWriter pw = new java.io.PrintWriter("employees.txt")) {
            for (Employee emp : listEmployee) {
                pw.println(emp.toFileString());
            }

            System.out.println("Save data to employees.txt successfully!!!");

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
        hasChange = false;
    }

    @Override
    public void quitProgram() {
        if (hasChange) { 
        boolean confirmSave = Inputter.inputYesNo("Data has changed! Do you want to save to file before exiting? (Y/N): ");
        if (confirmSave) {
            saveToFile();
        }
    }
    System.out.println("Thank you for using Employee Payroll Management System. Goodbye!");
    System.exit(0);
    }
}

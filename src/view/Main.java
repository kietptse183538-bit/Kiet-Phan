
package view;

import controller.Inputter;
import controller.Manager;

public class Main {
    public static void menu(){
        System.out.println("========= EMPLOYEES MANAGEMENT =========");
        System.out.println("1. Load employee data from file");
        System.out.println("2. Add a new employee");
        System.out.println("3. Update employee information");
        System.out.println("4. Remove employee by ID");
        System.out.println("5. Search employee by (ID, name, role, status)");
        System.out.println("6. Calculate Payroll");
        System.out.println("7. Display Employee List");
        System.out.println("8. Save data to File");
        System.out.println("9. Quit program");
        
    }
    
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.loadFromFile();
        String choice = "";
        do{
        menu();
        choice = Inputter.input("Enter your choice (1-9): ").trim();
        switch(choice){
            case "1":
                manager.loadFromFile();
                break;
            case "2":
                manager.addNewEmployee();
                break;
            case "3":
                manager.updateEmployee();
                break;
            case "4":
                manager.removeEmployee();
                break;
            case "5":
                manager.searchEmployee();
                break;
            case "6":
                manager.calculatePayroll();
                break;
            case "7":
                manager.displayEmployeeList();
                break;
            case "8":
                manager.saveToFile();
                break;
            case "9":
                manager.quitProgram();
                break;
            default: 
                System.out.println("Your choice is not available");
                break;
        }
        }while(!choice.equals("9"));
    }
}

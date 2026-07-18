
package controller;
public interface IEmployeeManager {
    void loadFromFile();
    void addNewEmployee();
    void updateEmployee();
    void removeEmployee();
    void searchEmployee();
    void calculatePayroll();
    void displayEmployeeList();
    void saveToFile();
    void quitProgram();
}

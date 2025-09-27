/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Student;
import tools.Inputter;

/**
 *
 * @author lenovo
 */
public class Menu {

    private final Inputter inp;

    public Menu() {
        this.inp = new Inputter();
    }

    public int showMenuAndGetChoice() {
        printMenu();
        return inp.getInt("Chose an option (1 - 9): ");
    }

    
    public void showStudentDetails(Student stu) {
        System.out.println("Student Details:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("Student ID : %s\n", stu.getId());
        System.out.printf("Name       : %s\n", stu.getName());
        System.out.printf("Phone      : %s\n", stu.getPhone());
        System.out.printf("Mountain   : %s\n", stu.getMountainCode());
        System.out.printf("Fee        : %,.0f\n", stu.getTuitionFee());
        System.out.println("-----------------------------------------------------");
    }
    
    private void printMenu() {
        System.out.println("========= Student Registration Menu =========");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search Participants by Name");
        System.out.println("6. Filter Data by Campus");
        System.out.println("7. Statistics of Registration Numbers by Location");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit the Program");
        System.out.println("=============================================");
    }
}

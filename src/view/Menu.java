/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.StudentController;
import tools.Inputter;

/**
 *
 * @author lenovo
 */
public class Menu {

    private StudentController controller;
    private final Inputter inp;

    public Menu(StudentController controller) {
        this.controller = controller;
        this.inp = new Inputter();
    }

    public void start() {
        int choice;

        do {
            printMenu();
            choice = inp.getInt("Chose an option (1 - 9): ");

            switch (choice) {
                case 1:
                    // todo 
                    break;
                case 2:
                    // todo 
                    break;
                case 3:
                    // todo 
                    break;
                case 4:
                    // todo 
                    break;
                case 5:
                    // todo 
                    break;
                case 6:
                    // todo 
                    break;
                case 7:
                    // todo 
                    break;
                case 8:
                    // todo 
                    break;
                case 9:
                    // todo 
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
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

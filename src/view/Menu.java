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

    public int showMenuAndGetChoice() {
        showMenu();
        return Inputter.getInt("Chose an option (1 - 9): ");
    }
    
    private void showMenu() {
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

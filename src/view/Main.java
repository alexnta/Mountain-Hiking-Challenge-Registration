/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import model.Student;
import controller.Students;
import controller.Mountains;
import java.util.List;
import tools.Acceptable;
import tools.Inputter;

/**
 *
 * @author lenovo
 */
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Students students = new Students();

        int choice;
        do {
            choice = menu.showMenuAndGetChoice();
            switch (choice) {
                case 1:
                    students.add();
                    break;
                case 2:
                    students.update();
                    break;
                case 3:
                    students.showAll();
                    break;
                case 4:
                    students.delete();
                    break;
                case 5:
                    students.searchByName();
                    break;
                case 6:
                    students.filterCampusAndShow();
                    break;
                case 7:
                    students.statisticalizeByMountainPeak();
                    break;
                case 8:
                    students.saveToFile();
                    break;
                case 9:
                    students.validateSaveAndLeave();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }
}

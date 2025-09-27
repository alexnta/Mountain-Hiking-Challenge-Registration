/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import model.Student;
import business.Students;
import business.Mountains;
import java.util.List;
import tools.Acceptable;
import tools.Inputter;
import view.Menu;

/**
 *
 * @author lenovo
 */
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Students students = new Students();
        Mountains mountains = new Mountains();

//        mountains.showALl();
        Student stu;
        String ans;
        int choice;
        do {
            choice = menu.showMenuAndGetChoice();
            switch (choice) {
                case 1:
//                    students.add(null);
                    stu = Inputter.enterStudentInfo(false, students, mountains);
                    students.add(stu);
                    System.out.println("Student registered successfully.");
                    break;
                case 2:
                    stu = Inputter.enterStudentInfo(true, students, mountains);
                    if (students.update(stu)) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("This student has not registered yet.");
                    }
                    break;
                case 3:
                    students.showAll();
                    break;
                case 4:
                    String id = Inputter.inputAndLoop("Student Code to delete: ", Acceptable.STU_ID_VALID);
                    Student target = students.searchById(id);

                    if (target == null) {
                        System.out.println("This student has not registered yet.");
                        break;
                    }

                    System.out.println("Registered student:\n");
                    menu.showStudentDetails(target);

                    ans = Inputter.inputAndLoop("Do you want to delete this registration? (Y/N): ", Acceptable.YES_NO_VALID, "This function key is not available.");
                    if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                        if (students.delete(id)) {
                            System.out.println("The registration has been successfully deleted.");
                        } else {
                            System.out.println("The registration hasn't been deleted.");
                        }
                        break;
                    }

                    break;
                case 5:
                    String name = Inputter.inputAndLoop("Student name to search: ", Acceptable.NAME_VALID);
                    if (!students.searchByName(name)) {
                        System.out.println("No one matches the search criteria!");
                    }
                    ;
                    break;
                case 6:
                    String campusCode = Inputter.inputAndLoop("Campus code to search: ", Acceptable.CAMPUS_CODE_VALID);
                    List<Student> campusList = students.filterByCampusCode(campusCode);

                    if (campusCode.isEmpty()) {
                        System.out.println("No students have registered under this campus.");
                    } else {
                        students.showAll(campusList);
                    }

                    break;
                case 7:
                    students.statisticalizeByMountainPeak();
                    break;
                case 8:
                    students.saveToFile();
                    break;
                case 9:
                    if (!students.isSaved()) {
                        ans = Inputter.inputAndLoop("Do you want to save the changes before exiting? (Y/N): ", Acceptable.YES_NO_VALID, "This function key is not available.");
                        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                            students.saveToFile();
                        }
                    }

                    // Exit program
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }
}

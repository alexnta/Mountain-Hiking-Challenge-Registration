/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import business.Students;
import business.Mountains;
import tools.Inputter;
import view.Menu;

/**
 *
 * @author lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Students students = new Students();
        Mountains mountains = new Mountains();
        Inputter input = new Inputter();
        
        StudentController controller = new StudentController(input, students, mountains);
        
        Menu menu = new Menu(controller);
        menu.start();
    }
}

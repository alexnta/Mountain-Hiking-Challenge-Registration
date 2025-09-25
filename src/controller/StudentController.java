/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import business.Mountains;
import business.Students;
import tools.Inputter;
/**
 *
 * @author lenovo
 */
public class StudentController {
    
    private Students students;
    private Mountains mountains;
    private Inputter input;

    public StudentController(Inputter input, Students students, Mountains mountains) {
        this.input = input;
        this.students = students;
        this.mountains = mountains;
    }
    
    public void addNewStudent() {
        
    }
    
}

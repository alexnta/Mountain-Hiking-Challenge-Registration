/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class Inputter {

    private Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return ndl.nextLine();
    }

    public int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);

        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID))
                result = Integer.parseInt(temp);
        return result;
    }

    public double getDouble(String mess) {
        // todo
    }

    public String inputAndLoop(String mess, String pattern) {
        String result = "";
        boolean more = true;
        
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            
            if (more)
                System.out.println("Invalid date! Re-enter...");
        } while (more);
        
        return result.trim();
    }

    public Student enterStudentInfo(boolean isUpdate) {
        // Method body not shown in image
    }
}

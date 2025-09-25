/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Scanner;
import business.Students;
import business.Mountains;
import model.Student;
import model.Mountain;

/**
 *
 * @author lenovo
 */
public class Inputter {

    private Scanner scanner;

    public Inputter() {
        this.scanner = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return scanner.nextLine();
    }

    public int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);

        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID))
            result = Integer.parseInt(temp);
        return result;
    }

    public double getDouble(String mess) {
        double result = 0;
        String temp = getString(mess);
        
        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID))
            result = Double.parseDouble(temp);
        return result;
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

    public Student enterStudentInfo(boolean isUpdate, Students students, Mountains mountains) {
        String id = null;
        if (!isUpdate) { // Only ask for ID when adding
            boolean validId;
            do {
                id = inputAndLoop("Enter Student ID: ", Acceptable.STU_ID_VALID);

                // Check uniqueness
                validId = students.searchById(id) == null;
                if (!validId) System.out.println("This Student ID already exists. Enter again!");
            } while (!validId);
        }

        // Name validation
        String name = inputAndLoop("Enter Student Name: ", Acceptable.NAME_VALID);

        // Phone validation (10 digits + Vietnamese operator)
        String phone;
        do {
            phone = inputAndLoop("Enter Phone Number: ", Acceptable.PHONE_VALID);
            if (!(Acceptable.isValid(phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(phone, Acceptable.VNPT_VALID))) {
                System.out.println("Phone number must belong to Viettel or VNPT!");
            }
        } while (!(Acceptable.isValid(phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(phone, Acceptable.VNPT_VALID)));

        // Email validation
        String email = inputAndLoop("Enter Email: ", Acceptable.EMAIL_VALID);

        // Mountain code validation
        String mountainCode;
        Mountain mountain;
        do {
            mountainCode = inputAndLoop("Enter Mountain Code: ", "^.+$"); // basic input, check existence in list
            mountain = mountains.get(mountainCode);
            if (mountain == null) {
                System.out.println("Mountain code does not exist. Enter again!");
            }
        } while (mountain == null);

        // Tuition fee calculation
        double tuitionFee = 6_000_000;
        if (Acceptable.isValid(phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(phone, Acceptable.VNPT_VALID)) {
            tuitionFee *= 0.65; // apply 35% discount
        }

        // Build Student object
        Student student = new Student();
        if (!isUpdate) student.setId(id);
        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
        student.setMountainCode(mountainCode);
        student.setTuitionFee(tuitionFee);

        return student;
    }

}

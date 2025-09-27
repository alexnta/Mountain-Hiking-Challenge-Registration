/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import business.Mountains;
import business.Students;
import java.util.Scanner;
import java.util.function.Predicate;
import model.Student;

/**
 *
 * @author lenovo
 */
public class Inputter {

    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String mess) {
        System.out.print(mess);
        return scanner.nextLine();
    }

    public static int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);

        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID)) {
            result = Integer.parseInt(temp);
        }
        return result;
    }

    public static double getDouble(String mess) {
        double result = 0;
        String temp = getString(mess);

        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID)) {
            result = Double.parseDouble(temp);
        }
        return result;
    }

    public static String inputAndLoop(String mess, String pattern) {
        return inputAndLoop(mess, pattern, "Invalid input! Please try again.");
    }
    
    public static String inputAndLoop(String mess, String pattern, String failMsg) {
        String result = "";
        boolean more = true;

        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);

            if (more) {
                System.out.println(failMsg);
            }
        } while (more);

        return result.trim();
    }

    public static Student enterStudentInfo(boolean isUpdate, Students students, Mountains mountains) {

        String id = inputStudentID(isUpdate, students);

        String name = inputAndLoop("Enter Student Name: ", Acceptable.NAME_VALID);

        String phone = inputAndLoop("Enter Phone Number (10 digits): ", Acceptable.PHONE_VALID);

        String email = inputAndLoop("Enter Email: ", Acceptable.EMAIL_VALID);

        String mountainCode = inputMountainCode(mountains);

        double tuitionFee = 6000000;
        if (Acceptable.isValid(phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(phone, Acceptable.VNPT_VALID)) {
            tuitionFee *= 0.65;
        }

        return new Student(id, name, phone, email, mountainCode, tuitionFee);
    }

    private static String inputStudentID(boolean isUpdate, Students students) {
        String idFailMessage = isUpdate ? "This student has not registered yet." : "Student ID already exists. Please enter a new one.";

        String id;
        do {
            id = inputAndLoop("Enter Student ID: ", Acceptable.STU_ID_VALID);

            if ((isUpdate && !students.isExistingId(id)) || (!isUpdate && students.isExistingId(id))) {
                System.out.println(idFailMessage);
                id = ""; // loop again
            }

        } while (id.isEmpty());

        return id;
    }

    private static String inputMountainCode(Mountains mountains) {
        String mountainCode;
        do {
            mountainCode = inputAndLoop("Enter Mountain Code: ", Acceptable.PEAK_CODE_VALID);
            if (!mountains.isValidMountainCode(mountainCode)) {
                System.out.println("Mountain code does not exist. Please try again.");
                mountainCode = "";
            }
        } while (mountainCode.trim().isEmpty());

        return mountainCode;
    }
}

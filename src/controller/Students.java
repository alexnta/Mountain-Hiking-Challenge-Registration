package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class Students {

    private final List<Student> list;
    private boolean isSaved;
    private Mountains mountains;
    
    private String pathFile;

    private final String HEADER_TABLE
            = "+------------------------------------------------------------------+\n"
            + "| Student ID |       Name    | Phone      | Peak Code |     Fee    |\n"
            + "-------------------------------------------------------------------";
    private final String FOOTER_TABLE
            = "+------------------------------------------------------------------+";

    public Students() {
        this("data/registration.dat");
    }

    public Students(String pathFile) {
        this.list = new ArrayList<>();
        this.isSaved = true;
        this.pathFile = pathFile;
        this.mountains = new Mountains();
        this.readFromFile();
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void add() {
        Student stu = Inputter.enterStudentInfo(false, this, mountains);
        if (stu == null) {
            return;
        }

        list.add(stu);
        System.out.println("Student registered successfully.");
        isSaved = false;
    }

    public void update() {
        Student stu = Inputter.enterStudentInfo(true, this, mountains);
        if (stu == null) {
            return;
        }

        boolean updated = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(stu.getId())) {
                list.set(i, stu);
                isSaved = false;

                updated = true;
            }
        }

        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("This student has not registered yet.");
        }
    }

    public void delete() {
        String id = Inputter.inputAndLoop("Student Code to delete: ", Acceptable.STU_ID_VALID);
        
        Student target = searchById(id);
        
        if (target == null) {
            System.out.println("This student has not registered yet.");
            return;
        }
        
        System.out.println("Registered student:\n");
        showStudentDetails(target);
        
        String ans = Inputter.inputAndLoop("Do you want to delete this registration? (Y/N): ", Acceptable.YES_NO_VALID, "This function key is not available.");
        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
            boolean removed = list.removeIf(s -> s.getId().equalsIgnoreCase(id));
            if (removed) {
                System.out.println("The registration has been successfully deleted.");
                isSaved = false;
            } else {
                System.out.println("The registration hasn't been deleted.");
            }
        }
    }

    private void showStudentDetails(Student stu) {
        System.out.println("Student Details:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("Student ID : %s\n", stu.getId());
        System.out.printf("Name       : %s\n", stu.getName());
        System.out.printf("Phone      : %s\n", stu.getPhone());
        System.out.printf("Mountain   : %s\n", stu.getMountainCode());
        System.out.printf("Fee        : %,.0f\n", stu.getTuitionFee());
        System.out.println("-----------------------------------------------------");
    }
    
    public Student searchById(String id) {
        for (Student stu : list) {
            if (stu.getId().equalsIgnoreCase(id)) {
                return stu;
            }
        }

        return null;
    }

    public boolean isExistingId(String id) {
        return searchById(id) != null;
    }

    public void searchByName() {
        String name = Inputter.inputAndLoop("Student name to search: ", Acceptable.NAME_VALID);
        
        for (Student stu : list) {
            if (stu.getName().equalsIgnoreCase(name)) {
                System.out.println(stu);
                return;
            }
        }

        System.out.println("No one matches the search criteria!");
    }

    public void showAll() {
        showAll(this.list);
    }

    public void showAll(List<Student> l) {
        if (l.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        System.out.println(HEADER_TABLE);
        for (Student stu : l) {
            System.out.printf("| %10s | %13s | %10s | %9s | %,10.0f |\n",
                    stu.getId(),
                    stu.getName(),
                    stu.getPhone(),
                    stu.getMountainCode(),
                    stu.getTuitionFee());
        }
        System.out.println(FOOTER_TABLE);
    }

    private List<Student> filterByCampusCode(String campusCode) {
        List<Student> result = new ArrayList<>();
        for (Student stu : list) {
            if (stu.getId().substring(0, 2).equalsIgnoreCase(campusCode)) {
                result.add(stu);
            }
        }

        return result;
    }
    
    public void filterCampusAndShow() {
        String campusCode = Inputter.inputAndLoop("Campus code to search: ", Acceptable.CAMPUS_CODE_VALID);
        List<Student> campusList = filterByCampusCode(campusCode);
        
        if (campusList.isEmpty()) {
            System.out.println("No students have registered under this campus.");
        } else {
            showAll(campusList);
        }
    }

    public void statisticalizeByMountainPeak() {
        if (list.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        Statistics stats = new Statistics(list);
        System.out.println("Statistics of Registration by Mountain Peak:");
        stats.show();
    }

    public void readFromFile() {

        File file = new File(this.pathFile); // /data/registration.dat
        if (!file.exists()) {
            return;
        }

        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                list.clear();
                for (Object o : (List<?>) obj) {
                    if (o instanceof Student) {
                        list.add((Student) o);
                    }
                }
            }

            this.isSaved = true;
            System.out.println("Data loaded successfully from " + pathFile);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveToFile() {
        if (isSaved()) {
            return;
        }

        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File f = new File(this.pathFile);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(list);
            isSaved = true;
            System.out.println("Data saved successfully.");
        } catch (IOException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void validateSaveAndLeave() {
        if (!isSaved()) {
            String ans = Inputter.inputAndLoop("Do you want to save the changes before exiting? (Y/N): ", Acceptable.YES_NO_VALID, "This function key is not available.");
            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")) {
                saveToFile();
            }
        }
        
        System.out.println("Exiting program...");
    }
}

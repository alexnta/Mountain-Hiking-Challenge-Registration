package Business;

import java.util.ArrayList;
import java.util.List;

import Models.Student;

public class Students {
    private String pathFile;
    private boolean isSaved;

    public Students() {
    }

    public boolean isSaved() {
        return false;
    }

    public void add(Student x) {}

    public void update(Student x) {}

    public void delete(String id) {}

    public Student searchById(String id) {return null;}

    public void searchByName(String name) {}

    public void showAll() {
        System.out.println("HEADER_TABLE");
        for (Student i : this) {
            System.out.println(i);
            System.out.println("FOOTER_TABLE");
        }
    }

    public void showAll(List<Student> list) {}

    public List<Student> filterByCampusCode(String campusCode) {}

    public void statisticalizeByMountainPeak() {}

    public void readFromFile() {}

    public void saveToFile() {}

}

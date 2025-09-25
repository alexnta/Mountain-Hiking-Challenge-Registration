package business;

import java.util.ArrayList;
import java.util.List;

import model.Student;

public class Students {

    private final List<Student> list;
    private String pathFile;
    private boolean isSaved;

    public Students() {
        this.list = new ArrayList<>();
        this.isSaved = true;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void add(Student stu) {
        if (stu == null) {
            return;
        }

        list.add(stu);
        isSaved = false;
    }

    public void update(Student stu) {
        if (stu == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(stu.getId())) {
                list.set(i, stu);
                isSaved = false;

                return;
            }
        }

        // if can't find student
        System.out.println("Student not found!");
    }

    public void delete(String id) {
        list.removeIf(s -> s.getId().equalsIgnoreCase(id));
        isSaved = false;
    }

    public Student searchById(String id) {
        for (Student stu : list) {
            if (stu.getId().equalsIgnoreCase(id)) {
                return stu;
            }
        }

        return null;
    }

    public void searchByName(String name) {
        for (Student stu : list) {
            if (stu.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(stu);
            }
        }
    }

    public void showAll() {
        showAll(this.list);
    }

    public void showAll(List<Student> l) {
        if (l.isEmpty()) {
            System.out.println("No students registered");
            return;
        }

//        System.out.println(HEADER_TABLE);
        l.forEach(stu -> System.out.println(stu));
//        System.out.println(FOOTER_TABLE);
    }

    public List<Student> filterByCampusCode(String campusCode) {
        List<Student> result = new ArrayList<>();
        for (Student stu : list) {
            if (stu.getId().substring(0, 2).equalsIgnoreCase(campusCode)) {
                result.add(stu);
            }
        }

        return result;
    }

//    public void statisticalizeByMountainPeak() {
//    }
//
//    public void readFromFile() {
// 
//    }
//
//    public void saveToFile() {
//    }
}

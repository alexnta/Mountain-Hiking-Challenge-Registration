package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Student;

public class Students {

    private final List<Student> list;
    private String pathFile;
    private boolean isSaved;

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
        this.readFromFile();
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

    public boolean update(Student stu) {
        if (stu == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(stu.getId())) {
                list.set(i, stu);
                isSaved = false;

                return true;
            }
        }

        // if can't find student
        return false;
    }

    public boolean delete(String id) {
        boolean removed = list.removeIf(s -> s.getId().equalsIgnoreCase(id));
        if (removed) {
            isSaved = false;
        }

        return removed;
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

    public boolean searchByName(String name) {
        for (Student stu : list) {
            if (stu.getName().equalsIgnoreCase(name)) {
                System.out.println(stu);
                return true;
            }
        }

        return false;
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

    public List<Student> filterByCampusCode(String campusCode) {
        List<Student> result = new ArrayList<>();
        for (Student stu : list) {
            if (stu.getId().substring(0, 2).equalsIgnoreCase(campusCode)) {
                result.add(stu);
            }
        }

        return result;
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

    @SuppressWarnings("unchecked")
    public void readFromFile() {

        File file = new File(this.pathFile); // projectRoot/data/registration.dat
        if (!file.exists()) {
//            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Student> loaded = (List<Student>) ois.readObject();
            list.clear();
            list.addAll(loaded);
            this.isSaved = true;
            System.out.println("Data loaded successfully from " + pathFile);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveToFile() {
        if (isSaved) {
            return;
        }

        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File f = new File(this.pathFile);

        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(list);
            this.isSaved = true;
            System.out.println("Data saved successfully.");

        } catch (IOException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

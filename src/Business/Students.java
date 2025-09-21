package Business;

import java.util.ArrayList;
import java.util.List;

import Models.Student;
import Models.Mountain;
import com.sun.istack.internal.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;

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

    public void readFromFile(){
        FileReader fr = null;
        try {
            // --- B1. Tạo đối tượng File để xác định tên tập tin MountainList.csv ---
            File f = new File(this.pathFile);
            // --- B1.1 Kiểm tra sự tồn tại của tập tin nếu không có ---
            if(!f.exists()){
                System.out.println("MountainList.csv file not found !");
                return;
            }
            // --- B2. Tạo đối tượng đọc dữ liệu từ tập tin ---
            fr = new FileReader(f);
            // --- B3. Tạo Buffer để đọc dữ liệu từ tập tin ---
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            // --- B4. Đọc từng dòng dữ liệu từ tập tin ---
            while((temp = br.readLine())!=null){
                Mountain m = (Mountain)DataToObject(temp);
                if(m != null){
                    this.add(m);
                }
            }
            // --- B5. Đóng tập tin sau khi hoàn thành ---
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void saveToFile() {}

}

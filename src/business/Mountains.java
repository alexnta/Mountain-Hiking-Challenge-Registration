/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;

/**
 *
 * @author lenovo
 */
public class Mountains {

    public List<Mountain> list = new ArrayList<>();
    private final String filePath;

    public Mountains() {
        this("data/MountainList.csv");
    }

    public Mountains(String filePath) {
        this.filePath = filePath;
        readFromFile();
    }

    // for trivial
    public void showALl() {
        for (Mountain m : list) {
            System.out.println(m);
        }
    }

    public Mountain get(String mountainCode) {
        for (Mountain m : list) {
            if (m.getMountainCode().equalsIgnoreCase(mountainCode)) {
                return m;
            }
        }

        return null;
    }

    public boolean isValidMountainCode(String mountainCode) {
        if (mountainCode == null || mountainCode.trim().isEmpty()) {
            return false;
        }

        for (Mountain m : list) {
            if (m.getMountainCode().equalsIgnoreCase(mountainCode)) {
                return true;
            }
        }

        return false;
    }

    public Mountain dataToObject(String text) {
        String[] parts = text.split(",");

        String code = parts[0].trim();
        String mountain = parts[1].trim();
        String province = parts[2].trim();
        String desc = parts[3].trim();

        return new Mountain(code, mountain, province, desc);
    }

    public void readFromFile() {

        File f = new File(this.filePath);
        if (!f.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(f))) {
            // skip first line
            br.readLine();

            String temp;
            while ((temp = br.readLine()) != null) {
                Mountain m = dataToObject(temp);
                if (m != null) {
                    list.add(m);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

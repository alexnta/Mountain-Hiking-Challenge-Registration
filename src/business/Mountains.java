/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;

/**
 *
 * @author lenovo
 */
public class Mountains {

    private List<Mountain> list = new ArrayList<>();
    private final String filePath;
    
    public Mountains() {
        this.filePath = "C:\\Users\\lenovo\\Documents\\fptu\\Ky 3\\LAB211\\Lab_FA25\\01_J1.L.P0027.MountainHiking _300LOC";
    }

    public Mountains(String filePath) {
        this.filePath = filePath;
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
        if (mountainCode == null || mountainCode.trim().isEmpty()) 
            return false;
        
        for (Mountain m : list) {
            if (m.getMountainCode().equalsIgnoreCase(mountainCode))
                return false;
        }
        
        return true;
    }

    public Mountain dataToObject(String text) {
        String[] parts = text.split(",");
        
        String code = parts[0].trim();
        String mountain = parts[1].trim();
        String province = parts[2].trim();
        String desc = parts[3].trim();
        
        return new Mountain(code, mountain, province, desc);
    }


    public void readFromFile(){
        File f = new File(this.filePath);
        if (!f.exists()) {
            System.out.println("File " + filePath + " is empty.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String temp;
            while ((temp = br.readLine()) != null) {
                Mountain m = dataToObject(temp);
                if (m != null) 
                    list.add(m);
            }
        } catch (IOException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

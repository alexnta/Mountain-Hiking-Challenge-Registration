/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Models.StatisticalInfo;
import Models.Student;
import java.util.HashMap;

/**
 *
 * @author lenovo
 */

public class Statistics extends HashMap<String, StatisticalInfo>{

    private final String HEADER_TABLE =
            "+-------------------------------------------------------------------+\n"+
            "|    Peak Name    |    Number of Participants    |    Total Cost    |\n" +
            "|-------------------------------------------------------------------|";

    private final String FOOTER_TABLE =
            "+-------------------------------------------------------------------+";

    public Statistics() {
        super();
    }

    public Statistics (List<Student> l) {
        super();
        statisticalize(l);
    }

    /* Phuong thuc thuc hien thong ke du lieu dua tren danh sach */
    public final void statisticalize (List<Student> 1) {
        for(Student i : l)
        if (this.containsKey(i.getMountainCode())){
            StatisticalInfo x = this.get(i.getMountainCode());
            x.setNumOfRegistration(x.getNumOfRegistration() + 1);
            x.setTotalCost(x.getTotalCost() + i.getTutionFee());
        }else{
            StatisticalInfo z = new StatisticalInfo(i.getMountainCode(), 1, i.getTutionFee());

            this.put(i.getMountainCode(), z);
        }
    }

    /** Hiển thị thông tin thống kê */
    public void show(){
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo i : this.values())
            System.out.println(i);
        System.out.println(FOOTER_TABLE);
    }
}
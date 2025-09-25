/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import model.StatisticalInfo;
import model.Student;
import java.util.HashMap;
import java.util.List;

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

//    public Statistics (List<Student> list) {
//        super();
//        statisticalize(list);
//    }
////
////    /* Phuong thuc thuc hien thong ke du lieu dua tren danh sach */
////    public final void statisticalize (List<Student> list) {
//        for (Student i : list) {
//            if (this.containsKey(i.getMountainCode())){
//                StatisticalInfo x = this.get(i.getMountainCode());
//                x.setNumOfRegistration(x.getNumOfRegistration() + 1);
//                x.setTotalCost(x.getTotalCost() + i.getTutionFee());
//            }else{
//                StatisticalInfo y = new StatisticalInfo(i.getMountainCode(), 1, i.getTutionFee());
//
//                this.put(i.getMountainCode(), y);
//            }
//        }
//    }

    
    public void show(){
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo i : this.values())
            System.out.println(i);
        System.out.println(FOOTER_TABLE);
    }
}
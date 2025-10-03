/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.StatisticalInfo;
import model.Student;
import java.util.HashMap;
import java.util.List;

/**
 *3
 * @author lenovo
 */
public class Statistics extends HashMap<String, StatisticalInfo> {

    private final String HEADER_TABLE
            = "+----------------------------------------------------------------------+\n"
            + "|      Peak Name     |    Number of Participants    |    Total Cost    |\n"
            + "|----------------------------------------------------------------------|";

    private final String FOOTER_TABLE
            = "+----------------------------------------------------------------------+";

    public Statistics() {
        super();
    }

    public Statistics(List<Student> list) {
        super();
        statisticalize(list);
    }

    public final void statisticalize(List<Student> list) {
        this.clear();

        if (list == null || list.isEmpty()) {
            return;
        }

        for (Student s : list) {
            if (s == null) {
                continue;
            }
            String code = s.getMountainCode();
            if (code == null) {
                continue;
            }

            if (this.containsKey(code)) {
                StatisticalInfo info = this.get(code);
                info.setNumOfStudent(info.getNumOfStudent() + 1);
                info.setTotalCost(info.getTotalCost() + s.getTuitionFee());
            } else {
                StatisticalInfo info = new StatisticalInfo(code, 1, s.getTuitionFee());
                this.put(code, info);
            }
        }
    }

    public void show() {
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo info : this.values()) {
            System.out.printf("| %-18s | %28d | %,16.0f |\n",
                    "MT" + info.getMountainCode(),
                    info.getNumOfStudent(),
                    info.getTotalCost());
        }
        System.out.println(FOOTER_TABLE);
    }
}

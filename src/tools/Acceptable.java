/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tools;

/**
 *
 * @author lenovo
 */
public interface Acceptable {

    String STU_ID_VALID = "^[CcDdHhSsQq][Ee]\\d{6}$";
    String CAMPUS_CODE_VALID = "^[CcDdHhSsQq][Ee]$";
    String NAME_VALID = "^[a-zA-Z ]{2,20}$";
    String DOUBLE_VALID = "^\\d+(\\.\\d+)?$";
    String INTEGER_VALID = "^\\d+$";
    String PHONE_VALID = "^0\\d{9}$";
    String VIETTEL_VALID = "^(086|096|097|098|039|038|037|036|035|034|033|032)\\d{7}$";
    String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";
    String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$";
    String YES_NO_VALID = "^(?i)(y|yes|n|no)$";

    static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}

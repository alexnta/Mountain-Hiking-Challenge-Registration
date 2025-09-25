/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataio;

import java.io.IOException;
import java.util.List;
import model.Student;

/**
 *
 * @author lenovo
 */
public interface Repository<T> {
    void save(List<T> list) throws IOException;
    List<T> load() throws IOException, ClassNotFoundException;
}

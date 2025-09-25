/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataio;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class ObjectFileRepo<T> implements Repository<T> {

    private final String filePath;

    public ObjectFileRepo(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(List<T> list) throws IOException {
        File f = new File(filePath);
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(list);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> load() throws IOException, ClassNotFoundException {
        File f = new File(filePath);
        // if file doesnt exist, create a new empty array list
        if ((!f.exists())) {
            System.out.println("File " + filePath + " is empty. Returning an empty list.");
            return new ArrayList<>();
        }

        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<T>) ois.readObject();
        } catch (EOFException e) {
            Logger.getLogger(ObjectFileRepo.class.getName()).log(Level.INFO, "File {0} is empty. Returning an empty list.", filePath);
            return new ArrayList<>();
        }
    }
}

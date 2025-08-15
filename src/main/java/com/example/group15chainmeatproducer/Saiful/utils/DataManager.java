package com.example.group15chainmeatproducer.Saiful.utils;

import java.io.*;
import java.util.ArrayList;

public class DataManager {

    public static void saveToFile(ArrayList<?> data, String filename) {
        try {
            File file = new File("data/" + filename + ".bin");
            file.getParentFile().mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static ArrayList<?> loadFromFile(String filename) {
        try {
            File file = new File("data/" + filename + ".bin");
            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<?> data = (ArrayList<?>) ois.readObject();
            ois.close();
            fis.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
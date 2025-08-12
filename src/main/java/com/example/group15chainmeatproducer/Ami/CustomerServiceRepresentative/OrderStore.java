package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class OrderStore {
    private static final String DEFAULT_PATH = "orders.bin";

    private OrderStore() {
    }

    public static ObservableList<Order> load() {
        return load(DEFAULT_PATH);
    }

    public static ObservableList<Order> load(String path) {
        Path p = Path.of(path);
        if (!Files.exists(p)) {
            return FXCollections.observableArrayList();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(p)))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?> list) {
                List<Order> orders = new ArrayList<>();
                for (Object o : list) {
                    if (o instanceof Order order) orders.add(order);
                }
                return FXCollections.observableArrayList(orders);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }

    public static void save(ObservableList<Order> orders) {
        save(DEFAULT_PATH, orders);
    }

    public static void save(String path, ObservableList<Order> orders) {
        Path p = Path.of(path);
        try {
            if (p.getParent() != null) Files.createDirectories(p.getParent());
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(p)))) {
                oos.writeObject(new ArrayList<>(orders));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

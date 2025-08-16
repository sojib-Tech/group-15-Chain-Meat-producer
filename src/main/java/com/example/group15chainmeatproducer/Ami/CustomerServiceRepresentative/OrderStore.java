package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class OrderStore {
    private static final String DEFAULT_PATH = "orders.bin";

    private OrderStore() {
    }

    public static ArrayList<Order> load() {
        return load(DEFAULT_PATH);
    }

    public static ArrayList<Order> load(String path) {
        Path p = Path.of(path);
        if (!Files.exists(p)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(p)))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?> list) {
                ArrayList<Order> orders = new ArrayList<>();
                for (Object o : list) {
                    if (o instanceof Order order) orders.add(order);
                }
                return orders;
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    public static void save(ArrayList<Order> orders) {
        save(DEFAULT_PATH, orders);
    }

    public static void save(String path, ArrayList<Order> orders) {
        Path p = Path.of(path);
        try {
            if (p.getParent() != null) Files.createDirectories(p.getParent());
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(p)))) {
                oos.writeObject(new ArrayList<>(orders));
            }
        } catch (IOException e) {
        }
    }
}

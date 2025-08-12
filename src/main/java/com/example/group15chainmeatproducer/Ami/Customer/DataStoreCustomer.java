package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataStoreCustomer {

    private static final String BASE_DIR = System.getProperty("user.home") + File.separator + ".chain_meat_customer" + File.separator;

    private static Path ensureDir() throws IOException {
        Path dir = Paths.get(BASE_DIR);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        return dir;
    }

    private static <T> List<T> loadList(String fileName) {
        try {
            ensureDir();
            Path path = Paths.get(BASE_DIR + fileName);
            if (!Files.exists(path)) return new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
                Object obj = ois.readObject();
                if (obj instanceof List<?>) {
                    @SuppressWarnings("unchecked") List<T> list = (List<T>) obj;
                    return list;
                }
            }
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    private static <T> void saveList(String fileName, List<T> data) {
        try {
            ensureDir();
            Path path = Paths.get(BASE_DIR + fileName);
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
                oos.writeObject(new ArrayList<>(data));
            }
        } catch (Exception ignored) {
        }
    }

    public static List<Product> loadProducts() {
        return loadList("products.bin");
    }

    public static void saveProducts(List<Product> products) {
        saveList("products.bin", products);
    }

    public static List<Review> loadReviews() {
        return loadList("reviews.bin");
    }

    public static void saveReviews(List<Review> reviews) {
        saveList("reviews.bin", reviews);
    }

    public static List<OrderSummary> loadOrders() {
        return loadList("orders.bin");
    }

    public static void saveOrders(List<OrderSummary> orders) {
        saveList("orders.bin", orders);
    }

    public static List<CartItem> loadCart() {
        return loadList("cart.bin");
    }

    public static void saveCart(List<CartItem> cart) {
        saveList("cart.bin", cart);
    }

    public static List<SupportTicket> loadTickets() {
        return loadList("tickets.bin");
    }

    public static void saveTickets(List<SupportTicket> tickets) {
        saveList("tickets.bin", tickets);
    }
}

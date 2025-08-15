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

public final class LoginStore {
    private static final String DEFAULT_PATH = "logins.bin";

    private LoginStore() {
    }

    public static ArrayList<LoginAttempt> load() {
        return load(DEFAULT_PATH);
    }

    public static ArrayList<LoginAttempt> load(String path) {
        Path p = Path.of(path);
        if (!Files.exists(p)) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(p)))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?> list) {
                ArrayList<LoginAttempt> out = new ArrayList<>();
                for (Object o : list) if (o instanceof LoginAttempt a) out.add(a);
                return out;
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    public static void save(ArrayList<LoginAttempt> attempts) {
        save(DEFAULT_PATH, attempts);
    }

    public static void save(String path, ArrayList<LoginAttempt> attempts) {
        Path p = Path.of(path);
        try {
            if (p.getParent() != null) Files.createDirectories(p.getParent());
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(p)))) {
                oos.writeObject(new ArrayList<>(attempts));
            }
        } catch (IOException e) {
        }
    }
}
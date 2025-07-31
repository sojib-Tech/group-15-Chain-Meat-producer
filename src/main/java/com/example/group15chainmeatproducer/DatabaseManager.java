package com.example.group15chainmeatproducer;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private static DatabaseManager instance;

    private DatabaseManager() {
        initializeDatabase();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializeDatabase() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        full_name TEXT NOT NULL,
                        id_number TEXT UNIQUE NOT NULL,
                        user_type TEXT NOT NULL,
                        password_hash TEXT NOT NULL,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                    )
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (full_name, id_number, user_type, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getIdNumber());
            pstmt.setString(3, user.getUserType());
            pstmt.setString(4, hashPassword(user.getPassword()));

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            if (e.getErrorCode() == 19) { // SQLITE_CONSTRAINT
                System.err.println("User with ID number already exists");
            } else {
                System.err.println("Error registering user: " + e.getMessage());
            }
            return false;
        }
    }

    public User authenticateUser(String idNumber, String password, String userType) {
        String sql = "SELECT * FROM users WHERE id_number = ? AND user_type = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idNumber);
            pstmt.setString(2, userType);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String inputHash = hashPassword(password);

                if (storedHash.equals(inputHash)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setIdNumber(rs.getString("id_number"));
                    user.setUserType(rs.getString("user_type"));
                    return user;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }

        return null;
    }

    public boolean userExists(String idNumber) {
        String sql = "SELECT COUNT(*) FROM users WHERE id_number = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error checking user existence: " + e.getMessage());
        }

        return false;
    }
}
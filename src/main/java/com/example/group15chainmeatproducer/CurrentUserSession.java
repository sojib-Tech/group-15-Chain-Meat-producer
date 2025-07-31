package com.example.group15chainmeatproducer;

public class CurrentUserSession {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static String getCurrentUserType() {
        return currentUser != null ? currentUser.getUserType() : null;
    }

    public static String getCurrentUserName() {
        return currentUser != null ? currentUser.getFullName() : null;
    }
}
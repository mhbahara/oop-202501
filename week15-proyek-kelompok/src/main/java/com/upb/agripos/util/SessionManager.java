package com.upb.agripos.util;

import com.upb.agripos.model.User;

public class SessionManager {
<<<<<<< HEAD
    private static SessionManager instance;
    private User currentUser;
    
    private SessionManager() {}
    
    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }
    
    public void login(User user) {
        this.currentUser = user;
        System.out.println("User logged in: " + user.getUsername() + " (" + user.getRole() + ")");
    }
    
    public void logout() {
        if (currentUser != null) {
            System.out.println("User logged out: " + currentUser.getUsername());
            this.currentUser = null;
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    public boolean isKasir() {
        return currentUser != null && currentUser.isKasir();
    }
    
    public Integer getCurrentUserId() {
        return currentUser != null ? currentUser.getId() : null;
    }
    
    public String getCurrentUserName() {
        return currentUser != null ? currentUser.getFullName() : "Guest";
    }
}
=======
    private static User currentUser = null;
    
    /**
     * Set user yang sedang login
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    /**
     * Dapatkan user yang sedang login
     */
    public static User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check apakah user sudah login
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Check apakah user adalah admin
     */
    public static boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    /**
     * Check apakah user adalah kasir
     */
    public static boolean isKasir() {
        return currentUser != null && currentUser.isKasir();
    }
    
    /**
     * Logout - hapus data user dari session
     */
    public static void logout() {
        currentUser = null;
    }
}
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5

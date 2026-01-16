package com.upb.agripos.util;

import com.upb.agripos.model.User;

public class SessionManager {
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

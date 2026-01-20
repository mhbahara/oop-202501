package com.upb.agripos.service;

import com.upb.agripos.dao.UserDAO;
import com.upb.agripos.dao.UserDAOImpl;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.util.SessionManager;

public class AuthService {
    private UserDAO userDAO;
    private SessionManager sessionManager;
    
    public AuthService() {
        this.userDAO = new UserDAOImpl();
        this.sessionManager = SessionManager.getInstance();
    }
    
    public User login(String username, String password) throws ValidationException, Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username harus diisi");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password harus diisi");
        }
        
        User user = userDAO.authenticate(username, password);
        if (user == null) {
            throw new ValidationException("Username atau password salah");
        }
        
        sessionManager.login(user);
        return user;
    }
    
    public void logout() {
        sessionManager.logout();
    }
    
    public User getCurrentUser() {
        return sessionManager.getCurrentUser();
    }
    
    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
    }
    
    public boolean hasRole(String role) {
        User user = sessionManager.getCurrentUser();
        return user != null && role.equals(user.getRole());
    }
    
    public boolean isAdmin() {
        return sessionManager.isAdmin();
    }
    
    public boolean isKasir() {
        return sessionManager.isKasir();
    }
}
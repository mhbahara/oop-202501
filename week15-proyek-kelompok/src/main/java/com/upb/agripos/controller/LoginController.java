package com.upb.agripos.controller;

import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;

public class LoginController {
    private AuthService authService;
    
    public LoginController() {
        this.authService = new AuthService();
    }
    
    public User login(String username, String password) 
            throws ValidationException, Exception {
        return authService.login(username, password);
    }
    
    public void logout() {
        authService.logout();
    }
    
    public User getCurrentUser() {
        return authService.getCurrentUser();
    }
    
    public boolean isLoggedIn() {
        return authService.isLoggedIn();
    }
    
    public boolean isAdmin() {
        return authService.isAdmin();
    }
    
    public boolean isKasir() {
        return authService.isKasir();
    }
}

package com.upb.agripos.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.upb.agripos.exception.AuthenticationException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.util.DatabaseConnection;

public class AuthService {
    
    /**
     * Login dengan username dan password
     * @param username username pengguna
     * @param password password pengguna
     * @return User object jika login berhasil
     * @throws AuthenticationException jika login gagal
     * @throws ValidationException jika input tidak valid
     */
    public static User login(String username, String password) 
            throws AuthenticationException, ValidationException {
        
        // Validasi input
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username tidak boleh kosong");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password tidak boleh kosong");
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT id, username, password, full_name, role FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                
                // Validasi password (dalam produksi, gunakan hashing)
                if (storedPassword.equals(password)) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("full_name"),
                            rs.getString("role")
                    );
                    
                    rs.close();
                    stmt.close();
                    
                    return user;
                } else {
                    throw new AuthenticationException("Username atau password salah");
                }
            } else {
                throw new AuthenticationException("Username atau password salah");
            }
            
        } catch (SQLException e) {
            throw new AuthenticationException("Error saat melakukan login: " + e.getMessage());
        }
    }
    
    /**
     * Mendapatkan user berdasarkan ID
     */
    public static User getUserById(int userId) throws AuthenticationException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT id, username, password, full_name, role FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role")
                );
                rs.close();
                stmt.close();
                return user;
            }
            
            throw new AuthenticationException("User tidak ditemukan");
            
        } catch (SQLException e) {
            throw new AuthenticationException("Error: " + e.getMessage());
        }
    }
    
    /**
     * Register user baru (hanya untuk admin)
     */
    public static void registerUser(String username, String password, String fullName, String role) 
            throws ValidationException {
        
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username tidak boleh kosong");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password tidak boleh kosong");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new ValidationException("Nama lengkap tidak boleh kosong");
        }
        if (!role.equals("ADMIN") && !role.equals("KASIR")) {
            throw new ValidationException("Role harus ADMIN atau KASIR");
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO users (username, password, full_name, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, fullName);
            stmt.setString(4, role);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new ValidationException("Username sudah terdaftar");
            }
            throw new ValidationException("Error saat mendaftar: " + e.getMessage());
        }
    }
    
    /**
     * Update password user
     */
    public static void updatePassword(int userId, String oldPassword, String newPassword) 
            throws AuthenticationException, ValidationException {
        
        try {
            User user = getUserById(userId);
            
            if (!user.getPassword().equals(oldPassword)) {
                throw new AuthenticationException("Password lama tidak sesuai");
            }
            
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE users SET password = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new AuthenticationException("Error: " + e.getMessage());
        }
    }
}

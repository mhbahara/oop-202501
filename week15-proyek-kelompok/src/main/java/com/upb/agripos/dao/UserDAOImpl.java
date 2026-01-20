package com.upb.agripos.dao;

import com.upb.agripos.model.User;
import com.upb.agripos.util.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection getConnection() {
        return DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public User save(User user) throws Exception {
        String sql = "INSERT INTO users (username, password, full_name, role, is_active) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashPassword(user.getPassword()));
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getRole());
            stmt.setBoolean(5, user.getIsActive());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        user.setId(keys.getInt(1));
                    }
                }
            }
        }
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        String sql = """
                UPDATE users 
                SET password = ?, full_name = ?, role = ?, is_active = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, hashPassword(user.getPassword()));
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.getIsActive());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public User findById(Integer id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY username";

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    @Override
    public User authenticate(String username, String password) throws Exception {
        String sql = """
                SELECT * FROM users
                WHERE username = ?
                  AND is_active = true
                """;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    
                    if (verifyPassword(password, hashedPassword)) {
                        return mapResultSetToUser(rs);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Hash password menggunakan BCrypt
     */
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Verifikasi password dengan BCrypt
     */
    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        try {
            if (hashedPassword.startsWith("$2a$") || hashedPassword.startsWith("$2b$")) {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } else {
                // Fallback untuk plain text (migration)
                return plainPassword.equals(hashedPassword);
            }
        } catch (Exception e) {
            return false;
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("full_name"));
        user.setRole(rs.getString("role"));
        user.setIsActive(rs.getBoolean("is_active"));
        return user;
    }
}

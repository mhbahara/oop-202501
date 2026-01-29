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

    // ================= CRUD =================

    @Override
    public User save(User user) throws Exception {
        String sql = """
                INSERT INTO users (username, password, full_name, role, is_active)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt =
                     getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashPasswordIfNeeded(user.getPassword()));
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getRole());
            stmt.setBoolean(5, user.getIsActive());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
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
            stmt.setString(1, hashPasswordIfNeeded(user.getPassword()));
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
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public User findById(Integer id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToUser(rs);
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
                    return mapToUser(rs);
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
                users.add(mapToUser(rs));
            }
        }
        return users;
    }

    // ================= AUTH =================

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
                    if (verifyPassword(password, rs.getString("password"))) {
                        return mapToUser(rs);
                    }
                }
            }
        }
        return null;
    }

    // ================= HELPER =================

    private String hashPasswordIfNeeded(String password) {
        if (password == null) return null;
        if (password.startsWith("$2a$") || password.startsWith("$2b$")) {
            return password;
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(String plain, String stored) {
        try {
            if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
                return BCrypt.checkpw(plain, stored);
            }
            return plain.equals(stored); // fallback data lama
        } catch (Exception e) {
            return false;
        }
    }

    private User mapToUser(ResultSet rs) throws SQLException {
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

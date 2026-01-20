// ========== UserDAO.java ==========
package com.upb.agripos.dao;

import com.upb.agripos.model.User;
import java.util.List;

public interface UserDAO {
    void save(User user) throws Exception;
    void update(User user) throws Exception;
    void delete(Integer id) throws Exception;
    User findById(Integer id) throws Exception;
    User findByUsername(String username) throws Exception;
    List<User> findAll() throws Exception;
    User authenticate(String username, String password) throws Exception;
}

package com.upb.agripos.dao;

import java.util.List;
import com.upb.agripos.model.User;

public interface UserDAO {

    User save(User user) throws Exception;

    User update(User user) throws Exception;

    boolean delete(Integer id) throws Exception;

    User findById(Integer id) throws Exception;

    User findByUsername(String username) throws Exception;

    List<User> findAll() throws Exception;

    User authenticate(String username, String password) throws Exception;
}

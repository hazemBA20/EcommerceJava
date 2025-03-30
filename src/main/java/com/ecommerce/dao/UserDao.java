package com.ecommerce.dao;

import com.ecommerce.model.User;

import java.sql.SQLException;

public interface UserDao {
    public User getUser(User user) throws SQLException;

}

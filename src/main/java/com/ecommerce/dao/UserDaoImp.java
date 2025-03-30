package com.ecommerce.dao;

import com.ecommerce.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements  UserDao {
    private DAOFactory          daoFactory;

    public UserDaoImp( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    @Override
    public User getUser(User user) throws SQLException {
        Connection connection=daoFactory.getConnection();
        String query="SELECT * FROM users where Nom=? and passkey=?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setString(1 , user.getName());
        ps.setString(2 , user.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            return user;
        }
return null;

    }
}

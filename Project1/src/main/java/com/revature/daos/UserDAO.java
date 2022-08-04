package com.revature.daos;

import com.revature.services.ConnectionManager;
import com.revature.pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User> {
    Connection connection;

    public UserDAO() {
        this.connection = ConnectionManager.getConnection();
    }

    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO users (user_id, email_address, user_pass) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUserPass());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setEmail(results.getString("email_address"));
                user.setUserPass(results.getString("user_pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setEmail(results.getString("email_address"));
                user.setUserPass(results.getString("user_pass"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE users SET user_id = ?, email_address = ?, user_pass = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUserPass());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
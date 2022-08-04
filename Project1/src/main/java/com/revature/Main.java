package com.revature;

import com.revature.services.ConnectionManager;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();
    }
}
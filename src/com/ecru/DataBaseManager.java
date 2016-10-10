package com.ecru;

import java.sql.*;

/**
 * Created by Vitaliy Ryvakov on 10.10.2016.
 */
public class DataBaseManager {

    //private Connection connection;
    public Connection connection;

    public void connectToDataBase(String database, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please register you JDBC driver", e);
        }
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useSSL=false";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException eSQLException) {
            throw new RuntimeException(String.format("Can't connect to Database: %s by User: %s or Password: %s. ", database, user, password), eSQLException);
        }
    }

    public int countRows(String tableName) {
        Statement statement;
        int countRows = 0;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM `" + tableName + "`");
            resultSet.next();
            countRows = resultSet.getInt(1);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }


}

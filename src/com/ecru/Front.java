package com.ecru;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vitaliy Ryvakov on 09.10.2016.
 */
public class Front {


    private String kod;
    private String name;
    private Connection connection;
    private DataBaseManager manager;


    public Front(String kod, String name) {
        this.kod = kod;
        this.name = name;
    }


    public Front(DataBaseManager manager) {
        this.manager = manager;
    }

    public String getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public Front[] arrayFronts() {
        Front []result = new Front[manager.countRows("frn")];
        Statement statement;
        try {
            statement = manager.connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM `frn`");
            int index = 0;
            while (resultSet.next()) {
                result[index] = new Front(resultSet.getString(2),resultSet.getString(3));
                index++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}

package com.ecru;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by Vitaliy Ryvakov on 09.10.2016.
 */
public class Color {

    private String kod;
    private String name;
    private Connection connection;
    private DataBaseManager manager;


    public Color(String kod, String name) {
        this.kod = kod;
        this.name = name;
    }

    public Color(DataBaseManager manager) {
        this.manager = manager;
        this.connection = manager.connection;

    }

    public String getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public Color[] arrayColors(String frontKod) {
        Color []result = new Color[manager.countRows("colors")];
        Statement statement;
        int index = 0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT `color_kod`,`name` FROM `kitchenkonstructor`.`colors` WHERE `frn_kod` LIKE '%" + frontKod + "%'");
            while (resultSet.next()) {
                result[index] = new Color(resultSet.getString(1), resultSet.getString(2));
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Arrays.copyOfRange(result,0,index);
    }


}

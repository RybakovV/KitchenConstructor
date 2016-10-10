package com.ecru;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DownloadDataColors {

    private static Connection connection;


    public static void main(String[] args) {
        System.out.println("Updating price list");
        connectToDataBase("kitchenkonstructor", "root", "root");
        //unicumColorDataToConsole();
        unikumColorDataToDB("colors.txt", "colors");
    }

    private static void unikumColorDataToDB(String fileName, String tableName) {

    }


    private static void unicumColorDataToConsole() {


        Statement statementFRN;
        Statement statementColor;
        ResultSet resultSetFRN;

        try {
            statementFRN = connection.createStatement();
            statementColor = connection.createStatement();
            resultSetFRN = statementFRN.executeQuery("SELECT * FROM `frn`");
            ResultSet resultSetColor = null;
            String kodFRN;

            while (resultSetFRN.next()){
                kodFRN = resultSetFRN.getString(2);
                resultSetColor = statementColor.executeQuery("SELECT  `id`, `kod`, `name_ukr` FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '%"+kodFRN+"%'");
                resultSetColor.next();
                String unicumColor[] = new String[400];
                int unicumIndex = 0;
                String color = resultSetColor.getString(2).split("-")[2];
                unicumColor[unicumIndex] = color;
                unicumIndex++;
                while (resultSetColor.next()){
                    color = resultSetColor.getString(2).split("-")[2];
                    int i = 0;
                    while (i < unicumIndex) {
                        if (color.equals(unicumColor[i])){
                            break;
                        }
                        i++;
                    }
                    if (i == unicumIndex ){
                        unicumColor[unicumIndex] = color;
                        System.out.println(kodFRN + "\t" + color + "\t"  + resultSetColor.getString(3) );
                        unicumIndex++;
                    }
                }

            }
            resultSetColor.close();
            resultSetFRN.close();
            statementFRN.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return Arrays.copyOfRange(unicumColor,0,unicumIndex-1);
    }

    public static void connectToDataBase(String database, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("JDBC driver registered...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please register you JDBC driver", e);
        }
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useSSL=false";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(String.format("You are connected to Database: %s by User: %s or Password: %s. ", database, user, password));
        } catch (SQLException eSQLException) {
                throw new RuntimeException(String.format("Can't connect to Database: %s by User: %s or Password: %s. ", database, user, password), eSQLException);
            }
        }
}

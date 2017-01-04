package com.ecru;

import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DownloadUnicumData {

    private static Connection connection;


    public static void main(String[] args) {
        System.out.println("Updating price list");
        connectToDataBase("kitchenkonstructor", "root", "root");
        System.out.println(Arrays.toString(unicumData()));
        //System.out.println(tableTop().keySet());

    }

    private static Map<String,String> tableTop(){
        Map result = new HashMap();

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr` FROM `kitchenkonstructor`.`senso_price` " +
                    "WHERE `kod` LIKE '%-BLAT-%'")) {
            while (resultSet.next()){
                result.put(resultSet.getString("kod").split("-")[2],resultSet.getString("name_ukr"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String[] unicumData() {
        String unicumData[] = new String[400];

        Statement statement;
        ResultSet resultSet;
        int unicumIndex = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `senso_price`");
            resultSet.next();
            String kodFRN = resultSet.getString(2).split("-")[1];

            unicumData[unicumIndex] = kodFRN;
            unicumIndex++;
            while (resultSet.next()){
                kodFRN = resultSet.getString(2).split("-")[1];
                int i = 0;
                while (i < unicumIndex) {
                    if (kodFRN.equals(unicumData[i])){
                        break;
                    }
                    i++;
                }
                if (i == unicumIndex ){
                    unicumData[unicumIndex] = kodFRN;
                    System.out.println(unicumIndex + "\t" + kodFRN + "\t"  + resultSet.getString(3));
                    unicumIndex++;
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Arrays.copyOfRange(unicumData,0,unicumIndex-1);
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

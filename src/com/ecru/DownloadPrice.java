package com.ecru;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DownloadPrice {

    private static Connection connection;


    public static void main(String[] args) {
        System.out.println("Updating price list");
        connectToDataBase("kitchenkonstructor", "root", "root");
/*
        readFileAndWriteToDB("Senso 1.txt");
        readFileAndWriteToDB("Senso 2.txt");
        readFileAndWriteToDB("Senso 3.txt");
        readFileAndWriteToDB("Senso 4.txt");
        readFileAndWriteToDB("Senso 5.txt");
        readFileAndWriteToDB("Senso 6.txt");
        readFRNFROMFileAndWriteToDB("FRN.txt");
*/

        readColorsFROMFileAndWriteToDB("colorsBlatLis.txt", "colors");
    }

    private static void readColorsFROMFileAndWriteToDB(String fileName, String tableName) {
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
/*
                byte[] lineBytes = line.getBytes("UTF-8");
                line = new String(lineBytes, "UTF-8");
*/
                writeLineToDB("colors", "frn_kod, color_kod, name", line);
            }
            br.close();
            System.out.println("Read complet...");
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    private static void writeLineToDB(String tableName, String columns, String line) {
        String arrayLine[] =  line.split("\t");
        Statement statement;
        try {
            statement = connection.createStatement();

            String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES(" +
                    "'" + arrayLine[0] + "', " +
                    "'" + arrayLine[1] + "', " +
                    "'" + arrayLine[2].replace("'","\\'")+"')";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readFRNFROMFileAndWriteToDB(String fileName) {
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
                writeLineToFrn("frn", "kod, name", line);
            }
            br.close();
            System.out.println("Read complet...");
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    private static void writeLineToFrn(String tableName, String rows, String line) {
        String arrayLine[] =  line.split("\t");
        Statement statement;
        try {
            statement = connection.createStatement();

            String sql = "INSERT INTO " + tableName + " (" + rows + ") VALUES(" +
                    "'" + arrayLine[1] + "', " +
                    "'" + arrayLine[2].replace("'","\\'")+"')";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void readFileAndWriteToDB(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
                writeLineToSensoPrice("senso_price", "kod, name_ukr, price", line);
            }
            br.close();
            System.out.println("Read complet...");
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private static void writeLineToSensoPrice(String tableName, String rows, String line) {
        String arrayLine[] =  line.split("\t");
        Statement statement;
        try {
            statement = connection.createStatement();

            String sql = "INSERT INTO " + tableName + " (" + rows + ") VALUES(" +
                    "'" + arrayLine[1] + "', " +
                    "'" + arrayLine[2].replace("'","\\'") + "', " +
                    "'" + arrayLine[3].replace(",",".").replace(" ","") + "')";

            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

package com.ecru;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;

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

//        readColorsFROMFileAndWriteToDB("colorsKorpus.txt", "colors");

//        readFileAndWriteToConsole("Senso 1.txt");


        /*
        readFileAndChangePriceInDB("Senso 1.2.txt");
        readFileAndChangePriceInDB("Senso 2.txt");
        readFileAndChangePriceInDB("Senso 3.txt");
        readFileAndChangePriceInDB("Senso 4.txt");
        readFileAndChangePriceInDB("Senso 5.txt");
        readFileAndChangePriceInDB("Senso 6.txt");
        */

        long start = System.currentTimeMillis();
        Runnable target1 = new MyRunnable("Senso 1.txt");
        Runnable target2 = new MyRunnable("Senso 2.txt");
        Runnable target3 = new MyRunnable("Senso 3.txt");
        Runnable target4 = new MyRunnable("Senso 4.txt");
        Runnable target5 = new MyRunnable("Senso 5.txt");
        Runnable target6 = new MyRunnable("Senso 6.txt");
        Thread thread1 = new Thread(target1);
        Thread thread2 = new Thread(target2);
        Thread thread3 = new Thread(target3);
        Thread thread4 = new Thread(target4);
        Thread thread5 = new Thread(target5);
        Thread thread6 = new Thread(target6);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        long finish = System.currentTimeMillis();
        System.out.println("Update price with 6 trhreads (one file - one thread) by:" + (finish-start) +"msec");

        start = System.currentTimeMillis();
        readFileAndChangePriceInDB("Senso 1.txt");
        readFileAndChangePriceInDB("Senso 2.txt");
        readFileAndChangePriceInDB("Senso 3.txt");
        readFileAndChangePriceInDB("Senso 4.txt");
        readFileAndChangePriceInDB("Senso 5.txt");
        readFileAndChangePriceInDB("Senso 6.txt");
        finish = System.currentTimeMillis();
        System.out.println("Update price with 1 trhread (all file by queue) for:" + (finish-start) +"msec");

    }

    static class MyRunnable implements Runnable{
        private String fileName;
        MyRunnable(String fileName) {
            this.fileName = fileName;
        }
        @Override
        public void run() {
            readFileAndChangePriceInDB(fileName);
        }
    }

    public static void readFileAndChangePriceInDB(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
                changePriseInDB(line);
                System.out.println(fileName + ": " + line);
            }
            br.close();
            System.out.println("Read complet...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void changePriseInDB(String line) {
        String arrayLine[] =  line.split("\t");
        Statement statement;
        String sql = "";
        try {
            statement = connection.createStatement();

            sql ="UPDATE `kitchenkonstructor`.`senso_price` SET `price`=" + arrayLine[3] +
                    " WHERE  `kod`='"+ arrayLine[1] +"'";

            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
        }
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
                byte[] lineBytes = line.getBytes("UTF-8");
                line = new String(lineBytes, "UTF-8");
                writeLineToSensoPrice("senso_price", "kod, name_ukr, price", line);
            }
            br.close();
            System.out.println("Read complet...");
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }



    public static void readFileAndWriteToConsole(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
                byte[] lineBytes = line.getBytes("UTF-8");
                line = new String(lineBytes, "UTF-8");

                String arrayLine[] =  line.split("\t");
                System.out.print(arrayLine[1] + ", ");
                System.out.print(arrayLine[2] + ", ");
                System.out.println(arrayLine[3]);
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

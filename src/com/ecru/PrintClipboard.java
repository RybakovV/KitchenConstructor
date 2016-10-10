package com.ecru;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Vitaliy Ryvakov on 04.10.2016.
 */
public class PrintClipboard {
    private static Connection connection;

    public static void main(String[] args) {

        connectToDataBase("kitchenkonstructor", "root", "root");
        Front[] arrayFronts = arrayFronts();

        System.out.println("Select main front ");
        for (int i = 0; i < arrayFronts.length; i++) {
            System.out.println(i+ ": " +arrayFronts[i].getName());
        }
        Scanner scanner = new Scanner(System.in);
        int selectedFrontIndex = Integer.parseInt(scanner.nextLine());
        //TODO System.out.println("Select addition front:");

        System.out.println("Select color front: ");
        Color[] arrayColors = arrayColors(arrayFronts[selectedFrontIndex].getKod());
        for (int i = 0; i < arrayColors.length; i++) {
            System.out.println(i+ ": " +arrayColors[i].getName());
        }
        int selectedColorIndex = Integer.parseInt(scanner.nextLine());
        //TODO System.out.println("Select addition color:");


        System.out.println("Select color korpus: ");
        Color[] arrayKorpusColors = arrayColors(arrayFronts[87].getKod()); //TODO Magic Number
        for (int i = 0; i < arrayKorpusColors.length; i++) {
            System.out.println(i+ ": " +arrayKorpusColors[i].getName());
        }
        //TODO System.out.println("Select addition color of korpus:");
        int selectedKorpusColorIndex = Integer.parseInt(scanner.nextLine());

        DataSet[] clipboard = splitClipboard();
        for (int i = 0; i < clipboard.length; i++) {
            System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());
            if (clipboard[i].getName().startsWith("FRN")){
                Nomenclature nomenclature = getNomeclatureFront(
                        arrayFronts[selectedFrontIndex].getName(),
                        arrayColors[selectedColorIndex].getKod(),
                        clipboard[i].getName().substring(4));
                System.out.println(nomenclature.getKod()    + "\t" +
                                    nomenclature.getName()  + "\t" +
                                    nomenclature.getPrice() + "\t" +
                                    clipboard[i].getCount() + "\t" +
                                    clipboard[i].getCount()*nomenclature.getPrice());
            }
            if (clipboard[i].getName().startsWith("KOR")){
                System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());
                Nomenclature nomenclature = getNomeclatureKorpus(
                        arrayKorpusColors[selectedKorpusColorIndex].getName(),
                        //KORPUS Color
                        arrayKorpusColors[selectedKorpusColorIndex].getKod(),
                        //KORPUS sizeType
                        clipboard[i].getName().substring(4));
                System.out.println(nomenclature.getKod()    + "\t" +
                        nomenclature.getName()  + "\t" +
                        nomenclature.getPrice() + "\t" +
                        clipboard[i].getCount() + "\t" +
                        clipboard[i].getCount()*nomenclature.getPrice());

            }
        }

    }

    private static Nomenclature getNomeclatureKorpus(String korpusName, String colorKod, String sizeType) {
        Statement statement;
        Nomenclature nomenclature = new Nomenclature("","",0.0);
        String kod = "K04-KORPUS-";
        try {
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT  `color_kod` FROM `kitchenkonstructor`.`colors` WHERE `color_kod` LIKE '%" + colorKod + "%'");
            resultSet.next();
            kod += resultSet.getString(1);
            kod += "-";
            kod += sizeType;
            kod += "-KOR01";
            nomenclature = nomenclatureInSensoPrice(kod);
            if (nomenclature==null){
                System.out.println(kod + " do not finde in price. Correct please:");
                Scanner scanner = new Scanner(System.in);
                System.out.print(kod);
                kod = scanner.nextLine();
                nomenclature = nomenclatureInSensoPrice(kod);
                if (nomenclature==null){
                    nomenclature = new Nomenclature(kod,"do not definathion",0.0);
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomenclature;

    }

    private static Color[] arrayColors(String frontKod) {
        Color []result = new Color[countRows("colors")];
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

    private static Nomenclature getNomeclatureFront(String frontName, String colorKod, String sizeType) {
        Statement statement;
        Nomenclature nomenclature = new Nomenclature("","",0.0);
        String kod = "K04-";
        try {
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT  `kod` FROM `kitchenkonstructor`.`frn` WHERE `name` LIKE '%" + frontName + "%'");
            resultSet.next();
            kod += resultSet.getString(1);
            kod += "-";
            resultSet = statement.executeQuery("SELECT  `color_kod` FROM `kitchenkonstructor`.`colors` WHERE `color_kod` LIKE '%" + colorKod + "%'");
            resultSet.next();
            kod += resultSet.getString(1);
            kod += "-";
            kod += sizeType;
            kod += "-FRN01";
            nomenclature = nomenclatureInSensoPrice(kod);
            if (nomenclature==null){
                System.out.println(kod + " do not finde in price. Correct please:");
                Scanner scanner = new Scanner(System.in);
                System.out.print(kod);
                kod = scanner.nextLine();
                nomenclature = nomenclatureInSensoPrice(kod);
                if (nomenclature==null){
                    nomenclature = new Nomenclature(kod,"do not definathion",0.0);
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomenclature;
    }

    private static Nomenclature nomenclatureInSensoPrice(String kod) {
        Statement statement;
        ResultSet resultSet;
        Nomenclature nomenclature = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
            if (resultSet.next()){
                nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), Float.valueOf(resultSet.getString("price")));
            } else{
                kod = kod.substring(0,kod.length()-1);
                kod+="2";
                resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                if (resultSet.next()){
                    nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), Float.valueOf(resultSet.getString("price")));
                }else {
                    kod = kod.substring(0,kod.length()-2);
                    kod+="+AKC01";
                    resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                    if (resultSet.next()) {
                        nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), Float.valueOf(resultSet.getString("price")));
                    } else{
                        kod = kod.substring(0,kod.length()-1);
                        kod+="2";
                        resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                        if (resultSet.next()) {
                            nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), Float.valueOf(resultSet.getString("price")));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomenclature;
    }

    public static void connectToDataBase(String database, String user, String password) {
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

    private static Front[] arrayFronts() {
        Front []result = new Front[countRows("frn")];

        Statement statement;
        try {
            statement = connection.createStatement();
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

    private static int countRows(String tableName) {
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


    private static DataSet[] splitClipboard() {
        String clipboard = "";
        try {
            clipboard = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            System.out.println("Unsupported data in clipboard");
        }
        String allData[] = clipboard.split("\n");
        String workData[] = new String[allData.length];
        int indexOKData = 0;
        for (String data: allData) {
            if ((data.startsWith("FRN")) || (data.startsWith("KOR")) || (data.startsWith("K04"))){
                workData[indexOKData] = data;
                indexOKData++;
            }
        }
        String workOKData[] = new String[indexOKData];
        System.arraycopy(workData,0,workOKData,0,indexOKData);

        DataSet[] nomenclature = new DataSet[workOKData.length];
        for (int i = 0; i < nomenclature.length; i++) {
            try {
                nomenclature[i] = new DataSet(workOKData[i].split("\t")[0], Integer.parseInt(workOKData[i].split("\t")[1].split(",")[0]));
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Maby buffer is not legal");
            }

        }
        return nomenclature;
    }
}

package com.ecru;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Vitaliy Ryvakov on 10.10.2016.
 */
public class Nomenclature {
    private Connection connection;
    private  String kod;
    private String name;
    private double price;
    private DataBaseManager manager;


    public Nomenclature(String kod, String name, double price) {
        this.kod = kod;
        this.name = name;
        this.price = price;
    }

    public Nomenclature(DataBaseManager manager) {
        this.manager = manager;
        this.connection = manager.connection;
    }

    public String getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Nomenclature getNomeclatureKorpus(String colorKod, String sizeType) {
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
            nomenclature = getNomenclatureByKod(kod);
            if (nomenclature==null){
                System.out.println(kod + " do not finde in price. Correct please:");
                Scanner scanner = new Scanner(System.in);
                System.out.print(kod);
                kod = scanner.nextLine();
                nomenclature = getNomenclatureByKod(kod);
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

    public Nomenclature getNomeclatureFront(String frontName, String colorKod, String sizeType) {
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
            nomenclature = getNomenclatureByKod(kod);
            if (nomenclature==null){
                System.out.println(kod + " do not finde in price. Correct please:");
                Scanner scanner = new Scanner(System.in);
                System.out.print(kod);
                kod = scanner.nextLine();
                nomenclature = getNomenclatureByKod(kod);
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

    public Nomenclature getNomenclatureByKod(String kod) {
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

}

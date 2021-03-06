package com.ecru;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Vitaliy Ryvakov on 10.10.2016.
 */
public class Nomenclature implements Comparable<Nomenclature> {
    private Connection connection;
    private String kod;
    private String name;
    private BigDecimal price;
    private DataBaseManager manager;


    public Nomenclature(String kod, String name, BigDecimal price) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Nomenclature getNomenclatureKorpus(String colorKod, String sizeType) {
        Statement statement;
        Nomenclature nomenclature = new Nomenclature("", "", BigDecimal.ZERO);
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
            if (nomenclature == null) {
                nomenclature = new Nomenclature(kod, "do not definathion", BigDecimal.ZERO);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomenclature;

    }

    public Nomenclature getNomenclatureFront(String frontName, String colorKod, String sizeType) {
        Statement statement;
        Nomenclature nomenclature = new Nomenclature("", "", BigDecimal.ZERO);
        String kod = "K04-";
        try {
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT  `kod` FROM `kitchenkonstructor`.`frn` WHERE `name` LIKE '" + frontName + "'");
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
            if (nomenclature == null) {
                nomenclature = new Nomenclature(kod, "do not definathion", BigDecimal.ZERO);
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
            if (resultSet.next()) {
                nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), BigDecimal.valueOf(Double.valueOf(resultSet.getString("price"))));
            } else {
                kod = kod.substring(0, kod.length() - 1);
                kod += "2";
                resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                if (resultSet.next()) {
                    nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), BigDecimal.valueOf(Double.valueOf(resultSet.getString("price"))));
                } else {
                    kod = kod.substring(0, kod.length() - 2);
                    kod += "+AKC01";
                    resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                    if (resultSet.next()) {
                        nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), BigDecimal.valueOf(Double.valueOf(resultSet.getString("price"))));
                    } else {
                        kod = kod.substring(0, kod.length() - 1);
                        kod += "2";
                        resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price`  FROM `kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '" + kod + "'");
                        if (resultSet.next()) {
                            nomenclature = new Nomenclature(resultSet.getString("kod"), resultSet.getString("name_ukr"), BigDecimal.valueOf(Double.valueOf(resultSet.getString("price"))));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomenclature;
    }

    @Override
    public int compareTo(Nomenclature nomenclature) {
        return this.kod.compareTo(nomenclature.kod); // if (this.kod > nomenclature.kod) {
    }

    public Set<Nomenclature> getNomenclature(String typeKod, String colorKod) {
        Set<Nomenclature> result = new TreeSet<>();
        Nomenclature nomenclature;
        typeKod += colorKod;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT  `kod`, `name_ukr`, `price` FROM " +
                     "`kitchenkonstructor`.`senso_price` WHERE `kod` LIKE '%" + typeKod + "%'")) {
            while (resultSet.next()) {
                String kod = resultSet.getString("kod");
                String name_ukr = resultSet.getString("name_ukr");
                BigDecimal price = BigDecimal.valueOf(Double.valueOf(resultSet.getString("price")));
                nomenclature = new Nomenclature(kod, name_ukr, price);
                result.add(nomenclature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<Nomenclature> getNomenclatureOther() {
        Set<Nomenclature> result = new TreeSet<>();
        Nomenclature nomenclature;

        Set<String> frn = new HashSet<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT `kod` FROM `kitchenkonstructor`.`frn`")) {
            while (resultSet.next()) {
                frn.add(resultSet.getString("kod"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT  `kod`, `name_ukr`, `price` FROM `kitchenkonstructor`.`senso_price` " +
                "WHERE `kod` NOT LIKE '%BLAT%' " +
                "AND `kod` NOT LIKE '%KORPUS%' " +
                "AND `kod` NOT LIKE '%-BLAT_LIS-%' " +
                "AND `kod` NOT LIKE '%-BLAT_LIS/D-%' " +
                "AND `kod` NOT LIKE '%-COKOL-%' ";
        for (String f : frn) {
            sql += "AND `kod` NOT LIKE '%" + f + "%'";
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String kod = resultSet.getString("kod");
                String name_ukr = resultSet.getString("name_ukr");
                BigDecimal price = BigDecimal.valueOf(Double.valueOf(resultSet.getString("price")));
                nomenclature = new Nomenclature(kod, name_ukr, price);
                result.add(nomenclature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

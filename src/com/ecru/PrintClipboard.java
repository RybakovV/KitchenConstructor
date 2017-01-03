package com.ecru;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Vitaliy Ryvakov on 04.10.2016.
 */
public class PrintClipboard {


    private static DataBaseManager manager;
    private static Front front;
    private static Color color;
    private static DataSet dataSet;
    private static Nomenclature nomenclature;
    private static Connection connection;


    public static void main(String[] args) {
        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");

        front = new Front(manager);
        Front[] arrayFronts = front.arrayFronts();

        System.out.println("Select main front ");
        for (int i = 0; i < arrayFronts.length; i++) {
            System.out.println(i+ ": " +arrayFronts[i].getName());
        }
        Scanner scanner = new Scanner(System.in);
        int selectedFrontIndex = Integer.parseInt(scanner.nextLine());
        //TODO System.out.println("Select addition front:");

        System.out.println("Select color front: ");
        color = new Color(manager);
        Color[] arrayColors = color.arrayColors(arrayFronts[selectedFrontIndex].getKod());
        for (int i = 0; i < arrayColors.length; i++) {
            System.out.println(i+ ": " +arrayColors[i].getName());
        }
        int selectedColorIndex = Integer.parseInt(scanner.nextLine());
        //TODO System.out.println("Select addition color:");


        System.out.println("Select color korpus: ");
        Color[] arrayKorpusColors = color.arrayColors("KORPUS");
        for (int i = 0; i < arrayKorpusColors.length; i++) {
            System.out.println(i+ ": " +arrayKorpusColors[i].getName());
        }
        //TODO System.out.println("Select addition color of korpus:");
        int selectedKorpusColorIndex = Integer.parseInt(scanner.nextLine());

        dataSet = new DataSet();
        DataSet[] clipboard = dataSet.splitClipboard();
        for (int i = 0; i < clipboard.length; i++) {
            System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());
            nomenclature = new Nomenclature(manager);
            if (clipboard[i].getName().startsWith("FRN")){
                Nomenclature nomenclatureFront = nomenclature.getNomenclatureFront(
                        arrayFronts[selectedFrontIndex].getName(),
                        arrayColors[selectedColorIndex].getKod(),
                        clipboard[i].getName().substring(4));
                System.out.println(nomenclatureFront.getKod()    + "\t" +
                                    nomenclatureFront.getName()  + "\t" +
                                    nomenclatureFront.getPrice() + "\t" +
                                    clipboard[i].getCount() + "\t" +
                                    nomenclatureFront.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount())));
            }
            if (clipboard[i].getName().startsWith("KOR")){
                System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());
                Nomenclature nomenclatureKorpus = nomenclature.getNomenclatureKorpus(
                        //KORPUS Color
                        arrayKorpusColors[selectedKorpusColorIndex].getKod(),
                        //KORPUS sizeType
                        clipboard[i].getName().substring(4));
                System.out.println(nomenclatureKorpus.getKod()    + "\t" +
                        nomenclatureKorpus.getName()  + "\t" +
                        nomenclatureKorpus.getPrice() + "\t" +
                        clipboard[i].getCount() + "\t" +
                        nomenclatureKorpus.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount())));
            }
            if (clipboard[i].getName().startsWith("K04")||clipboard[i].getName().startsWith("SK")){
                System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());
                Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(clipboard[i].getName());
                System.out.println(nomenclatureFullKod.getKod()    + "\t" +
                        nomenclatureFullKod.getName()  + "\t" +
                        nomenclatureFullKod.getPrice() + "\t" +
                        clipboard[i].getCount() + "\t" +
                        nomenclatureFullKod.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount())));
            }

        }

    }

}

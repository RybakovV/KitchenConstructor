package com.ecru;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Vitaliy Ryvakov on 04.10.2016.
 */
public class PrintClipboard {
    public static void main(String[] args) {
        DataSet[] clipboard = splitClipboard();
        for (int i = 0; i < clipboard.length; i++) {
            System.out.println("Select front:");


            System.out.println(clipboard[i].getName() + " " + clipboard[i].getCount());

        }

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
            nomenclature[i] = new DataSet(workOKData[i].split("\t")[0], Integer.parseInt(workOKData[i].split("\t")[1].split(",")[0]));
        }
        return nomenclature;
    }
}

package com.ecru;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.Arrays;

/**
 * Created by Vitaliy Ryvakov on 04.10.2016.
 */
public class DataSet {

    private String name;
    private int count;

    public DataSet() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public DataSet(String name, int count){
        this.name = name;
        this.count = count;
    }

    public DataSet[] splitClipboard() {
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

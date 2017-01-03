package com.ecru;

import javax.swing.*;

import static com.ecru.GUI.*;


/**
 * Created by Vitaliy Ryvakov on 03.01.2017.
 */
public class JTableUtil {
    public static String[][] getDataFromTable(JTable jTable) {
        String[][] result = new String[jTable.getRowCount()][jTable.getColumnCount()];
        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                String cellData = String.valueOf(jTable.getValueAt(i,j));
                if (cellData.isEmpty()){
                    result[i][j] = "";
                }else {
                    result[i][j] = String.valueOf(jTable.getValueAt(i,j));
                }
            }
        }
        return result;
    }

    static void setColumnWidth(JTable jTable) {
        jTable.getColumnModel().getColumn(NUMBER_ROW).setMaxWidth(NUMBER_ROW_WIDTH);
        jTable.getColumnModel().getColumn(KOD_PRO100).setMaxWidth(KOD_PRO100_WIDTH);
        jTable.getColumnModel().getColumn(TYPE).setMaxWidth(TYPE_WIDTH);
        jTable.getColumnModel().getColumn(COLOR).setMaxWidth(COLOR_WIDTH);
        jTable.getColumnModel().getColumn(ARTIKLE).setMaxWidth(ARTIKLE_WIDTH);
        jTable.getColumnModel().getColumn(NAME).setMaxWidth(NAME_WIDTH);
        jTable.getColumnModel().getColumn(COUNT).setMaxWidth(COUNT_WIDTH);
        jTable.getColumnModel().getColumn(PRICE).setMaxWidth(PRICE_WIDTH);
        jTable.getColumnModel().getColumn(SUM).setMaxWidth(SUM_WIDTH);
    }

}

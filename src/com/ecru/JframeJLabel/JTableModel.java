package com.ecru.JframeJLabel;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Vitaliy Ryvakov on 15.10.2016.
 */
public class JTableModel extends AbstractTableModel {

    private int columnCount = 5;

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}

package com.ecru.JframeJLabel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/** @see http://stackoverflow.com/questions/7350445 */
public class DependentColumn extends JFrame {

    private static final int DEPENDENT_COL = 1;
    private static final int ITEM_COL = 2;
    private static final String[] columnNames = {"Col 1", "Col 2", "Col 3"};

    public static void main(String args[]) throws IOException {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                DependentColumn dc = new DependentColumn();
            }
        });
    }

    public DependentColumn() {
        this.setTitle("Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create Model & Table
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

            @Override
            public Object getValueAt(int row, int col) {
                if (col == DEPENDENT_COL) {
                    return "C2:" + this.getValueAt(row, ITEM_COL);
                } else {
                    return super.getValueAt(row, col);
                }
            }
        };
        for (int i = 0; i < 16; i++) {
            model.addRow(new Object[]{"C1", "C2", "Item1"});
        }
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(320, 120));

        //Create ComboBox
        String[] items = {"Item1", "Item2", "Item3"};
        JComboBox combo = new JComboBox(items);
        TableColumn col = table.getColumnModel().getColumn(ITEM_COL);
        col.setCellEditor(new DefaultCellEditor(combo));
        combo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(e.getItem() + " selected");
                }
            }
        });

        this.add(new JScrollPane(table));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
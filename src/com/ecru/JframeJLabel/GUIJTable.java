package com.ecru.JframeJLabel;


import javax.swing.*;
import java.awt.*;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUIJTable extends JFrame {


    private JTable jTableOrder;
    private JScrollPane jScrollPane;

    private String[] columnNames = {"№", "Назва", "Кількість", "Ціна", "Сума"};
    String[][] data =  {{"1","FRN_393/596_O", "1","",""},
                        {"2","FRN_570/596", "1","",""},
                        {"3","KOR-G_45/72", "1","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","",""}};



    public GUIJTable() {
        super("GUITable");
        setLayout(new FlowLayout());
        jTableOrder = new JTable(data, columnNames);

        jTableOrder.setPreferredScrollableViewportSize(new Dimension(550,500));

        jTableOrder.setFillsViewportHeight(true);

        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);

    }
}

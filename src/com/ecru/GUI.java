package com.ecru;


import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUI extends JFrame {

    private static final int MAXIMUM_USE_FRONT = 3; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR = 0; //TODO Analitics
    private JPanel jPanelFronts;
    private JPanel jPanelColorsFront;

    private JComboBox jComboBoxFronts;
    private JComboBox jComboBoxColorsFront;
    private JLabel jLabel;

    private static DataBaseManager manager;
    private static Front front;
    private static Color color;

    private JTable jTableOrder;
    private JScrollPane jScrollPane;

    private String[] columnNames = {"№", "Назва", "Кількість", "Ціна", "Сума"};
    String[][] data =  {{"1","FRN_393/596_O", "1","",""},
                        {"2","FRN_570/596", "1","",""},
                        {"3","KOR-G_45/72", "1","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","",""}};

    public GUI() {
        super("GUITable");
        setLayout(new FlowLayout());

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");
        front = new Front(manager);
        Front[] fronts = front.arrayFronts();
        color = new Color(manager);


        jComboBoxFronts = new JComboBox(front.getFrontsNames(fronts));
        jComboBoxFronts.setSelectedIndex(MAXIMUM_USE_FRONT);
        jPanelFronts = new JPanel();
        jPanelFronts.setBorder(BorderFactory.createTitledBorder("Виберіть фронт"));
        jPanelFronts.add(jComboBoxFronts);
        add(jPanelFronts);


        Color[] colorsFront = color.arrayColors(fronts[jComboBoxFronts.getSelectedIndex()].getKod());
        jComboBoxColorsFront = new JComboBox(color.getColorsNames(colorsFront));
        jComboBoxColorsFront.setSelectedIndex(MAXIMUM_USE_COLOR);
        jPanelColorsFront = new JPanel();
        jPanelColorsFront.setBorder(BorderFactory.createTitledBorder("Виберіть колір фронту"));
        jPanelColorsFront.add(jComboBoxColorsFront);
        add(jPanelColorsFront);


        jLabel = new JLabel();
        jLabel.setText(String.valueOf(jComboBoxFronts.getSelectedIndex()));
        add(jLabel);

        jTableOrder = new JTable(data, columnNames);

        jTableOrder.setPreferredScrollableViewportSize(new Dimension(550, 500));

        jTableOrder.setFillsViewportHeight(true);

        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);

        jComboBoxFronts.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jLabel.setText(String.valueOf(jComboBoxFronts.getSelectedIndex()));
                jComboBoxColorsFront.removeAllItems();
                Color[] colors = color.arrayColors(fronts[jComboBoxFronts.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(colors);
                for (String colorName: colorsNames) {
                    jComboBoxColorsFront.addItem(colorName);
                }
            }
        });

    }


}

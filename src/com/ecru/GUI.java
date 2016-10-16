package com.ecru;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUI extends JFrame {

    private static final int MAXIMUM_USE_FRONT = 3; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR_FRONT = 0; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR_KORPUS = 3;  //TODO Analitics
    private final JComboBox jComboBoxColorsKorpus;
    private final JPanel jPanelColorsKorpus;
    private final JPanel jPanelButtons;
    private JPanel jPanelFronts;
    private JPanel jPanelColorsFront;
    private JButton jButtonGetFromClipboard;
    private JComboBox jComboBoxFronts;
    private JComboBox jComboBoxColorsFront;
    private JLabel jLabel;

    private static DataBaseManager manager;
    private static Front front;
    private static Color color;

    private JTable jTableOrder;
    private JScrollPane jScrollPane;

    private String[] columnNames = {"№", "PRO100", "Артикул", "Назва", "Кількість", "Ціна", "Вартість"};
    String[][] data =  {{"1","FRN_393/596_O", "K04-ML_NORDE-DCN-393/596_O-FRN02", "Фасад 36th Norde Avenue Дуб Canadian 393/596_O","1","325.25", "325.25"},
                        {"2","FRN_570/596", "","","","",""},
                        {"3","KOR-G_45/72", "1","","","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","","","",""}};

    public GUI() {
        super("GUITable");
        setLayout(new FlowLayout());

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");
        front = new Front(manager);
        Front[] fronts = front.arrayFronts();
        color = new Color(manager);
        Dimension dimensionPanels = new Dimension(320,60);
        Dimension dimensionComboBox = new Dimension(295,25);

        jComboBoxFronts = new JComboBox(front.getFrontsNames(fronts));
        jComboBoxFronts.setSelectedIndex(MAXIMUM_USE_FRONT);
        jComboBoxFronts.setPreferredSize(dimensionComboBox);
        jPanelFronts = new JPanel();
        jPanelFronts.setPreferredSize(dimensionPanels);
        jPanelFronts.setBorder(BorderFactory.createTitledBorder("Виберіть фронт"));
        jPanelFronts.add(jComboBoxFronts);
        add(jPanelFronts);


        Color[] colorsFront = color.arrayColors(fronts[jComboBoxFronts.getSelectedIndex()].getKod());
        jComboBoxColorsFront = new JComboBox(color.getColorsNames(colorsFront));
        jComboBoxColorsFront.setSelectedIndex(MAXIMUM_USE_COLOR_FRONT);
        jComboBoxColorsFront.setPreferredSize(dimensionComboBox);
        jPanelColorsFront = new JPanel();
        jPanelColorsFront.setPreferredSize(dimensionPanels);
        jPanelColorsFront.setBorder(BorderFactory.createTitledBorder("Виберіть колір фронту"));
        jPanelColorsFront.add(jComboBoxColorsFront);
        add(jPanelColorsFront);

        Color[] colorsKorpus = color.arrayColors("KORPUS");
        jComboBoxColorsKorpus = new JComboBox(color.getColorsNames(colorsKorpus));
        jComboBoxColorsKorpus.setSelectedIndex(MAXIMUM_USE_COLOR_KORPUS);
        jComboBoxColorsKorpus.setPreferredSize(dimensionComboBox);
        jPanelColorsKorpus = new JPanel();
        jPanelColorsKorpus.setPreferredSize(dimensionPanels);
        jPanelColorsKorpus.setBorder(BorderFactory.createTitledBorder("Виберіть колір корпусу"));
        jPanelColorsKorpus.add(jComboBoxColorsKorpus);
        add(jPanelColorsKorpus);

        jPanelButtons = new JPanel();
        jPanelButtons.setPreferredSize(new Dimension(967, 54));
        jPanelButtons.setBorder(BorderFactory.createTitledBorder(""));
        jButtonGetFromClipboard = new JButton(new ImageIcon("editpaste_32.png"));
        jButtonGetFromClipboard.setToolTipText("Вставити з буферу обміну");
        jPanelButtons.add(jButtonGetFromClipboard);
        add(jPanelButtons);

        //data = getDataFromClipboard();
        jTableOrder = new JTable(data, columnNames);
        jTableOrder.setPreferredScrollableViewportSize(new Dimension(800, 700));
        jTableOrder.setFillsViewportHeight(true);
        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);

        jComboBoxFronts.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
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

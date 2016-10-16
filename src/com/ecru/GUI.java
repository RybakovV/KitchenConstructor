package com.ecru;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
    private String[][] data = {{"1","FRN_393/596_O", "K04-ML_NORDE-DCN-393/596_O-FRN02", "Фасад 36th Norde Avenue Дуб Canadian 393/596_O","1","325.25", "325.25"},
                        {"2","FRN_570/596", "","","","",""},
                        {"3","KOR-G_45/72", "1","","","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","","","",""}};

    public GUI() {
        super("GUITable");
        setLayout(new FlowLayout());

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");
        front = new Front(manager);
        Front[] arrayFronts = front.arrayFronts();
        color = new Color(manager);
        Dimension dimensionPanels = new Dimension(320,60);
        Dimension dimensionComboBox = new Dimension(295,25);

        jComboBoxFronts = new JComboBox(front.getFrontsNames(arrayFronts));
        jComboBoxFronts.setSelectedIndex(MAXIMUM_USE_FRONT);
        jComboBoxFronts.setPreferredSize(dimensionComboBox);
        jPanelFronts = new JPanel();
        jPanelFronts.setPreferredSize(dimensionPanels);
        jPanelFronts.setBorder(BorderFactory.createTitledBorder("Виберіть фронт"));
        jPanelFronts.add(jComboBoxFronts);
        add(jPanelFronts);


        Color[] arrayColors = color.arrayColors(arrayFronts[jComboBoxFronts.getSelectedIndex()].getKod());
        jComboBoxColorsFront = new JComboBox(color.getColorsNames(arrayColors));
        jComboBoxColorsFront.setSelectedIndex(MAXIMUM_USE_COLOR_FRONT);
        jComboBoxColorsFront.setPreferredSize(dimensionComboBox);
        jPanelColorsFront = new JPanel();
        jPanelColorsFront.setPreferredSize(dimensionPanels);
        jPanelColorsFront.setBorder(BorderFactory.createTitledBorder("Виберіть колір фронту"));
        jPanelColorsFront.add(jComboBoxColorsFront);
        add(jPanelColorsFront);

        Color[] arrayKorpusColors = color.arrayColors("KORPUS");
        jComboBoxColorsKorpus = new JComboBox(color.getColorsNames(arrayKorpusColors));
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

        jTableOrder = new JTable(data, columnNames);
        jTableOrder.setPreferredScrollableViewportSize(new Dimension(800, 700));
        jTableOrder.setFillsViewportHeight(true);
        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);


        jButtonGetFromClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataSet dataSet = new DataSet();
                DataSet[] clipboard = dataSet.splitClipboard();
                String[][] data = new String[clipboard.length][columnNames.length];
                for (int i = 0; i < clipboard.length; i++) {
                    data[i][0] = String.valueOf(i);
                    data[i][1] = clipboard[i].getName();
                    Nomenclature nomenclature = new Nomenclature(manager);
                    if (clipboard[i].getName().startsWith("FRN")){
                        Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                                arrayFronts[jComboBoxFronts.getSelectedIndex()].getName(),
                                arrayColors[jComboBoxColorsFront.getSelectedIndex()].getKod(),
                                clipboard[i].getName().substring(4));
                        data[i][2] = nomenclatureFront.getKod();
                        data[i][3] = nomenclatureFront.getName();
                        data[i][5] = String.valueOf(nomenclatureFront.getPrice());
                        data[i][6] = String.valueOf(clipboard[i].getCount()*nomenclatureFront.getPrice());
                    }
                    if (clipboard[i].getName().startsWith("KOR")){
                        Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                                arrayKorpusColors[jComboBoxColorsKorpus.getSelectedIndex()].getKod(),
                                clipboard[i].getName().substring(4));
                        data[i][2] = nomenclatureKorpus.getKod();
                        data[i][3] = nomenclatureKorpus.getName();
                        data[i][5] = String.valueOf(nomenclatureKorpus.getPrice());
                        data[i][6] = String.valueOf(clipboard[i].getCount()*nomenclatureKorpus.getPrice());
                    }
                    if (clipboard[i].getName().startsWith("K04")||clipboard[i].getName().startsWith("SK")){
                        Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(clipboard[i].getName());
                        data[i][2] = nomenclatureFullKod.getKod();
                        data[i][3] = nomenclatureFullKod.getName();
                        data[i][5] = String.valueOf(nomenclatureFullKod.getPrice());
                        data[i][6] = String.valueOf(clipboard[i].getCount()*nomenclatureFullKod.getPrice());
                    }
                    data[i][4] = String.valueOf(clipboard[i].getCount());
                }
                for (int i = 0; i < data.length ; i++) {
                    for (int j = 0; j < data[i].length ; j++) {
                        System.out.print(data[i][j]+"\t");
                    }
                    System.out.println();

                }
                //jTableOrder.removeAll();

                DefaultTableModel model = new DefaultTableModel(data, columnNames);// jTableOrder.getModel();
                jTableOrder.setModel(model);


            }
        });
        jComboBoxFronts.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsFront.removeAllItems();
                Color[] colors = color.arrayColors(arrayFronts[jComboBoxFronts.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(colors);
                for (String colorName: colorsNames) {
                    jComboBoxColorsFront.addItem(colorName);
                }
            }
        });

    }


}

package com.ecru;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import static com.ecru.GUI.*;


/**
 * Created by Vitaliy Ryvakov on 02.01.2017.
 */
public class GUIAddRow extends JFrame{

    private final JLabel jLabelType;
    private final JComboBox jComboBoxType;
    private final JComboBox jComboBoxColorsTableTop;
    private final JLabel statusLabel;
    private final JTable jTableNomeklature;
    private final JScrollPane jScrollPanel;
    private String[] typesNomenсlature = {"", "Корпус", "Фасад", "Стільниця"};
    private JComboBox jComboBoxColorsKorpus;
    private JComboBox jComboBoxFront;
    private JComboBox jComboBoxColorsFront;

    private String[] columnNames = {"Код", "Назва", "Ціна"};
    String[][] data =  {{"K04-BLAT-BUS-POW_600_GR_38-BLA01","Стільниця погонна BUS  від 600 товщина 38", "0"}};

    public GUIAddRow(){
        super("Додати номенклатуру");
        setLayout(new FlowLayout());
        jLabelType = new JLabel("Тип номенклатури");

        jComboBoxType = new JComboBox(typesNomenсlature);
        add(jLabelType);
        add(jComboBoxType);


        Color[] arrayKorpusColors = color.arrayColors("KORPUS");
        jComboBoxColorsKorpus = new JComboBox(color.getColorsNames(arrayKorpusColors));
        jComboBoxColorsKorpus.setVisible(false);
        add(jComboBoxColorsKorpus);


        Front front = new Front(manager);
        Front[] arrayFronts = front.arrayFronts();
        jComboBoxFront = new JComboBox(front.getFrontsNames(arrayFronts));
        jComboBoxFront.setSelectedIndex(MAXIMUM_USE_FRONT);
        jComboBoxFront.setVisible(false);
        add(jComboBoxFront);
        Color[] arrayColorsFront = color.arrayColors(arrayFronts[jComboBoxFront.getSelectedIndex()].getKod());
        jComboBoxColorsFront = new JComboBox(color.getColorsNames(arrayColorsFront));
        jComboBoxColorsFront.setSelectedIndex(MAXIMUM_USE_COLOR_FRONT);
        jComboBoxColorsFront.setVisible(false);
        add(jComboBoxColorsFront);

        Color[] arrayColorsTableTop = color.arrayColors("BLAT");
        jComboBoxColorsTableTop = new JComboBox(color.getColorsNames(arrayColorsTableTop));
        jComboBoxColorsTableTop.setVisible(false);
        add(jComboBoxColorsTableTop);

        statusLabel = new JLabel("status");
        add(statusLabel);

        jTableNomeklature = new JTable(data, columnNames);
        jTableNomeklature.setPreferredScrollableViewportSize(new Dimension(800,400));

        jTableNomeklature.setFillsViewportHeight(true);

        jScrollPanel = new JScrollPane(jTableNomeklature);
        add(jScrollPanel);



        jComboBoxColorsKorpus.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Nomenclature nomenclature = new Nomenclature(manager);
                Set<Nomenclature> dataSet =  nomenclature.getNomenclatureKorpus(arrayKorpusColors[jComboBoxColorsKorpus.getSelectedIndex()].getKod());
                data = new String[dataSet.size()][3];
                int indexData = 0;
                //data[indexData] = columnNames;
                for (Nomenclature d: dataSet){
                    data[indexData][0] = d.getKod();
                    data[indexData][1] = d.getName();
                    data[indexData][2] = String.valueOf(d.getPrice());
                    indexData++;
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames){
                  @Override
                  public boolean isCellEditable(int row, int column) {
                      //all cells false
                      return false;
                  }
                };

                jTableNomeklature.setModel(model);
                //jTableNomeklature.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            }
        });

        jComboBoxType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (jComboBoxType.getSelectedIndex()){
                    case 1: {
                        jComboBoxColorsKorpus.setVisible(true);
                        jComboBoxFront.setVisible(false);
                        jComboBoxColorsFront.setVisible(false);
                        break;
                    }
                    case 2: {
                        jComboBoxFront.setVisible(true);
                        jComboBoxColorsFront.setVisible(true);
                        jComboBoxColorsKorpus.setVisible(false);

                        break;
                    }
                    case 3: {
                        jComboBoxFront.setVisible(false);
                        jComboBoxColorsFront.setVisible(false);
                        jComboBoxColorsKorpus.setVisible(false);
                        jComboBoxColorsTableTop.setVisible(true);

                        break;
                    }

                }
            }
        });
    }
}

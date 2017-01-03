package com.ecru;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Set;

import static com.ecru.GUI.*;
import static com.ecru.JTableUtil.*;


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
    private Color[] arrayColorsFront;
    private String[] typesNomenсlature = {"", "Корпус", "Фасад", "Стільниця"};
    private JComboBox jComboBoxColorsKorpus;
    private JComboBox jComboBoxFront;
    private JComboBox jComboBoxColorsFront;

    private String[] columnNamesTableNomenclature = {"Код", "Назва", "Ціна"};
    String[][] data =  {{"K04-BLAT-BUS-POW_600_GR_38-BLA01","Стільниця погонна BUS  від 600 товщина 38", "0"}};

    private Set<Nomenclature> dataSet;

    public GUIAddRow(JTable jTableOrder){
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
        arrayColorsFront = color.arrayColors(arrayFronts[jComboBoxFront.getSelectedIndex()].getKod());
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

        jTableNomeklature = new JTable(data, columnNamesTableNomenclature);
        jTableNomeklature.setPreferredScrollableViewportSize(new Dimension(800,400));
        jTableNomeklature.setFillsViewportHeight(true);
        jScrollPanel = new JScrollPane(jTableNomeklature);
        add(jScrollPanel);


        jComboBoxColorsFront.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Nomenclature nomenclature = new Nomenclature(manager);
                    if (jComboBoxColorsFront.getItemCount() == 0) {
                        dataSet =  nomenclature.getNomenclatureFrontByType(
                                arrayFronts[jComboBoxFront.getSelectedIndex()].getKod());
                    }else{
                        dataSet =  nomenclature.getNomenclatureFrontByTypeAndColor(
                                arrayFronts[jComboBoxFront.getSelectedIndex()].getKod(),
                                arrayColorsFront[jComboBoxColorsFront.getSelectedIndex()].getKod());
                    }
                    data = new String[dataSet.size()][columnNamesTableNomenclature.length];
                    int indexData = 0;
                    for (Nomenclature d: dataSet){
                        data[indexData][0] = d.getKod();
                        data[indexData][1] = d.getName();
                        data[indexData][2] = String.valueOf(d.getPrice());
                        indexData++;
                    }
                    DefaultTableModel model = new DefaultTableModel(data, columnNamesTableNomenclature){
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells false
                            return false;
                        }
                    };
                    jTableNomeklature.setModel(model);
                }
            }
        );

        jComboBoxFront.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsFront.removeAllItems();
                arrayColorsFront = color.arrayColors(arrayFronts[jComboBoxFront.getSelectedIndex()].getKod());
                String[] colorsNames = color.getColorsNames(arrayColorsFront);
                for (int i = 0; i < colorsNames.length; i++) {
                    jComboBoxColorsFront.addItem(colorsNames[i]);
                }
            }
        });

        jTableNomeklature.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    statusLabel.setText("clicked " + row + " row with nomenclature (" + data[row][0] +") " + data[row][1]);
                    insertNomenclature(data[row], jTableOrder);
                }
            }
        });

        jComboBoxColorsKorpus.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Nomenclature nomenclature = new Nomenclature(manager);
                dataSet =  nomenclature.getNomenclatureKorpusByColor(arrayKorpusColors[jComboBoxColorsKorpus.getSelectedIndex()].getKod());
                data = new String[dataSet.size()][columnNamesTableNomenclature.length];
                int indexData = 0;
                for (Nomenclature d: dataSet){
                    data[indexData][0] = d.getKod();
                    data[indexData][1] = d.getName();
                    data[indexData][2] = String.valueOf(d.getPrice());
                    indexData++;
                }

                DefaultTableModel model = new DefaultTableModel(data, columnNamesTableNomenclature){
                  @Override
                  public boolean isCellEditable(int row, int column) {
                      //all cells false
                      return false;
                  }
                };
                jTableNomeklature.setModel(model);
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

    private void insertNomenclature(String[] row, JTable jTable) {

        int selectedRow = jTable.getSelectedRow();
        if (selectedRow ==  -1) {
            selectedRow = jTable.getRowCount()-2;
        }
        int selectedColumn = jTable.getSelectedColumn();
        if (selectedColumn == -1) {
            selectedColumn = 0;
        }
        copyTableRow(jTable, selectedColumn, selectedRow);
        selectedRow++;
        int count = 1;
        //jTable.setValueAt(nomenclature.getKod(), selectedRow, ARTIKLE);
        jTable.setValueAt(row[0], selectedRow, ARTIKLE);
        //jTable.setValueAt(nomenclature.getName(), selectedRow, NAME);
        jTable.setValueAt(row[1], selectedRow, NAME);
        //jTable.setValueAt(String.valueOf(nomenclature.getPrice()), selectedRow, PRICE);
        jTable.setValueAt(row[2], selectedRow, PRICE);
        //BigDecimal sum = nomenclature.getPrice().multiply(BigDecimal.valueOf(count));
        BigDecimal sum = BigDecimal.valueOf(Double.valueOf(String.valueOf(row[2]))).multiply(BigDecimal.valueOf(count));
        jTable.setValueAt(String.valueOf(sum), selectedRow, SUM);
        sum = getTotalSum(jTable);
        jTable.setValueAt(String.valueOf(sum), jTable.getRowCount()-1, GUI.SUM);
    }

    private void copyTableRow(JTable jTable, int column, int row){
        int selectedRow = row;
        int selectedColumn = column;
        String[][] dataInTable = getDataFromTable(jTable);
        String[][] newData = new String[dataInTable.length+1][dataInTable[0].length];
        for (int i = 0; i <= selectedRow; i++) {
            newData[i] = dataInTable[i];
        }
        newData[selectedRow+1] = new String[dataInTable[0].length];
        for (int i = 0; i < dataInTable[0].length ; i++) {
            newData[selectedRow+1][i] = dataInTable[selectedRow][i];
        }
        newData[selectedRow+1][NUMBER_ROW] = String.valueOf(selectedRow+2);
        for (int i = selectedRow+2; i < newData.length; i++) {
            newData[i] = dataInTable[i-1];
            newData[i][NUMBER_ROW] = String.valueOf(i+1);
        }
        newData[newData.length-1][NUMBER_ROW] = "";
        newData[newData.length-1][KOD_PRO100] = "";
        newData[newData.length-1][TYPE] = "";
        newData[newData.length-1][COLOR] = "";
        newData[newData.length-1][ARTIKLE] = "";
        newData[newData.length-1][NAME] = "";
        newData[newData.length-1][COUNT] = "";
        dataInTable = newData;
        dataInTable[dataInTable.length-1][SUM] = String.valueOf(getTotalSum(jTable));

        String[] columnNames = getColumnNames(jTable);

        DefaultTableModel model = new DefaultTableModel(dataInTable, columnNames);
        jTable.setModel(model);
        setColumnWidth(jTable);
        jTable.addRowSelectionInterval(selectedRow+1,selectedRow+1);

        jTable.addColumnSelectionInterval(selectedColumn,selectedColumn);
        //jComboBoxFrontInTable.removeAll();
        //jComboBoxFrontInTable.addItem(front.getFrontsNames(arrayFronts));
        TableColumn typeColumn = jTable.getColumnModel().getColumn(TYPE);
        //typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));

    }

    private String[] getColumnNames(JTable jTable) {
        int columnCount = jTable.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount ; i++) {
            columnNames[i] = jTable.getColumnName(i);
        }
        return columnNames;
    }


    public BigDecimal getTotalSum(JTable jTable) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int row = 0; row < jTable.getRowCount()-1 ; row++) {
            String price = String.valueOf(jTable.getValueAt(row, SUM));
            if (!price.isEmpty()){
                sum = sum.add(BigDecimal.valueOf(Double.valueOf(price)));
            }
        }
        return sum;
    }

}

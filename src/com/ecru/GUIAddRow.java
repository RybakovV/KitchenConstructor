package com.ecru;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static com.ecru.GUI.*;
import static com.ecru.JTableUtil.*;


/**
 * Created by Vitaliy Ryvakov on 02.01.2017.
 */
public class GUIAddRow extends JFrame{

    private final JLabel jLabelType;
    private final JComboBox jComboBoxType;
    private final JComboBox jComboBoxColorsBlat;
    private final JComboBox jComboBoxColorsCokol;
    private JComboBox jComboBoxColorsBlatLis;

    private final JLabel statusLabel;
    private final JTable jTableNomeklature;
    private final JScrollPane jScrollPanel;
    private Color[] arrayColorsFront;
    private String[] typesNomenсlature = {"", "Корпус", "Фасад", "Стільниця", "Плінтус", "Цоколь", "Інше"};
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

        Color[] arrayColorsBlat = color.arrayColors("BLAT");
        jComboBoxColorsBlat = new JComboBox(color.getColorsNames(arrayColorsBlat));
        jComboBoxColorsBlat.setVisible(false);
        add(jComboBoxColorsBlat);

        Color[] arrayColorsBlatLis = color.arrayColors("BLAT_LIS");
        jComboBoxColorsBlatLis = new JComboBox(color.getColorsNames(arrayColorsBlatLis));
        jComboBoxColorsBlatLis.setVisible(false);
        add(jComboBoxColorsBlatLis);

        Color[] arrayColorsCokol = color.arrayColors("COKOL");
        jComboBoxColorsCokol = new JComboBox(color.getColorsNames(arrayColorsCokol));
        jComboBoxColorsCokol.setVisible(false);
        add(jComboBoxColorsCokol);

        statusLabel = new JLabel("status");
        add(statusLabel);


        jTableNomeklature = new JTable(data, columnNamesTableNomenclature);
        jTableNomeklature.setPreferredScrollableViewportSize(new Dimension(800,400));
        jTableNomeklature.setFillsViewportHeight(true);
        jScrollPanel = new JScrollPane(jTableNomeklature);
        add(jScrollPanel);

        jComboBoxColorsCokol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nomenclature nomenclature = new Nomenclature(manager);
                Set<Nomenclature> dataSetColorsCokol =  nomenclature.getNomenclature("-COKOL-",
                        arrayColorsCokol[jComboBoxColorsCokol.getSelectedIndex()].getKod());
                dataSet = new TreeSet<Nomenclature>();
                dataSet.addAll(dataSetColorsCokol);
                data = getArrayFromSet(dataSet);

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

        jComboBoxColorsBlatLis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nomenclature nomenclature = new Nomenclature(manager);
                Set<Nomenclature> dataSetColorsLis =  nomenclature.getNomenclature("-BLAT_LIS-",
                        arrayColorsBlatLis[jComboBoxColorsBlatLis.getSelectedIndex()].getKod());
                Set<Nomenclature> dataSetColorsD = nomenclature.getNomenclature("-BLAT_LIS/D-",
                        arrayColorsBlatLis[jComboBoxColorsBlatLis.getSelectedIndex()].getKod());
                dataSet = new TreeSet<Nomenclature>();

                dataSet.addAll(dataSetColorsLis);
                dataSet.addAll(dataSetColorsD);
                data = getArrayFromSet(dataSet);

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

        jComboBoxColorsBlat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nomenclature nomenclature = new Nomenclature(manager);
                dataSet =  nomenclature.getNomenclature("-BLAT-", arrayColorsBlat[jComboBoxColorsBlat.getSelectedIndex()].getKod());
                data = getArrayFromSet(dataSet);

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

        jComboBoxColorsFront.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Nomenclature nomenclature = new Nomenclature(manager);
                    if (jComboBoxColorsFront.getItemCount() == 0) {
                        dataSet =  nomenclature.getNomenclature("K04-",
                                arrayFronts[jComboBoxFront.getSelectedIndex()].getKod());
                    }else{
                        dataSet =  nomenclature.getNomenclature("K04-",
                                arrayFronts[jComboBoxFront.getSelectedIndex()].getKod() + "-" +
                                arrayColorsFront[jComboBoxColorsFront.getSelectedIndex()].getKod());
                    }
                    data = getArrayFromSet(dataSet);
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
                dataSet =  nomenclature.getNomenclature("K04-KORPUS-", arrayKorpusColors[jComboBoxColorsKorpus.getSelectedIndex()].getKod());
                data = getArrayFromSet(dataSet);
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
                        setUnvisibleAllComboBox();
                        jComboBoxColorsKorpus.setVisible(true);
                        break;
                    }
                    case 2: {
                        setUnvisibleAllComboBox();
                        jComboBoxFront.setVisible(true);
                        jComboBoxColorsFront.setVisible(true);
                        break;
                    }
                    case 3: {
                        setUnvisibleAllComboBox();
                        jComboBoxColorsBlat.setVisible(true);
                        break;
                    }
                    case 4:{
                        setUnvisibleAllComboBox();
                        jComboBoxColorsBlatLis.setVisible(true);
                        break;
                    }
                    case 5: {
                        setUnvisibleAllComboBox();
                        jComboBoxColorsCokol.setVisible(true);
                        break;

                    }
                    case 6:{
                        setUnvisibleAllComboBox();

                        Nomenclature nomenclature = new Nomenclature(manager);
                        dataSet =  nomenclature.getNomenclatureOther();
                        data = getArrayFromSet(dataSet);
                        DefaultTableModel model = new DefaultTableModel(data, columnNamesTableNomenclature){
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                //all cells false
                                return false;
                            }
                        };
                        jTableNomeklature.setModel(model);

                        break;
                    }
                }
            }
        });
    }

    private void setUnvisibleAllComboBox() {
        jComboBoxFront.setVisible(false);
        jComboBoxColorsFront.setVisible(false);
        jComboBoxColorsKorpus.setVisible(false);
        jComboBoxColorsBlat.setVisible(false);
        jComboBoxColorsBlatLis.setVisible(false);
        jComboBoxColorsCokol.setVisible(false);
    }

    private String[][] getArrayFromSet(Set<Nomenclature> dataSet) {
        String[][] data = new String[dataSet.size()][columnNamesTableNomenclature.length];
        int indexData = 0;
        for (Nomenclature d: dataSet){
            data[indexData][0] = d.getKod();
            data[indexData][1] = d.getName();
            data[indexData][2] = String.valueOf(d.getPrice());
            indexData++;
        }
        return data;
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
        jTable.setValueAt(row[0], selectedRow, ARTIKLE);
        jTable.setValueAt(row[1], selectedRow, NAME);
        jTable.setValueAt(row[2], selectedRow, PRICE);
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
        TableColumn typeColumn = jTable.getColumnModel().getColumn(TYPE);

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

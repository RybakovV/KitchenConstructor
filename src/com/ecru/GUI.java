package com.ecru;




import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUI extends JFrame {

    private static final int MAXIMUM_USE_FRONT = 3; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR_FRONT = 0; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR_KORPUS = 3;  //TODO Analitics

    private final JComboBox jComboBoxColorsMainKorpus;
    private final JPanel jPanelColorsKorpus;
    private final JPanel jPanelButtons;
    private final JLabel jLabelMainFront;
    private final JLabel jLabelAditionFront;
    private final JComboBox jComboBoxAdditionFront;
    private final JLabel jLabelColorsAdditionFront;
    private final JComboBox jComboBoxColorsAdditionFront;
    private final JLabel jLabelColorsMainFront;
    private final JLabel jLabelColorsMainKorpus;
    private final JLabel jLabelColorsAdditionKorpus;
    private final JComboBox jComboBoxColorsAdditionKorpus;
    private final JButton jButtonAddRows;
    private final Front[] arrayFronts;
    private final JComboBox jComboBoxFrontInTable;
    private JComboBox jComboBoxColorsFrontInTable;
    private TableColumn typeColumn;
    private Color[] arrayColorsMainFront;
    private Color[] arrayColorsAdditionFront;
    private final Color[] arrayKorpusColors;
    private JPanel jPanelFronts;
    private JPanel jPanelColorsFront;
    private JButton jButtonGetFromClipboard;
    private JComboBox jComboBoxMainFront;
    private JComboBox jComboBoxColorsMainFront;
    private JLabel jLabel;

    private static DataBaseManager manager;
    private static Front front;
    private static Color color;

    private JTable jTableOrder;
    private JScrollPane jScrollPane;


    private static final int NUMBER_ROW = 0;
    private static final int NUMBER_ROW_WIDTH = 20;
    private static final int KOD_PRO100 = 1;
    private static final int KOD_PRO100_WIDTH = 150;
    private static final int TYPE = 2;
    private static final int TYPE_WIDTH = 150;
    private static final int COLOR = 3;
    private static final int COLOR_WIDTH = 150;
    private static final int ARTIKLE = 4;
    private static final int ARTIKLE_WIDTH = 200;
    private static final int NAME = 5;
    private static final int NAME_WIDTH = 300;
    private static final int COUNT = 6;
    private static final int COUNT_WIDTH = 20;
    private static final int PRICE = 7;
    private static final int PRICE_WIDTH = 50;
    private static final int SUM = 8;
    private static final int SUM_WIDTH = 75;
    private String[] columnNames = {"№", "PRO100","Фронт/Корпус", "Колір", "Артикул", "Назва", "Кількість", "Ціна", "Вартість"};
    private String[][] data = {{"1","FRN_393/596_O","36th Norde Avenue", "Дуб Canadian", "K04-ML_NORDE-DCN-393/596_O-FRN02", "Фасад 36th Norde Avenue Дуб Canadian 393/596_O","1","325.25", "325.25"},
                        {"2","FRN_570/596", "","","","","","",""},
                        {"3","KOR-G_45/72", "1","","","","","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","","","","","",""}};
    private double sum;

    public GUI() {
        super("GUITable");
        setLayout(new FlowLayout());

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");
        front = new Front(manager);
        arrayFronts = front.arrayFronts();
        color = new Color(manager);
        Dimension dimensionPanels = new Dimension(320,133);
        Dimension dimensionComboBox = new Dimension(295,25);

        jLabelMainFront = new JLabel("Основний");
        jComboBoxMainFront = new JComboBox(front.getFrontsNames(arrayFronts));
        jComboBoxMainFront.setSelectedIndex(MAXIMUM_USE_FRONT);
        jComboBoxMainFront.setPreferredSize(dimensionComboBox);

        jLabelAditionFront = new JLabel("Додатковий");
        jComboBoxAdditionFront = new JComboBox(front.getFrontsNames(arrayFronts));
        jComboBoxAdditionFront.addItem("Виберіть додатковий фронт");
        jComboBoxAdditionFront.setSelectedIndex(arrayFronts.length);
        jComboBoxAdditionFront.setPreferredSize(dimensionComboBox);

        jPanelFronts = new JPanel();
        jPanelFronts.setPreferredSize(dimensionPanels);
        jPanelFronts.setBorder(BorderFactory.createTitledBorder("Виберіть фронт"));
        jPanelFronts.add(jLabelMainFront);
        jPanelFronts.add(jComboBoxMainFront);
        jPanelFronts.add(jLabelAditionFront);
        jPanelFronts.add(jComboBoxAdditionFront);
        add(jPanelFronts);

        arrayColorsMainFront = color.arrayColors(arrayFronts[jComboBoxMainFront.getSelectedIndex()].getKod());

        jLabelColorsMainFront = new JLabel("Основний");
        jComboBoxColorsMainFront = new JComboBox(color.getColorsNames(arrayColorsMainFront));
        jComboBoxColorsMainFront.setSelectedIndex(MAXIMUM_USE_COLOR_FRONT);
        jComboBoxColorsMainFront.setPreferredSize(dimensionComboBox);

        jLabelColorsAdditionFront = new JLabel("Додатковий");
        jComboBoxColorsAdditionFront = new JComboBox();
        jComboBoxColorsAdditionFront.addItem("Виберіть колір додаткового фронту");
        jComboBoxColorsAdditionFront.setPreferredSize(dimensionComboBox);
        jPanelColorsFront = new JPanel();
        jPanelColorsFront.setPreferredSize(dimensionPanels);
        jPanelColorsFront.setBorder(BorderFactory.createTitledBorder("Виберіть колір фронту"));
        jPanelColorsFront.add(jLabelColorsMainFront);
        jPanelColorsFront.add(jComboBoxColorsMainFront);
        jPanelColorsFront.add(jLabelColorsAdditionFront);
        jPanelColorsFront.add(jComboBoxColorsAdditionFront);
        add(jPanelColorsFront);

        arrayKorpusColors = color.arrayColors("KORPUS");
        jLabelColorsMainKorpus = new JLabel("Основний");
        jComboBoxColorsMainKorpus = new JComboBox(color.getColorsNames(arrayKorpusColors));
        jComboBoxColorsMainKorpus.setSelectedIndex(MAXIMUM_USE_COLOR_KORPUS);
        jComboBoxColorsMainKorpus.setPreferredSize(dimensionComboBox);

        jLabelColorsAdditionKorpus = new JLabel("Додатковий");
        jComboBoxColorsAdditionKorpus = new JComboBox(color.getColorsNames(arrayKorpusColors));
        jComboBoxColorsAdditionKorpus.addItem("Виберіть додатковий колір корпусу");
        jComboBoxColorsAdditionKorpus.setSelectedIndex(arrayKorpusColors.length);
        jComboBoxColorsAdditionKorpus.setPreferredSize(dimensionComboBox);


        jPanelColorsKorpus = new JPanel();
        jPanelColorsKorpus.setPreferredSize(dimensionPanels);
        jPanelColorsKorpus.setBorder(BorderFactory.createTitledBorder("Виберіть колір корпусу"));
        jPanelColorsKorpus.add(jLabelColorsMainKorpus);
        jPanelColorsKorpus.add(jComboBoxColorsMainKorpus);
        jPanelColorsKorpus.add(jLabelColorsAdditionKorpus);
        jPanelColorsKorpus.add(jComboBoxColorsAdditionKorpus);
        add(jPanelColorsKorpus);

        jPanelButtons = new JPanel();
        jPanelButtons.setPreferredSize(new Dimension(967, 54));
        jPanelButtons.setBorder(BorderFactory.createTitledBorder(""));
        jButtonGetFromClipboard = new JButton(new ImageIcon("editpaste_32.png"));
        jButtonGetFromClipboard.setToolTipText("Вставити з буферу обміну");
        jPanelButtons.add(jButtonGetFromClipboard);
        jButtonAddRows = new JButton(new ImageIcon("add_32.png"));
        jButtonAddRows.setToolTipText("Добавити рядок");
        jPanelButtons.add(jButtonAddRows);
        jLabel = new JLabel("rcwercw");
        jPanelButtons.add(jLabel);
        add(jPanelButtons);

        jTableOrder = new JTable(data, columnNames);
        jTableOrder.setPreferredScrollableViewportSize(new Dimension(967, 600));
        jTableOrder.setFillsViewportHeight(true);
        setColumnWidth();
        jComboBoxFrontInTable = new JComboBox(front.getFrontsNames(arrayFronts));
        jComboBoxColorsFrontInTable = new JComboBox(color.getColorsNames(arrayColorsMainFront));
        typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));
        typeColumn = jTableOrder.getColumnModel().getColumn(COLOR);
        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxColorsFrontInTable));

        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);
        jTableOrder.setCellSelectionEnabled(true);//so ass select only one cell


        jTableOrder.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                int selectedRow = jTableOrder.getSelectedRow();
                int selectedColumn = jTableOrder.getSelectedColumn();
                String selectedCellData = String.valueOf(jTableOrder.getValueAt(selectedRow, selectedColumn));
                jLabel.setText("editing row " + selectedRow + " column " + selectedColumn + " " + selectedCellData);
                int count = getCount(selectedRow);
                if (selectedColumn == KOD_PRO100) {
                    String kodPRO100 = selectedCellData;
                    Nomenclature nomenclature = new Nomenclature(manager);
                    if (kodPRO100.startsWith("FRN")) {
                        Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                                arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName(),
                                arrayColorsMainFront[jComboBoxColorsMainFront.getSelectedIndex()].getKod(),
                                kodPRO100.substring(4));
                        insertNomenclature(nomenclatureFront);
                    }
                    if (kodPRO100.startsWith("KOR")) {
                        Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                                arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getKod(),
                                kodPRO100.substring(4));
                        insertNomenclature(nomenclatureKorpus);
                    }
                    if (kodPRO100.startsWith("K04") || kodPRO100.startsWith("SK")) {
                        Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(kodPRO100);
                        insertNomenclature(nomenclatureFullKod);                    }
                }else if (selectedColumn == COUNT){
                    BigDecimal price = BigDecimal.valueOf(Double.valueOf(String.valueOf(jTableOrder.getValueAt(selectedRow,PRICE))));
                    BigDecimal sum = price.multiply(BigDecimal.valueOf(count));
                    jTableOrder.setValueAt(String.valueOf(sum), selectedRow, SUM);
                    sum = getTotalSum();
                    jTableOrder.setValueAt(String.valueOf(sum), jTableOrder.getRowCount()-1, SUM);
                }else if (selectedColumn == TYPE){
                    jComboBoxColorsFrontInTable.removeAll();
                    Color[] arrayColors = color.arrayColors(arrayFronts[jComboBoxFrontInTable.getSelectedIndex()].getKod());
                    String[] colorsNames = color.getColorsNames(arrayColors);
                    for (int i = 0; i < arrayColors.length; i++) {
                        jComboBoxColorsFrontInTable.addItem(colorsNames[i]);
                    }

                    typeColumn = jTableOrder.getColumnModel().getColumn(COLOR);
                    typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxColorsFrontInTable));
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
        jButtonAddRows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTableOrder.getSelectedRow();
                int selectedColumn = jTableOrder.getSelectedColumn();
                data = getDataFromTable();
                String[][] newData = new String[data.length+1][columnNames.length];
                for (int i = 0; i <= selectedRow; i++) {
                    newData[i] = data[i];
                }
                newData[selectedRow+1] = new String[columnNames.length];
                for (int i = 0; i < columnNames.length ; i++) {
                    newData[selectedRow+1][i] = data[selectedRow][i];
                }
                for (int i = selectedRow+2; i < newData.length; i++) {
                    newData[i] = data[i-1];
                    newData[i][NUMBER_ROW] = String.valueOf(i+1);
                }
/*
                for (int i = 0; i < newData.length-1; i++) {
                    newData[i][NUMBER_ROW] = String.valueOf(i+1);
                }
*/
                newData[newData.length-1][NUMBER_ROW] = "";
                newData[newData.length-1][KOD_PRO100] = "";
                newData[newData.length-1][TYPE] = "";
                newData[newData.length-1][COLOR] = "";
                newData[newData.length-1][ARTIKLE] = "";
                newData[newData.length-1][NAME] = "";
                newData[newData.length-1][COUNT] = "";
                data = newData;
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                jTableOrder.setModel(model);
                setColumnWidth();
                jTableOrder.addRowSelectionInterval(selectedRow+1,selectedRow+1);
                jTableOrder.addColumnSelectionInterval(selectedColumn,selectedColumn);
                jComboBoxFrontInTable.removeAll();
                jComboBoxFrontInTable.addItem(front.getFrontsNames(arrayFronts));
                TableColumn typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
                typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));


            }
        });

        jButtonGetFromClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataSet dataSet = new DataSet();
                DataSet[] clipboard = dataSet.splitClipboard();
                data = getData(clipboard, arrayFronts, arrayColorsMainFront, arrayKorpusColors);
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                jTableOrder.setModel(model);
                TableColumn typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
                typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));

                setColumnWidth();
            }
        });

        jComboBoxFrontInTable.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                Color[] arrayColors = color.arrayColors(arrayFronts[jComboBoxFrontInTable.getSelectedIndex()].getKod());
                String[] colorsNames = color.getColorsNames(arrayColors);
                jComboBoxColorsFrontInTable = new JComboBox(colorsNames);
                typeColumn = jTableOrder.getColumnModel().getColumn(COLOR);
                typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxColorsFrontInTable));

            }
        });

        jComboBoxMainFront.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsMainFront.removeAllItems();
                arrayColorsMainFront = color.arrayColors(arrayFronts[jComboBoxMainFront.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(arrayColorsMainFront);
                for (String colorName: colorsNames) {
                    jComboBoxColorsMainFront.addItem(colorName);
                }
            }
        });

        jComboBoxAdditionFront.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsAdditionFront.removeAllItems();
                arrayColorsAdditionFront = color.arrayColors(arrayFronts[jComboBoxAdditionFront.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(arrayColorsMainFront);
                for (String colorName: colorsNames) {
                    jComboBoxColorsAdditionFront.addItem(colorName);
                }
            }
        });

    }

    private void setColumnWidth() {
        jTableOrder.getColumnModel().getColumn(NUMBER_ROW).setMaxWidth(NUMBER_ROW_WIDTH);
        jTableOrder.getColumnModel().getColumn(KOD_PRO100).setMaxWidth(KOD_PRO100_WIDTH);
        jTableOrder.getColumnModel().getColumn(TYPE).setMaxWidth(TYPE_WIDTH);
        jTableOrder.getColumnModel().getColumn(COLOR).setMaxWidth(COLOR_WIDTH);
        jTableOrder.getColumnModel().getColumn(ARTIKLE).setMaxWidth(ARTIKLE_WIDTH);
        jTableOrder.getColumnModel().getColumn(NAME).setMaxWidth(NAME_WIDTH);
        jTableOrder.getColumnModel().getColumn(COUNT).setMaxWidth(COUNT_WIDTH);
        jTableOrder.getColumnModel().getColumn(PRICE).setMaxWidth(PRICE_WIDTH);
        jTableOrder.getColumnModel().getColumn(SUM).setMaxWidth(SUM_WIDTH);
    }

    private String[][] getDataFromTable() {
        String[][] result = new String[jTableOrder.getRowCount()][jTableOrder.getColumnCount()];
        for (int i = 0; i < jTableOrder.getRowCount(); i++) {
            for (int j = 0; j < jTableOrder.getColumnCount(); j++) {
                String cellData = String.valueOf(jTableOrder.getValueAt(i,j));
                if (cellData.isEmpty()){
                    result[i][j] = "";
                }else {
                    result[i][j] = String.valueOf(jTableOrder.getValueAt(i,j));
                }
            }
        }
        return result;
    }

    private void insertNomenclature(Nomenclature nomenclature) {
        int selectedRow = jTableOrder.getSelectedRow();
        int count = getCount(selectedRow);
        jTableOrder.setValueAt(nomenclature.getKod(), selectedRow, ARTIKLE);
        jTableOrder.setValueAt(nomenclature.getName(), selectedRow, NAME);
        jTableOrder.setValueAt(String.valueOf(nomenclature.getPrice()), selectedRow, PRICE);
        BigDecimal sum = nomenclature.getPrice().multiply(BigDecimal.valueOf(count));
        jTableOrder.setValueAt(String.valueOf(sum), selectedRow, SUM);
        sum = getTotalSum();
        jTableOrder.setValueAt(String.valueOf(sum), jTableOrder.getRowCount()-1, SUM);
    }

    private int getCount(int selectedRow) {
        if (String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)).equals("")){
            jTableOrder.setValueAt("1", selectedRow, COUNT);
            return 1;
        }else {
            return Integer.parseInt(String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)));
        }
    }

    private String[][] getData(DataSet[] clipboard, Front[] arrayFronts, Color[] arrayColors, Color[] arrayKorpusColors) {
        String[][] data = new String [clipboard.length+1][columnNames.length];
        BigDecimal totalSum = BigDecimal.ZERO;
        BigDecimal sum;
        for (int i = 0; i < clipboard.length; i++) {
            sum = BigDecimal.ZERO;
            data[i][NUMBER_ROW] = String.valueOf(i+1);
            data[i][KOD_PRO100] = clipboard[i].getName();
            data[i][COUNT] = String.valueOf(clipboard[i].getCount());
            Nomenclature nomenclature = new Nomenclature(manager);
            if (clipboard[i].getName().startsWith("FRN")){
                Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                        arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName(),
                        arrayColors[jComboBoxColorsMainFront.getSelectedIndex()].getKod(),
                        clipboard[i].getName().substring(4));
                data[i][TYPE] = arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName();
                data[i][COLOR] = arrayColors[jComboBoxColorsMainFront.getSelectedIndex()].getName();
                data[i][ARTIKLE] = nomenclatureFront.getKod();
                data[i][NAME] = nomenclatureFront.getName();
                data[i][PRICE] = String.valueOf(nomenclatureFront.getPrice());
                sum = nomenclatureFront.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount()));
                data[i][SUM] = String.valueOf(sum);
            }
            if (clipboard[i].getName().startsWith("KOR")){
                Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                        arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getKod(),
                        clipboard[i].getName().substring(4));
                data[i][TYPE] = "Корпус";
                data[i][COLOR] = arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getName();
                data[i][ARTIKLE] = nomenclatureKorpus.getKod();
                data[i][NAME] = nomenclatureKorpus.getName();
                data[i][PRICE] = String.valueOf(nomenclatureKorpus.getPrice());
                sum = nomenclatureKorpus.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount()));
                data[i][SUM] = String.valueOf(sum);

            }
            if (clipboard[i].getName().startsWith("K04")||clipboard[i].getName().startsWith("SK")){
                Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(clipboard[i].getName());
                data[i][TYPE] = "";
                data[i][COLOR] = "";
                data[i][ARTIKLE] = nomenclatureFullKod.getKod();
                data[i][NAME] = nomenclatureFullKod.getName();
                data[i][PRICE] = String.valueOf(nomenclatureFullKod.getPrice());
                sum = nomenclatureFullKod.getPrice().multiply(BigDecimal.valueOf(clipboard[i].getCount()));
                data[i][SUM] = String.valueOf(sum);
            }
            totalSum = totalSum.add(sum);
        }
        data[clipboard.length][NAME] = "Загальна сума:";
        data[clipboard.length][SUM] = String.valueOf(totalSum);
        return data;
    }

    public BigDecimal getTotalSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (int row = 0; row < jTableOrder.getRowCount()-1 ; row++) {
            String price = String.valueOf(jTableOrder.getValueAt(row, SUM));
            if (!price.isEmpty()){
                sum = sum.add(BigDecimal.valueOf(Double.valueOf(price)));
            }
        }
        return sum;
    }


    private class EnterAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

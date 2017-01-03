package com.ecru;




import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.TimerTask;

import static com.ecru.JTableUtil.*;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUI extends JFrame {

    static final int MAXIMUM_USE_FRONT = 3; //TODO Analitics
    static final int MAXIMUM_USE_COLOR_FRONT = 0; //TODO Analitics
    private static final int MAXIMUM_USE_COLOR_KORPUS = 3;  //TODO Analitics

    private JButton jButtonAddRow;
    private JLabel jLabel;
    private JProgressBar jProgressBar;

    private JComboBox jComboBoxColorsMainKorpus;
    private JPanel jPanelColorsKorpus;
    private JPanel jPanelButtons;
    private JLabel jLabelMainFront;
    private JLabel jLabelAditionFront;
    private JComboBox jComboBoxAdditionFront;
    private JLabel jLabelColorsAdditionFront;
    private JButton jButtonRecalculate;
    private BoundsPopupMenuListener listenerComboBoxFrontInTable;
    private BoundsPopupMenuListener listenerComboBoxColorsFrontInTable;
    private JComboBox jComboBoxColorsAdditionFront;
    private JLabel jLabelColorsMainFront;
    private JLabel jLabelColorsMainKorpus;
    private JLabel jLabelColorsAdditionKorpus;
    private JComboBox jComboBoxColorsAdditionKorpus;
    private JButton jButtonCopyRow;
    private JButton jButtonRemoveRow;
    private Front[] arrayFronts;
    private JComboBox jComboBoxFrontInTable;
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


    static DataBaseManager manager;
    private static Front front;
    static Color color;

    private JTable jTableOrder;
    private JScrollPane jScrollPane;


    static final int NUMBER_ROW = 0;
    static final int NUMBER_ROW_WIDTH = 20;
    static final int KOD_PRO100 = 1;
    static final int KOD_PRO100_WIDTH = 150;
    static final int TYPE = 2;
    static final int TYPE_WIDTH = 150;
    static final int COLOR = 3;
    static final int COLOR_WIDTH = 150;
    static final int ARTIKLE = 4;
    static final int ARTIKLE_WIDTH = 200;
    static final int NAME = 5;
    static final int NAME_WIDTH = 300;
    static final int COUNT = 6;
    static final int COUNT_WIDTH = 20;
    static final int PRICE = 7;
    static final int PRICE_WIDTH = 50;
    static final int SUM = 8;
    static final int SUM_WIDTH = 75;
    private String[] columnNames = {"№", "PRO100","Фронт/Корпус", "Колір", "Артикул", "Назва", "Кількість", "Ціна", "Вартість"};
    private String[][] dataOrder = {{"1","FRN_393/596_O","36th Norde Avenue", "Дуб Canadian", "K04-ML_NORDE-DCN-393/596_O-FRN02", "Фасад 36th Norde Avenue Дуб Canadian 393/596_O","1","325.25", "325.25"},
                        {"2","FRN_570/596", "","","","","","",""},
                        {"3","KOR-G_45/72", "1","","","","","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","","","","","",""}};
    private double sum;
    private ProgressDialog progress;

    public GUI() {
        super("Конструктор кухонь");
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
        jButtonCopyRow = new JButton(new ImageIcon("add_32.png"));
        jButtonCopyRow.setToolTipText("Копіювати рядок");

        jButtonRemoveRow = new JButton(new ImageIcon("remove_32.png"));
        jButtonRemoveRow.setToolTipText("Видалити рядок");

        jButtonAddRow = new JButton(new ImageIcon("gtk-add_4285.png"));
        jButtonAddRow.setToolTipText("Додати номенклатуру...");

        jButtonRecalculate = new JButton(new ImageIcon("reload_8215.png"));
        jButtonRecalculate.setToolTipText("Перерахувати");
        jPanelButtons.add(jButtonRecalculate);
        jPanelButtons.add(jButtonCopyRow);
        jPanelButtons.add(jButtonRemoveRow);
        jPanelButtons.add(jButtonAddRow);

        jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setMaximum(40);
        jProgressBar.setValue(0);

        jPanelButtons.add(jProgressBar);
        jLabel = new JLabel();
        jPanelButtons.add(jLabel);
        add(jPanelButtons);

        jTableOrder = new JTable(dataOrder, columnNames);
        jTableOrder.setPreferredScrollableViewportSize(new Dimension(967, 580));
        jTableOrder.setFillsViewportHeight(true);
        setColumnWidth(jTableOrder);
        jComboBoxFrontInTable = new JComboBox(front.getFrontsNames(arrayFronts));
        listenerComboBoxFrontInTable = new BoundsPopupMenuListener(true, false);
        jComboBoxFrontInTable.addPopupMenuListener(listenerComboBoxFrontInTable);
        jComboBoxColorsFrontInTable = new JComboBox(color.getColorsNames(arrayColorsMainFront));
        listenerComboBoxColorsFrontInTable = new BoundsPopupMenuListener(true, false);
        jComboBoxColorsFrontInTable.addPopupMenuListener(listenerComboBoxColorsFrontInTable);
        typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));
        typeColumn = jTableOrder.getColumnModel().getColumn(COLOR);
        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxColorsFrontInTable));

        jScrollPane = new JScrollPane(jTableOrder);
        add(jScrollPane);
        jTableOrder.setCellSelectionEnabled(true);//so ass select only one cell

        jButtonAddRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIAddRow addRow = new GUIAddRow(jTableOrder);
                addRow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addRow.setSize(1000,500);
                addRow.setVisible(true);
            }
        });

        jButtonRecalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataOrder = getDataFromTable(jTableOrder);
                jProgressBar.setMaximum(dataOrder.length-2);
                jProgressBar.setIndeterminate(false);

                progress = new ProgressDialog();
                java.util.Timer timer = new java.util.Timer();
                final TimerTask task = new TimerTask() {
                    public void run() {
                        progress.showDialog();
                    }
                };
                // Задаем время, через которое должен включиться progressBar,
                // Если задача еще не выполнена
                timer.schedule( task, 2000);
                Thread someThread = new Thread(new Runnable(){
                    public void run() {
                        // Тут идет обработка.
                        for (int i = 0; i < dataOrder.length-1; i++) {
                            jProgressBar.setValue(i);
                            Nomenclature nomenclature = new Nomenclature(manager);
                            int count = getCount(i);
                            if (dataOrder[i][KOD_PRO100].startsWith("FRN")){
                                String selectedFrontName = dataOrder[i][TYPE];
                                int indexSelectedFront = getIndexFront(selectedFrontName);
                                Color[] arrayColors = color.arrayColors(arrayFronts[indexSelectedFront].getKod());
                                String selectedColor = dataOrder[i][COLOR];
                                int indexSelectedColor = getIndexColor(arrayColors, selectedColor);
                                Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                                        arrayFronts[indexSelectedFront].getName(),
                                        arrayColors[indexSelectedColor].getKod(),
                                        dataOrder[i][KOD_PRO100].substring(4));
                                dataOrder[i][ARTIKLE] = nomenclatureFront.getKod();
                                dataOrder[i][NAME] = nomenclatureFront.getName();
                                BigDecimal price = nomenclatureFront.getPrice();
                                dataOrder[i][PRICE] = String.valueOf(price);
                                BigDecimal sum = price.multiply(BigDecimal.valueOf(count));
                                dataOrder[i][SUM] = String.valueOf(sum);
                            }
                            if (dataOrder[i][KOD_PRO100].startsWith("KOR")){
                                String selectedColor = dataOrder[i][COLOR];
                                int indexSelectedColor = getIndexColor(arrayKorpusColors, selectedColor);
                                Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                                        arrayKorpusColors[indexSelectedColor].getKod(),
                                        dataOrder[i][KOD_PRO100].substring(4));
                                dataOrder[i][ARTIKLE] = nomenclatureKorpus.getKod();
                                dataOrder[i][NAME] = nomenclatureKorpus.getName();
                                BigDecimal price = nomenclatureKorpus.getPrice();
                                dataOrder[i][PRICE] = String.valueOf(price);
                                BigDecimal sum = price.multiply(BigDecimal.valueOf(count));
                                dataOrder[i][SUM] = String.valueOf(sum);
                            }
                            if (dataOrder[i][KOD_PRO100].startsWith("K04")|| dataOrder[i][KOD_PRO100].startsWith("SK")){
                                Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(dataOrder[i][KOD_PRO100]);
                                dataOrder[i][ARTIKLE] = nomenclatureFullKod.getKod();
                                dataOrder[i][NAME] = nomenclatureFullKod.getName();
                                BigDecimal price = nomenclatureFullKod.getPrice();
                                dataOrder[i][PRICE] = String.valueOf(price);
                                BigDecimal sum = price.multiply(BigDecimal.valueOf(count));
                                dataOrder[i][SUM] = String.valueOf(sum);
                            }
                        }
                        dataOrder[dataOrder.length-1][SUM] = String.valueOf(getTotalSum());
                        dataOrder[dataOrder.length-1][NUMBER_ROW] = "";
                        dataOrder[dataOrder.length-1][KOD_PRO100] = "";
                        dataOrder[dataOrder.length-1][TYPE] = "";
                        dataOrder[dataOrder.length-1][COLOR] = "";
                        dataOrder[dataOrder.length-1][ARTIKLE] = "";
                        dataOrder[dataOrder.length-1][NAME] = "";
                        dataOrder[dataOrder.length-1][COUNT] = "";
                        dataOrder[dataOrder.length-1][PRICE] = "Всього:";
                        DefaultTableModel model = new DefaultTableModel(dataOrder, columnNames);
                        jTableOrder.setModel(model);
                        TableColumn typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
                        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));
                        setColumnWidth(jTableOrder);

                        SwingUtilities.invokeLater(new Runnable(){
                            public void run() {
                                task.cancel();
                                progress.closeDialog();
                            }
                        });
                    }
                });
                someThread.start();
            }
        });

        jTableOrder.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

                int selectedRow = jTableOrder.getSelectedRow();
                int selectedColumn = jTableOrder.getSelectedColumn();
                String selectedCellData = String.valueOf(jTableOrder.getValueAt(selectedRow, selectedColumn));

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
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        jButtonRemoveRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = jTableOrder.getSelectedRow();
                if ( selectedRow >= 0 ){
                    dataOrder = getDataFromTable(jTableOrder);
                    String[][] newData = new String[dataOrder.length-1][columnNames.length];
                    for (int i = 0; i < selectedRow; i++) {
                        newData[i] = dataOrder[i];
                    }
                    for (int i = selectedRow+1; i < newData.length; i++) {
                        newData[i-1] = dataOrder[i];
                        newData[i-1][NUMBER_ROW] = String.valueOf(i);
                    }

                    dataOrder = newData;
                    dataOrder[dataOrder.length-1][SUM] = String.valueOf(getTotalSum(dataOrder));

                    DefaultTableModel model = new DefaultTableModel(dataOrder, columnNames);
                    jTableOrder.setModel(model);
                    setColumnWidth(jTableOrder);
                    jComboBoxFrontInTable.removeAll();
                    jComboBoxFrontInTable.addItem(front.getFrontsNames(arrayFronts));
                    TableColumn typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
                    typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));
                }
            }
        });


        jButtonCopyRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTableOrder.getSelectedRow();
                int selectedColumn = jTableOrder.getSelectedColumn();
                dataOrder = getDataFromTable(jTableOrder);
                String[][] newData = new String[dataOrder.length+1][columnNames.length];
                for (int i = 0; i <= selectedRow; i++) {
                    newData[i] = dataOrder[i];
                }
                newData[selectedRow+1] = new String[columnNames.length];
                for (int i = 0; i < columnNames.length ; i++) {
                    newData[selectedRow+1][i] = dataOrder[selectedRow][i];
                }
                newData[selectedRow+1][NUMBER_ROW] = String.valueOf(selectedRow+2);
                for (int i = selectedRow+2; i < newData.length; i++) {
                    newData[i] = dataOrder[i-1];
                    newData[i][NUMBER_ROW] = String.valueOf(i+1);
                }
                newData[newData.length-1][NUMBER_ROW] = "";
                newData[newData.length-1][KOD_PRO100] = "";
                newData[newData.length-1][TYPE] = "";
                newData[newData.length-1][COLOR] = "";
                newData[newData.length-1][ARTIKLE] = "";
                newData[newData.length-1][NAME] = "";
                newData[newData.length-1][COUNT] = "";
                dataOrder = newData;
                dataOrder[dataOrder.length-1][SUM] = String.valueOf(getTotalSum(dataOrder));

                DefaultTableModel model = new DefaultTableModel(dataOrder, columnNames);
                jTableOrder.setModel(model);
                setColumnWidth(jTableOrder);
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


                final ProgressDialog progress = new ProgressDialog();
                java.util.Timer timer = new java.util.Timer();
                final TimerTask task = new TimerTask() {
                    public void run() {
                        progress.showDialog();
                    }
                };
                // Задаем время, через которое должен включиться progressBar,
                // Если задача еще не выполнена
                timer.schedule( task, 2000 );
                Thread someThread = new Thread(new Runnable(){
                    public void run() {
                        // Тут идет обработка.
                        dataOrder = getData(clipboard, arrayFronts, arrayColorsMainFront, arrayColorsAdditionFront, arrayKorpusColors);

                        DefaultTableModel model = new DefaultTableModel(dataOrder, columnNames);
                        jTableOrder.setModel(model);
                        TableColumn typeColumn = jTableOrder.getColumnModel().getColumn(TYPE);
                        typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxFrontInTable));
                        setColumnWidth(jTableOrder);

                        SwingUtilities.invokeLater(new Runnable(){
                            public void run() {
                                task.cancel();
                                progress.closeDialog();
                            }
                        });
                    }
                });
                someThread.start();
            }
        });

        jComboBoxFrontInTable.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                Color[] arrayColors = color.arrayColors(arrayFronts[jComboBoxFrontInTable.getSelectedIndex()].getKod());
                String[] colorsNames = color.getColorsNames(arrayColors);
                jComboBoxColorsFrontInTable = new JComboBox(colorsNames);
                listenerComboBoxColorsFrontInTable = new BoundsPopupMenuListener(true, false);
                jComboBoxColorsFrontInTable.addPopupMenuListener(listenerComboBoxColorsFrontInTable);
                typeColumn = jTableOrder.getColumnModel().getColumn(COLOR);
                typeColumn.setCellEditor(new DefaultCellEditor(jComboBoxColorsFrontInTable));
                setColumnWidth(jTableOrder);
            }
        });

        jComboBoxMainFront.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsMainFront.removeAllItems();
                arrayColorsMainFront = color.arrayColors(arrayFronts[jComboBoxMainFront.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(arrayColorsMainFront);
                for (int i = 0; i < colorsNames.length; i++) {
                    jComboBoxColorsMainFront.addItem(colorsNames[i]);
                }
            }
        });

        jComboBoxAdditionFront.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBoxColorsAdditionFront.removeAllItems();
                arrayColorsAdditionFront = color.arrayColors(arrayFronts[jComboBoxAdditionFront.getSelectedIndex()].getKod());
                String [] colorsNames = color.getColorsNames(arrayColorsAdditionFront);
                for (int i = 0; i < colorsNames.length; i++) {
                    jComboBoxColorsAdditionFront.addItem(colorsNames[i]);
                }
            }
        });
    }

    private int getIndexColor(Color[] arrayColors, String selectedColor) {
        for (int i = 0; i < arrayColors.length; i++) {
            if (arrayColors[i].getName().equals(selectedColor)) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexFront(String frontName) {
        for (int i = 0; i < arrayFronts.length ; i++) {
            if (arrayFronts[i].getName().equals(frontName)){
                return i;
            }
        }
        return -1;
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


    private String[][] getData(DataSet[] clipboard, Front[] arrayFronts, Color[] arrayColorsMainFront, Color[] arrayColorsAdditionFront, Color[] arrayKorpusColors) {
        String[][] data = new String [clipboard.length+1][columnNames.length];


        BigDecimal totalSum = BigDecimal.ZERO;
        BigDecimal sum;
        jProgressBar.setMaximum(clipboard.length-1);


        for (int i = 0; i < clipboard.length; i++) {
            jProgressBar.setValue(i);
            System.out.println(i);
            sum = BigDecimal.ZERO;
            data[i][NUMBER_ROW] = String.valueOf(i+1);
            data[i][KOD_PRO100] = clipboard[i].getName();
            data[i][COUNT] = String.valueOf(clipboard[i].getCount());
            Nomenclature nomenclature = new Nomenclature(manager);
            if (clipboard[i].getName().startsWith("FRN")){
                Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                        arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName(),
                        arrayColorsMainFront[jComboBoxColorsMainFront.getSelectedIndex()].getKod(),
                        clipboard[i].getName().substring(4));
                if (nomenclatureFront.getName().equals("do not definathion") &&
                        jComboBoxAdditionFront.getSelectedIndex() != arrayFronts.length){
                    nomenclatureFront = nomenclature.getNomeclatureFront(
                            arrayFronts[jComboBoxAdditionFront.getSelectedIndex()].getName(),
                            arrayColorsAdditionFront[jComboBoxColorsAdditionFront.getSelectedIndex()].getKod(),
                            clipboard[i].getName().substring(4));
                    data[i][TYPE] = arrayFronts[jComboBoxAdditionFront.getSelectedIndex()].getName();
                    data[i][COLOR] = arrayColorsAdditionFront[jComboBoxColorsAdditionFront.getSelectedIndex()].getName();
                }else {
                    data[i][TYPE] = arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName();
                    data[i][COLOR] = arrayColorsMainFront[jComboBoxColorsMainFront.getSelectedIndex()].getName();
                }
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
                if (nomenclatureKorpus.getName().equals("do not definathion") &&
                        jComboBoxColorsAdditionKorpus.getSelectedIndex() != arrayKorpusColors.length){
                    nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                            arrayKorpusColors[jComboBoxColorsAdditionKorpus.getSelectedIndex()].getKod(),
                            clipboard[i].getName().substring(4));
                    data[i][COLOR] = arrayKorpusColors[jComboBoxColorsAdditionKorpus.getSelectedIndex()].getName();

                }else {
                    data[i][COLOR] = arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getName();
                }
                data[i][TYPE] = "Корпус";
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
        data[clipboard.length][PRICE] = "Всього:";
        data[clipboard.length][SUM] = String.valueOf(totalSum);

        return data;
    }

    private BigDecimal getTotalSum(String[][] data) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int row = 0; row < data.length-1 ; row++) {
            String price = String.valueOf(data[row][SUM]);
            if (!price.isEmpty()){
                sum = sum.add(BigDecimal.valueOf(Double.valueOf(price)));
            }
        }
        return sum;
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

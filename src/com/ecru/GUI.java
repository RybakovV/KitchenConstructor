package com.ecru;




import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;


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

    private KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    private static final int NUMBER_ROW = 0;
    private static final int KOD_PRO100 = 1;
    private static final int ARTIKLE = 2;
    private static final int NAME = 3;
    private static final int COUNT = 4;
    private static final int PRICE = 5;
    private static final int SUM = 6;
    private String[] columnNames = {"№", "PRO100", "Артикул", "Назва", "Кількість", "Ціна", "Вартість"};
    private String[][] data = {{"1","FRN_393/596_O", "K04-ML_NORDE-DCN-393/596_O-FRN02", "Фасад 36th Norde Avenue Дуб Canadian 393/596_O","1","325.25", "325.25"},
                        {"2","FRN_570/596", "","","","",""},
                        {"3","KOR-G_45/72", "1","","","",""},
                        {"4","K04-KORPUS-BK-AVENTOS_HF_A2-AKC02", "1","","","",""}};
    private double sum;

    public GUI() {
        super("GUITable");
        setLayout(new FlowLayout());

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");
        front = new Front(manager);
        Front[] arrayFronts = front.arrayFronts();
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
        jLabel = new JLabel("rcwercw");
        jPanelButtons.add(jLabel);
        add(jPanelButtons);

        jTableOrder = new JTable(data, columnNames);
        jTableOrder.setPreferredScrollableViewportSize(new Dimension(800, 600));
        jTableOrder.setFillsViewportHeight(true);
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
                if (selectedColumn == KOD_PRO100) {
                    String kodPRO100 = selectedCellData;
                    Nomenclature nomenclature = new Nomenclature(manager);
                    if (kodPRO100.startsWith("FRN")) {
                        Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                                arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName(),
                                arrayColorsMainFront[jComboBoxColorsMainFront.getSelectedIndex()].getKod(),
                                kodPRO100.substring(4));

                        jTableOrder.setValueAt(nomenclatureFront.getKod(), selectedRow, ARTIKLE);
                        jTableOrder.setValueAt(nomenclatureFront.getName(), selectedRow, NAME);
                        jTableOrder.setValueAt(nomenclatureFront.getPrice(), selectedRow, PRICE);
                        int count = Integer.parseInt(String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)));
                        jTableOrder.setValueAt(nomenclatureFront.getPrice()*count, selectedRow, SUM);
                        double sum = getSum();
                        jTableOrder.setValueAt(sum, jTableOrder.getRowCount()-1, SUM);
                    }
                    if (kodPRO100.startsWith("KOR")) {
                        Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                                arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getKod(),
                                kodPRO100.substring(4));
                        jTableOrder.setValueAt(nomenclatureKorpus.getKod(), selectedRow, ARTIKLE);
                        jTableOrder.setValueAt(nomenclatureKorpus.getName(), selectedRow, NAME);
                        jTableOrder.setValueAt(nomenclatureKorpus.getPrice(), selectedRow, PRICE);
                        int count = Integer.parseInt(String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)));
                        jTableOrder.setValueAt(nomenclatureKorpus.getPrice()*count, selectedRow, SUM);
                        double sum = getSum();
                        jTableOrder.setValueAt(sum, jTableOrder.getRowCount()-1, SUM);

                    }
                    if (kodPRO100.startsWith("K04") || kodPRO100.startsWith("SK")) {
                        Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(kodPRO100);
                        jTableOrder.setValueAt(nomenclatureFullKod.getKod(), selectedRow, ARTIKLE);
                        jTableOrder.setValueAt(nomenclatureFullKod.getName(), selectedRow, NAME);
                        jTableOrder.setValueAt(nomenclatureFullKod.getPrice(), selectedRow, PRICE);
                        int count = Integer.parseInt(String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)));
                        jTableOrder.setValueAt(nomenclatureFullKod.getPrice()*count, selectedRow, SUM);
                        double sum = getSum();
                        jTableOrder.setValueAt(sum, jTableOrder.getRowCount()-1, SUM);
                    }
                }else if (selectedColumn == COUNT){
                    double price = Double.valueOf(String.valueOf(jTableOrder.getValueAt(selectedRow,PRICE)));
                    int count = Integer.valueOf(String.valueOf(jTableOrder.getValueAt(selectedRow,COUNT)));
                    double sum = price * count;
                    jTableOrder.setValueAt(sum, selectedRow, SUM);
                    jTableOrder.setValueAt(getSum(), jTableOrder.getRowCount()-1, SUM);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        jButtonGetFromClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataSet dataSet = new DataSet();
                DataSet[] clipboard = dataSet.splitClipboard();
                String[][] data = getData(clipboard, arrayFronts, arrayColorsMainFront, arrayKorpusColors);

                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                jTableOrder.setModel(model);
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

    private String[][] getData(DataSet[] clipboard, Front[] arrayFronts, Color[] arrayColors, Color[] arrayKorpusColors) {
        String[][] data = new String [clipboard.length+1][7];
        double totalSum = 0;
        double sum;
        for (int i = 0; i < clipboard.length; i++) {
            sum = 0;
            data[i][0] = String.valueOf(i);
            data[i][1] = clipboard[i].getName();
            Nomenclature nomenclature = new Nomenclature(manager);
            if (clipboard[i].getName().startsWith("FRN")){
                Nomenclature nomenclatureFront = nomenclature.getNomeclatureFront(
                        arrayFronts[jComboBoxMainFront.getSelectedIndex()].getName(),
                        arrayColors[jComboBoxColorsMainFront.getSelectedIndex()].getKod(),
                        clipboard[i].getName().substring(4));
                data[i][2] = nomenclatureFront.getKod();
                data[i][3] = nomenclatureFront.getName();
                data[i][5] = String.valueOf(nomenclatureFront.getPrice());
                sum = clipboard[i].getCount()*nomenclatureFront.getPrice();
                data[i][6] = String.valueOf(sum);
            }
            if (clipboard[i].getName().startsWith("KOR")){
                Nomenclature nomenclatureKorpus = nomenclature.getNomeclatureKorpus(
                        arrayKorpusColors[jComboBoxColorsMainKorpus.getSelectedIndex()].getKod(),
                        clipboard[i].getName().substring(4));
                data[i][2] = nomenclatureKorpus.getKod();
                data[i][3] = nomenclatureKorpus.getName();
                data[i][5] = String.valueOf(nomenclatureKorpus.getPrice());
                sum = clipboard[i].getCount()*nomenclatureKorpus.getPrice();
                data[i][6] = String.valueOf(sum);

            }
            if (clipboard[i].getName().startsWith("K04")||clipboard[i].getName().startsWith("SK")){
                Nomenclature nomenclatureFullKod = nomenclature.getNomenclatureByKod(clipboard[i].getName());
                data[i][2] = nomenclatureFullKod.getKod();
                data[i][3] = nomenclatureFullKod.getName();
                data[i][5] = String.valueOf(nomenclatureFullKod.getPrice());
                sum = clipboard[i].getCount()*nomenclatureFullKod.getPrice();
                data[i][6] = String.valueOf(sum);
            }
            totalSum += sum;
            data[i][4] = String.valueOf(clipboard[i].getCount());

        }
        data[clipboard.length][5] = "Загальна сума:";
        data[clipboard.length][6] = String.valueOf(totalSum);
        return data;
    }

    public double getSum() {
        double sum = 0;
        for (int row = 0; row < jTableOrder.getRowCount()-1 ; row++) {
            sum = sum + Double.valueOf(String.valueOf(jTableOrder.getValueAt(row, 6)));
        }
        return sum;
    }


    private class EnterAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

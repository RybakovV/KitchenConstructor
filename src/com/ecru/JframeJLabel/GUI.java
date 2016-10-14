package com.ecru.JframeJLabel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUI extends JFrame{

    private JLabel jLabelPicture;
    private JComboBox jComboBox;

    private ButtonGroup buttonGroup;
    private JRadioButton jRadioButtonArial;
    private JRadioButton jRadioButtonSerif;
    private JRadioButton jRadioButtonCourier;

    private JLabel jLabel;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JPasswordField jPasswordField;
    private JButton jButton1;
    private JButton jButton2;
    private JCheckBox jCheckBoxBold;
    private JCheckBox jCheckBoxItalic;
    private static String[] fileName = {"edit-paste.png", "folder_2272.png", "save.png"};
    private Icon[] pics =  {new ImageIcon(getClass().getResource(fileName[0])),
                            new ImageIcon(getClass().getResource(fileName[1])),
                            new ImageIcon(getClass().getResource(fileName[2]))};



    public GUI(){
        super("Это заголовок");
        setLayout(new FlowLayout());
        jTextField1 =new JTextField(10);
        add(jTextField1);
        jTextField2 =new JTextField("Статичний текс ііі'і");
        add(jTextField2);
        jTextField3 =new JTextField("Нередактируемой поле", 20);
        add(jTextField3);
        jTextField3.setEditable(false);
        jPasswordField = new JPasswordField("Enter password");
        add(jPasswordField);

        jLabel = new JLabel("Не корми енота в туалете!!!");
        jLabel.setToolTipText("Єто не шутка!!!");
        add(jLabel);

        jButton1 = new JButton("Звичайна кнопка");
        add(jButton1);

        Icon folder = new ImageIcon(getClass().getResource("save.png"));
        Icon folderRed = new ImageIcon(getClass().getResource("save.png"));

        jButton2 = new JButton("Гарна кнопка", folder);
        jButton2.setRolloverIcon(folderRed);
        add(jButton2);

        Action action = new Action();
        jTextField1.addActionListener(action);
        jTextField2.addActionListener(action);
        jTextField3.addActionListener(action);
        jPasswordField.addActionListener(action);

        jButton1.addActionListener(action);
        jButton2.addActionListener(action);

        jCheckBoxBold = new JCheckBox("Грубий");
        add(jCheckBoxBold);
        jCheckBoxBold.addActionListener(action);
        jCheckBoxItalic = new JCheckBox("Нахилений");
        add(jCheckBoxItalic);
        jCheckBoxItalic.addActionListener(action);


        buttonGroup = new ButtonGroup();
        jRadioButtonArial = new JRadioButton("Arial");
        jRadioButtonArial.setSelected(true);
        jRadioButtonArial.addActionListener(action);
        add(jRadioButtonArial);
        jRadioButtonCourier = new JRadioButton("Courier New");
        jRadioButtonCourier.addActionListener(action);
        add(jRadioButtonCourier);

        jRadioButtonSerif = new JRadioButton("Serif");
        jRadioButtonSerif.addActionListener(action);
        add(jRadioButtonSerif);

        buttonGroup.add(jRadioButtonArial);
        buttonGroup.add(jRadioButtonCourier);
        buttonGroup.add(jRadioButtonSerif);


        jComboBox = new JComboBox(fileName);
        jComboBox.addItemListener(
            new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        jLabelPicture.setIcon(pics[jComboBox.getSelectedIndex()]);
                    }
                }
            });
        add(jComboBox);

        jLabelPicture = new JLabel(pics[0]);
        add(jLabelPicture);

    }



    private class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
/*
            String string = "";
            if (event.getSource() == jTextField1){
                string = String.format("jTextField1: %s", event.getActionCommand());
            }else if (event.getSource() == jTextField2){
                string = String.format("jTextField2: %s", event.getActionCommand());
            }else if (event.getSource() == jTextField3){
                string = String.format("jTextField3: %s", event.getActionCommand());
            }else if (event.getSource() == jPasswordField){
                string = String.format("jPasswordField: %s", event.getActionCommand());
            }else if (event.getSource() == jButton1) {
                string = String.format("Натиснута кнопка: %s", event.getActionCommand());
            }else if (event.getSource() == jRadioButtonArial) {
                string = String.format("Ви вибрали : %s", jRadioButtonArial.getActionCommand() + "шрифт");
            }
*/

            JOptionPane.showMessageDialog(null, String.format("%s = %s",event.getSource().getClass().getSimpleName() , event.getActionCommand()));
            Font font = null;
            String fontName = "Arial";
            if (event.getSource() == jRadioButtonArial ||
                event.getSource() == jRadioButtonCourier ||
                event.getSource() == jRadioButtonSerif){
                fontName = event.getActionCommand();
                font = new Font(fontName, Font.PLAIN, 14);
            }
            if (jCheckBoxBold.isSelected() && jCheckBoxItalic.isSelected()){
                font = new Font(fontName, Font.BOLD + Font.ITALIC, 14);
            }else if (jCheckBoxBold.isSelected()){
                font = new Font(fontName, Font.BOLD, 14);
            }else if (jCheckBoxItalic.isSelected()){
                font = new Font(fontName, Font.ITALIC, 14);
            }
            jTextField2.setFont(font);
            jTextField1.setFont(font);
            jTextField3.setFont(font);

        }
    }
}

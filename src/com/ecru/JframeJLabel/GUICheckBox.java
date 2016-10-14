package com.ecru.JframeJLabel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUICheckBox extends JFrame{

    private JTextField jTextField1;
    private JCheckBox jCheckBoxBold;
    private JCheckBox jCheckBoxItalic;

    public GUICheckBox(){
        super("GUICheckBox");
        setLayout(new FlowLayout());
        jTextField1 =new JTextField(30);
        add(jTextField1);
        jCheckBoxBold = new JCheckBox("Грубий");
        add(jCheckBoxBold);
        jCheckBoxBold.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent event) {
                        String fontName = "Arial";
                        Font font = new Font(fontName, Font.PLAIN, 14);
                            if (jCheckBoxBold.isSelected() && jCheckBoxItalic.isSelected()){
                                font = new Font(fontName, Font.BOLD + Font.ITALIC, 14);
                            }else if (jCheckBoxBold.isSelected()){
                                font = new Font(fontName, Font.BOLD, 14);
                            }else if (jCheckBoxItalic.isSelected()){
                                font = new Font(fontName, Font.ITALIC, 14);
                            }
                        jTextField1.setFont(font);
                    }
                });
        jCheckBoxItalic = new JCheckBox("Нахилений");
        add(jCheckBoxItalic);
        jCheckBoxItalic.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent event) {
                        String fontName = "Arial";
                        Font font = new Font(fontName, Font.PLAIN, 14);
                            if (jCheckBoxBold.isSelected() && jCheckBoxItalic.isSelected()){
                                font = new Font(fontName, Font.BOLD + Font.ITALIC, 14);
                            }else if (jCheckBoxBold.isSelected()){
                                font = new Font(fontName, Font.BOLD, 14);
                            }else if (jCheckBoxItalic.isSelected()){
                                font = new Font(fontName, Font.ITALIC, 14);
                            }
                        jTextField1.setFont(font);
                    }
                }
        );
    }
}

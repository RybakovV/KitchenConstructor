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
public class GUIComboBox extends JFrame{

    private static String[] files = {"edit-paste.png", "save.png", "folder_2272.png"};
    private Icon icons[] = {new ImageIcon(getClass().getResource(files[0])),
                    new ImageIcon(getClass().getResource(files[1])),
                    new ImageIcon(getClass().getResource(files[2]))};

    private JComboBox jComboBox;
    private JLabel jLabel;


    public GUIComboBox(){
        super("GUIComboBox");
        setLayout(new FlowLayout());
        jComboBox = new JComboBox(files);
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jLabel.setIcon(icons[jComboBox.getSelectedIndex()]);
            }
        });
        add(jComboBox);
        jLabel = new JLabel(icons[0]);
        add(jLabel);


    }
}

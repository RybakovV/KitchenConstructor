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
public class GUIButton extends JFrame {

    private JButton jButton1;
    private JButton jButton2;


    public GUIButton() {
        super("GUIButton");
        setLayout(new FlowLayout());

        jButton1 = new JButton("Звичайна кнопка");
        add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, String.format("%s = %s",e.getSource().getClass().getSimpleName() , e.getActionCommand()));
            }
        });


        Icon editPasteIcon = new ImageIcon(getClass().getResource("edit-paste.png"));
        Icon folderIcon = new ImageIcon(getClass().getResource("folder_2272.png"));
        jButton2 = new JButton("Гарна кнопка", editPasteIcon);
        jButton2.setRolloverIcon(folderIcon);
        add(jButton2);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, String.format("%s = %s",e.getSource().getClass().getSimpleName() , e.getActionCommand()));
            }
        });

    }
}

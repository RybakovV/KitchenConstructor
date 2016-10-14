package com.ecru.JframeJLabel;


import com.ecru.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUIJList extends JFrame {
    private JList jListLeft;
    private JList jListRight;
    private JButton jButton;

    private static String[] colorNames = {"білий","чорний",
            "cиній", "червоний"};
    private static Color[] colors = {Color.WHITE,
            Color.BLACK, Color.BLUE, Color.RED};


    public GUIJList() {
        super("GUIJList");
        setLayout(new FlowLayout());
        jListLeft = new JList(colorNames);
        add(jListLeft);

        jButton = new JButton("Добавити");
        add(jButton);

        jListRight = new JList();
        add(jListRight);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jListRight.setListData(jListLeft.getSelectedValues());
            }
        });

    }
}

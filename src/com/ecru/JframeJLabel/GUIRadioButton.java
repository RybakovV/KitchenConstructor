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
public class GUIRadioButton extends JFrame{

    private JTextField jTextField1;
    private ButtonGroup buttonGroup;
    private JRadioButton jRadioButtonArial;
    private JRadioButton jRadioButtonSerif;
    private JRadioButton jRadioButtonCourier;

    public GUIRadioButton(){
        super("GUIRadioButton");
        setLayout(new FlowLayout());
        jTextField1 =new JTextField(30);
        add(jTextField1);
        buttonGroup = new ButtonGroup();
        jRadioButtonArial = new JRadioButton("Arial");
        jRadioButtonArial.setSelected(true);
        add(jRadioButtonArial);
        jRadioButtonArial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String fontName = event.getActionCommand();
                Font font = new Font(fontName, Font.PLAIN, 14);
                jTextField1.setFont(font);
            }
        });

        jRadioButtonCourier = new JRadioButton("Courier New");
        add(jRadioButtonCourier);
        jRadioButtonCourier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String fontName = event.getActionCommand();
                Font font = new Font(fontName, Font.PLAIN, 14);
                jTextField1.setFont(font);
            }
        });

        jRadioButtonSerif = new JRadioButton("Serif");
        add(jRadioButtonSerif);
        jRadioButtonSerif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String fontName = event.getActionCommand();
                Font font = new Font(fontName, Font.PLAIN, 14);
                jTextField1.setFont(font);
            }
        });

        buttonGroup.add(jRadioButtonArial);
        buttonGroup.add(jRadioButtonCourier);
        buttonGroup.add(jRadioButtonSerif);


    }
}

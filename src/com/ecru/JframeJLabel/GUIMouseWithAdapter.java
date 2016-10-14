package com.ecru.JframeJLabel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class GUIMouseWithAdapter extends JFrame {

    private JPanel jPanel;
    private JLabel jLabel;


    public GUIMouseWithAdapter() {
        super("GUIMouse");
        jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        add(jPanel, BorderLayout.CENTER);
        jLabel = new JLabel("Строка состояния");
        add(jLabel,BorderLayout.SOUTH);
        HandlerClass handler = new HandlerClass();

        jPanel.addMouseListener(handler);

    }

    private class HandlerClass extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            String Datails = String.format("Ти клацнув %s раз",e.getClickCount());
            if (e.isMetaDown()) {
                Datails += "правою кнопкою";
            }else if (e.isAltDown()) {
                Datails += "центральною кнопкою";
            }else {
                Datails += "лівою кнопкою";
            }

            jLabel.setText(Datails);
        }
    }
}

package com.ecru.JframeJLabel;


import javax.swing.*;
import java.awt.*;
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
        jPanel.addMouseMotionListener(handler);



    }

    private class HandlerClass implements MouseListener, MouseMotionListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            jLabel.setText(String.format("Ти нажав тут %s, %s", e.getX(), e.getY()));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            jLabel.setText("Ти нажав кнопку");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            jLabel.setText("Ти відпустив кнопку");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jLabel.setText("Ти завів мишку у вікно");
            jPanel.setBackground(Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            jLabel.setText("Ти вивів мишку з вікна");

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            jLabel.setText("Ти тягаєш мишку");

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            jLabel.setText("Ти водиш мишкою");
        }
    }
}

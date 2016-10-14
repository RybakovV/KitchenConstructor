package com.ecru.JframeJLabel;

import javax.swing.*;

/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class Bender {
    public static void main(String[] args) {


        GUIComboBox windowComboBox = new GUIComboBox();
        windowComboBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowComboBox.setSize(400,400);
        windowComboBox.setVisible(true);

        GUICheckBox windowGIUCheckBox = new GUICheckBox();
        windowGIUCheckBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowGIUCheckBox.setSize(400,400);
        windowGIUCheckBox.setVisible(true);

        GUIButton windowButton = new GUIButton();
        windowButton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowButton.setSize(400,400);
        windowButton.setVisible(true);

        GUIRadioButton windowRadioButton = new GUIRadioButton();
        windowRadioButton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRadioButton.setSize(400,400);
        windowRadioButton.setVisible(true);

        GUIJList windowJList = new GUIJList();
        windowJList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowJList.setSize(400,400);
        windowJList.setVisible(true);

        GUIMouse windowMouse = new GUIMouse();
        windowMouse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowMouse.setSize(400,400);
        windowMouse.setVisible(true);

    }
}

package com.ecru.JframeJLabel;

/**
 * Created by Vitaliy Ryvakov on 24.10.2016.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Main extends JFrame {
    JProgressBar current = new JProgressBar(0, 2000);
    int num = 0;
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        current.setValue(0);
        current.setStringPainted(true);
        pane.add(current);
        setContentPane(pane);
    }

    public void iterate() {
        while (num < 2000) {
            current.setValue(num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            num += 95;
        }
    }

    public static void main(String[] arguments) {
        Main frame = new Main();
        frame.pack();
        frame.setVisible(true);
        frame.iterate();
    }
}


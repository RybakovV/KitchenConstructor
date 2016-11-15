package com.ecru;

import javax.swing.*;

/**
 * Created by Vitaliy Ryvakov on 25.10.2016.
 */
public class GoProgressBar implements Runnable {
    private JProgressBar progressBar;

    public GoProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        progressBar.setValue(progressBar.getValue()+1);
    }
}

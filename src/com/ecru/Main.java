package com.ecru;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Vitaliy Ryvakov on 11.10.2016.
 */
public class Main extends Application implements EventHandler<ActionEvent>{
    Button buttton;
    Button buttton2;
    Button buttton3;



    public static void main(String[] args) {
        //launch(args);
        int fn = Integer.parseInt(JOptionPane.showInputDialog("Input num 1"));
        int sn = Integer.parseInt(JOptionPane.showInputDialog("Input num 2"));
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "Sum = fn + sn = ", JOptionPane.PLAIN_MESSAGE );
        //JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "Sum = fn + sn = ", JOptionPane.ERROR_MESSAGE );
        //JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "Sum = fn + sn = ", JOptionPane.ERROR );
/*
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "NFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "QUESTION_MESSAGE", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "CANCEL_OPTION", JOptionPane.CANCEL_OPTION);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "CLOSED_OPTION", JOptionPane.CLOSED_OPTION);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "DEFAULT_OPTION", JOptionPane.DEFAULT_OPTION);
        JOptionPane.showMessageDialog(null, "Sum = fn + sn = " + (fn+sn), "NO_OPTION", JOptionPane.NO_OPTION);
*/




    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        buttton = new Button();
        buttton.setText("Click me");
        buttton.setOnAction(this);

        buttton2 = new Button();
        buttton2.setText("Bottom2");
        buttton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("I am love as you tuch me too");
            }
        });

        buttton3 = new Button();
        buttton3.setText("Bottom3");
        buttton3.setOnAction(e -> System.out.println("It is very very ethe"));

        StackPane layyout = new StackPane();
        layyout.getChildren().add(buttton);
        layyout.getChildren().add(buttton2);
        layyout.getChildren().add(buttton3);

        Scene scene = new Scene(layyout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource()==buttton){
            System.out.println("Ooooo I live it when you tuch meeee");
        }

    }
}

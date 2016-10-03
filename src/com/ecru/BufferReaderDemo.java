package com.ecru;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Vitaliy Ryvakov on 18.09.2016.
 */
public class BufferReaderDemo {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("from.txt"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}

package com.ecru;

import java.io.ByteArrayInputStream;

/**
 * Created by Vitaliy Ryvakov on 19.09.2016.
 */
public class FromByeTiIntDemo {
    public static void main(String[] args) {
        int integerNumber;
        byte[] byteArray = {-128, -127, -100, -1 , 0, 1, 2, 100, 127};

        for (byte byteValue: byteArray ) {
            int intValue = new ByteArrayInputStream(new byte[]{byteValue}).read();
            System.out.print("[" + byteValue + "]" + Integer.toBinaryString(byteValue));
            System.out.print(" --> ");
            System.out.println("[" + intValue + "]" + Integer.toBinaryString(intValue));
        }
        System.out.println("-----------------");
/*
        for (byte byteValue: byteArray ) {
            int intVelue = new ByteArrayInputStream(byteArray).read();
            System.out.println(Integer.toBinaryString(byteValue));
        }
*/

    }

}

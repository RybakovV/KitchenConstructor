package com.ecru;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Vitaliy Ryvakov on 16.09.2016.
 */
public class CharsetDemo {


    public static void main(String[] args) throws UnsupportedEncodingException {
        String strUKR = "ЖуЖа";
        String strEN = "JuJa";
        System.out.println(getDefaultCharset());
        System.out.println(getBinnaryReprisantation(strUKR, "UTF-8"));
        System.out.println(getBinnaryReprisantation(strUKR, "cp1251"));
        System.out.println(getBinnaryReprisantation(strUKR, "UTF-16"));

        System.out.println(getBinnaryReprisantation(strEN, "UTF-8"));
        System.out.println(getBinnaryReprisantation(strEN, "cp1251"));
        System.out.println(getBinnaryReprisantation(strEN, "UTF-16"));

        System.out.println(changeStringEncoding(strEN, "UTF-8"));
        System.out.println(changeStringEncoding(strEN, "cp1251"));
        System.out.println(changeStringEncoding(strEN, "UTF-16"));

        System.out.println(changeStringEncoding(strUKR, "UTF-8"));
        System.out.println(changeStringEncoding(strUKR, "cp1251"));
        System.out.println(changeStringEncoding(strUKR, "UTF-16"));

    }

    private static String changeStringEncoding(String str, String encodingTo) throws UnsupportedEncodingException {
        byte[] strBytes = str.getBytes(encodingTo);
        return new String(strBytes, encodingTo);
    }

    private static String getBinnaryReprisantation(String str, String encoding) throws UnsupportedEncodingException {
        byte[] strBytes = str.getBytes(encoding);
        String result = "";
        result += strBytes.length;
        result += " bytes. ";

        for (int i = 0; i < strBytes.length; i++) {
            result += strBytes[i] + "(" + Integer.toBinaryString(strBytes[i]) + ")";

        }

        return result;
    }

    public static String getDefaultCharset() {
        Charset defaultCharset = Charset.defaultCharset();
        return defaultCharset.displayName();
    }
}

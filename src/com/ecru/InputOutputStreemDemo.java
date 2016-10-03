package com.ecru;

import org.w3c.dom.UserDataHandler;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Vitaliy Ryvakov on 17.09.2016.
 */
public class InputOutputStreemDemo {
    public static void main(String[] args) throws IOException {
//        copyFromFileToFile("from.txt", "from.txt");



//        copyFromFileToFile("to.txt", "toto.txt");
/*
        copyFromFileToFile("verti_4.jpg", "verti_4copy.jpg", 1);
        copyFromFileToFile("verti_4.jpg", "verti_4copy.jpg", 2);
        copyFromFileToFile("verti_4.jpg", "verti_4copy.jpg", 4);
        copyFromFileToFile("verti_4.jpg", "verti_4copy.jpg", 512);
*/
       /* copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 8*1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 16*1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 32*1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 64*1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 128*1024);
        copyFromFileToFile("bolton.jpg", "bolton_copy.jpg", 256*1024);*/


//        compresFile("to.txt", "to.zip");

/*
        long startCopy = System.currentTimeMillis();
        copyFromFileToFile("from.txt", "to.txt", 64*1024);
        long finishCopy = System.currentTimeMillis();
        System.out.println("File copied by " + (finishCopy-startCopy) + "ms");


        long startCompres = System.currentTimeMillis();
        compresFileByDecorators("from.txt", "to(zip).txt");
        long finishCompres = System.currentTimeMillis();
        System.out.println("File compresed by " + (finishCompres-startCompres) + "ms");
*/

        UserData userData = new UserData();
        userData.age = 45;
        userData.name = "Stiven Pupkin";
        userData.salaryArray = new int[]{100, 200, 300,400};

        writeToFile(userData, "StivenPupkin.data");
        printFromFile("StivenPupkin.data");

        /*
        long startDecompres = System.currentTimeMillis();
        decompresFileByDecorator("to(zip).txt", "to(fromZip).txt");
        long finishDecompres = System.currentTimeMillis();
        System.out.println("File decompresed by " + (finishDecompres-startDecompres) + "ms");
*/
        //copyFromUrlToFile("https://google.com", "google.html");
        //copyFromUrlToFile("http://www.ex.ua/load/274856945", "De l'autre côté du lit (2008) BDRip [Ukr.Fra][Hurtom].avi");
//        copyFromUrlToFile("http://mebelbos.com.ua", "mebelbos.html");
//        readStringToConsole("http://mebelbos.com.ua");
//        readStringToConsole("https://google.com");
    }

    private static void printFromFile(String inputFileName) {
        UserData userData = new UserData();
        try {
            InputStream inputStream = new GZIPInputStream(
                    new BufferedInputStream(
                            new FileInputStream(inputFileName)));
            DataInput dataInput = new DataInputStream(inputStream);
            userData.age = dataInput.readByte();
            userData.name = dataInput.readUTF();
            userData.salaryArray = new int[dataInput.readInt()];
            for (int i = 0; i < userData.salaryArray.length; i++) {
                userData.salaryArray[i] = dataInput.readInt();
            }
            System.out.println(userData.age);
            System.out.println(userData.name);
            System.out.println(Arrays.toString(userData.salaryArray));
            inputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(UserData userData, String outputFileName) {
        try {
            OutputStream outputStream = new GZIPOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(outputFileName)));
            DataOutput dataOutput = new DataOutputStream(outputStream);
            try {
                dataOutput.writeByte(userData.age);
                dataOutput.writeUTF(userData.name);
                dataOutput.writeInt(userData.salaryArray.length);
                for (int salaryElem : userData.salaryArray) {
                    dataOutput.writeInt(salaryElem);
                }
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void decompresFileByDecorator(String fileNameFrom, String fileNameTo) {
        try {
            InputStream inputStream = new GZIPInputStream(new BufferedInputStream(new FileInputStream(fileNameFrom)));
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileNameTo));
            int bufferSize = 64 * 1024;
            int count;
            byte[] buffer = new byte[bufferSize];

            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readStringToConsole(String fromUrl) throws IOException {
        InputStream urlInputStream = new URL(fromUrl).openStream();
        while (true){
            int oneByte = urlInputStream.read();
            if (oneByte != -1){
                System.out.print((char)oneByte);
            }else {
                System.out.println("\n");
                System.out.println("end");
                break;
            }
        }
        urlInputStream.close();
    }


    private static void copyFromUrlToFile(String fromUrl, String fileNameTo) {
        try {
            InputStream urlInputStream = new URL(fromUrl).openStream();
            FileOutputStream fileOutputStream = new FileOutputStream(fileNameTo);
            int bufferSize = 64*1024;
            byte [] buffer = new byte[bufferSize];
            int count;
            int sizeDownload = 0;
            long startTime = System.currentTimeMillis();
            while ((count = urlInputStream.read(buffer)) != -1){
                //System.out.print(new String(buffer,0, count, "UTF-8"));
                fileOutputStream.write(buffer, 0, count);
                //buffer = new byte[bufferSize];
                sizeDownload += count;
            }
            long finishTime = System.currentTimeMillis();
            System.out.println("File " + sizeDownload + " bytes downlouded few " + (finishTime -startTime) + "msec");
            urlInputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compresFile(String fileNameFrom, String fileNameTo) {
        String fileName = null;
        try {
            fileName = fileNameFrom;
            FileInputStream fileInputStream = new FileInputStream(fileNameFrom);
            fileName = fileNameTo;
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileNameTo));
            int bufferSize = 1;
            byte [] buffer = new byte[bufferSize];
            ZipEntry zipEntry = new ZipEntry(fileNameFrom);
            zipOutputStream.putNextEntry(zipEntry);
            while (fileInputStream.read(buffer) != -1){
                zipOutputStream.write(buffer);
                buffer = new byte[bufferSize];
            }
            fileInputStream.close();
            zipOutputStream.close();
        } catch (java.io.IOException e) {
            System.out.println("File '" + fileName + "' not found");;
        }
    }

    private static void compresFileByDecorators(String fileNameFrom, String fileNameTo) {
        String fileName = null;
        try {
            fileName = fileNameFrom;
            FileInputStream fileInputStream = new FileInputStream(fileNameFrom);
            fileName = fileNameTo;
            OutputStream zipOutputStream = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(fileNameTo)));
            int bufferSize = 64 * 1024;
            int count;
            byte [] buffer = new byte[bufferSize];

            while ((count = fileInputStream.read(buffer)) != -1){
                zipOutputStream.write(buffer,0, count);
            }
            fileInputStream.close();
            zipOutputStream.close();
        } catch (java.io.IOException e) {
            System.out.println("File '" + fileName + "' not found");;
        }
    }


    private static void copyFromFileToFile(String fileNameFrom, String fileNameTo, int bufferSize)  {
        String fileName = null;
        try {
            fileName = fileNameFrom;
            FileInputStream fileInputStream = new FileInputStream(fileNameFrom);

            fileName = fileNameTo;
            FileOutputStream fileOutputStream = new FileOutputStream(fileNameTo);

            //int bufferSize = 64*1024;
            byte [] buffer = new byte[bufferSize];
            int count;
            long startTime = System.currentTimeMillis();
            while ((count = fileInputStream.read(buffer)) != -1){
                //System.out.println(new String(buffer, 0, count, "utf-8"));
                fileOutputStream.write(buffer, 0, count);
                buffer = new byte[bufferSize];
            }
            long finishTime = System.currentTimeMillis();
            System.out.println("Coppy complet to " + (finishTime - startTime) + "ms");
            fileInputStream.close();
            fileOutputStream.close();
        } catch (java.io.IOException e) {
            System.out.println("File '" + fileName + "' not found");;
        }
    }
}

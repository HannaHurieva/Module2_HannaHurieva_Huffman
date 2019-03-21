package com.alevel.lesson_21;

import java.util.Scanner;

import static com.alevel.lesson_21.Decompressor.DecompressorText;

public class Main {
    public static void main(String[] args) throws Exception {
        //src/main/java/input.txt

        //src/main/java/encodingText.txt.hf
        //src/main/java/matchTable.txt.table

        System.out.println("Input path to file :");
        Scanner scanner = new Scanner(System.in);
        String pathToFile = scanner.nextLine();
        int indexStartExtension = pathToFile.lastIndexOf(".hf");
        if (indexStartExtension > 0) {
            String path = pathToFile.substring(0,indexStartExtension);
            System.out.println("Input path to file with Data Table:");
            Scanner in = new Scanner(System.in);
            String pathToTable = in.nextLine();
            String pathToFileTable = pathToTable.substring(0,pathToTable.lastIndexOf('.'));
            System.out.println("Run Decompressor:");
            DecompressorText(path,pathToFileTable);
        } else {
            System.out.println("Run Compressor:");
            Compressor.CompressionText(pathToFile);
        }
    }
}
package com.alevel.lesson_21;

import java.io.*;
import java.util.*;

public class Decompressor {
    protected static void DecompressorText(String pathToFile, String pathToFileTable) throws Exception {
        Map<String, String> matchTable = readingMatchTableToMapSymbolCode(pathToFileTable);

        System.out.println("symbol : code");
        for (Map.Entry<String, String> entry : matchTable.entrySet()) {
            System.out.println("   " + entry.getKey() + "   : " + entry.getValue());
        }

        byte[] dataDecodedText = readingDecodedTextFromFile(pathToFile);
        StringBuffer decodedText= buildDecodedStringFromByte(matchTable, dataDecodedText);
        System.out.println("Decoded text from byte:");
        System.out.println(decodedText.toString());

        File out = new File(pathToFile);
        String nameFile = out.getName();
        FileWriter decodingText = new FileWriter("src/main/java/out_decompressor/" + nameFile);
        decodingText.write(decodedText.toString());
        decodingText.close();
    }

    protected static StringBuffer buildDecodedStringFromByte(Map<String, String> matchTable, byte[] dataText) {
        StringBuffer decodedString = new StringBuffer();
        String tempStr = "";
        for (int i = 0; i < dataText.length; i++) {
            tempStr += String.valueOf(dataText[i]-48);
            for (Map.Entry<String, String> entry : matchTable.entrySet()) {
                if (tempStr.equals(entry.getValue())) {
                    decodedString.append(entry.getKey());
                    tempStr = "";
                    break;
                }
            }
        }
        return decodedString;
    }

    protected static byte[] readingDecodedTextFromFile(String pathToFile) {
        try (FileInputStream text = new FileInputStream(pathToFile)) {
            byte[] buffer = new byte[text.available()];
            text.read(buffer, 0, buffer.length);

            System.out.println("File data:");
            for (int i = 0; i < buffer.length; i++) {
                System.out.print((char) buffer[i]);
            }
            System.out.println();
            return buffer;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    protected static Map<String, String> readingMatchTableToMapSymbolCode(String path) throws FileNotFoundException {
        FileReader table = new FileReader(path);
        Scanner scanner = new Scanner(table);
        Map<String, String> matchTable = new HashMap<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            matchTable.put(data.split(" : ")[0], data.split(" : ")[1]);
        }
        return matchTable;
    }
}
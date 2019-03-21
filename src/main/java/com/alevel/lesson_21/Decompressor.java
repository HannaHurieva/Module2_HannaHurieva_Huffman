package com.alevel.lesson_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Decompressor {
    protected static void DecompressorText(String pathToFile, String pathToFileTable) throws Exception {
        Map<String, String> matchTable = readingMatchTableToMapSymbolCode(pathToFileTable);

        System.out.println("symbol : code");
        for (Map.Entry<String, String> entry : matchTable.entrySet()) {
            System.out.println("   " + entry.getKey() + "   : " + entry.getValue());
        }

        List<String> inputString = readingEncodedStringToArrayList((readingDecodedTextFromFile(pathToFile)));
        StringBuffer decodedText = buildDecodedString(matchTable, inputString);

        File out = new File(pathToFile);
        String nameFile = out.getName();
        FileWriter decodingText = new FileWriter("src/main/java/out_decompressor/" + nameFile);
        decodingText.write(decodedText.toString());
        decodingText.close();

        System.out.println("Decoded text :");
        System.out.println(decodedText.toString());
    }

    protected static StringBuffer buildDecodedString(Map<String, String> matchTable, List<String> inputString) {
        StringBuffer decodedString = new StringBuffer();
        String tempStr = "";
        for (int i = 0; i < inputString.size(); i++) {
            tempStr += inputString.get(i);
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

    protected static List<String> readingEncodedStringToArrayList(String encodedText) throws FileNotFoundException {
        List<String> codeBit = new ArrayList<>();
        char[] characters = encodedText.toCharArray();
        for (int i = 0; i < encodedText.length(); i++) {
            codeBit.add(String.valueOf(characters[i]));
        }
        return codeBit;
    }

    protected static String readingDecodedTextFromFile(String pathToFile) throws FileNotFoundException {
        FileReader text = new FileReader(pathToFile);
        Scanner scanner = new Scanner(text);
        return scanner.nextLine();
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
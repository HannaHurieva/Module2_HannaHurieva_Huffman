package com.alevel.lesson_21;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Compressor {
    protected static StringBuffer stringCompression(String string) throws Exception {
        Map<String, Integer> symbolRepetitions = buildRepetitionsMap(string);

        FileWriter frequencyTable = new FileWriter("src/main/java/frequencyTable.txt");
            for (Map.Entry<String, Integer> entry : symbolRepetitions.entrySet()) {
                frequencyTable.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            frequencyTable.close();

        Map<String, Node> symbolBundleCodeHuffman = buildCodeHuffman(symbolRepetitions);

        StringBuffer encodedString = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            String symbol = "" + string.charAt(i);
            encodedString.append(symbolBundleCodeHuffman.get(symbol).code);
        }

        FileWriter encodingText = new FileWriter("src/main/java/encodingText.txt");
        encodingText.write(encodedString.toString());
        encodingText.close();

        return encodedString;
    }

    protected static Map<String, Node> buildCodeHuffman(Map<String, Integer> symbolRepetitions) {
        Map<String, Node> symbolBundleCodeHuffman = new HashMap<>();
        Queue<Node> nodeQueue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : symbolRepetitions.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue(), null, null);
            symbolBundleCodeHuffman.put(entry.getKey(), node);
            nodeQueue.add(node);
        }

        while (nodeQueue.size() > 1) {
            Node leftNode = nodeQueue.poll();
            Node rightNode = nodeQueue.poll();

            nodeQueue.offer(new Node(leftNode.getSymbol() + rightNode.getSymbol(),
                    leftNode.getWeight() + rightNode.getWeight(),
                    leftNode,
                    rightNode));
        }

        Node root = nodeQueue.poll();
        if (symbolRepetitions.size() == 1) {
            root.code = "0";
        } else {
            System.out.println("Table Huffman");
            System.out.println("symbol : code");
            root.buildCode("");
        }

        System.out.println("Huffman Tree");
        System.out.println(root.toString());

        return symbolBundleCodeHuffman;
    }

    protected static Map<String, Integer> buildRepetitionsMap(String string) {
        Map<String, Integer> symbolRepetitions = new HashMap<>();
        String symbol;
        int frequency;
        for (int i = 0; i < string.length(); i++) {
            symbol = "" + string.charAt(i);
            if (symbolRepetitions.containsKey(symbol)) {
                frequency = symbolRepetitions.get(symbol) + 1;
                symbolRepetitions.put(symbol, frequency);
            } else {
                symbolRepetitions.put(symbol, 1);
            }
        }

        System.out.println("symbol : frequency");
        for (Map.Entry<String, Integer> entry : symbolRepetitions.entrySet()) {
            System.out.println("   " + entry.getKey() + "   :     " + entry.getValue());
        }

        return symbolRepetitions;
    }
}

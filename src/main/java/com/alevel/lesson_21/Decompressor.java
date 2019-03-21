package com.alevel.lesson_21;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Decompressor {
    protected static void DecompressorText() throws Exception {
        Map<String, Integer> frequencyMap = readRepetitionsTable();
        Node root = treeHuffmanBuilder(frequencyMap);
        if (frequencyMap.size() == 1) {
            root.code = "0";
        } else {
            root.buildCode("");
        }

/*        System.out.println("Huffman Tree");
        System.out.println(root.toString());*/

        List<Integer> code = readEncodedText();
        StringBuffer text = new StringBuffer();
        text = root.visitNode(root, code, 0, text);
        System.out.println(text);
    }

    protected static List<Integer> readEncodedText() throws FileNotFoundException {
        FileReader text = new FileReader("src/main/java/encodingText.txt");
        Scanner scanner = new Scanner(text);
        String encodedText = scanner.nextLine();
        char[] characters = encodedText.toCharArray();
        List<Integer> codeBit = new ArrayList<>();
        for (int i = 0; i < encodedText.length(); i++) {
            codeBit.add(Character.digit(characters[i], 10));
        }
        return codeBit;
    }

    protected static Node treeHuffmanBuilder(Map<String, Integer> symbolRepetitions) {
        Queue<Node> nodeQueue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : symbolRepetitions.entrySet()) {
            nodeQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }
        while (nodeQueue.size() > 1) {
            Node leftNode = nodeQueue.poll();
            Node rightNode = nodeQueue.poll();
            nodeQueue.offer(new Node(leftNode.getSymbol() + rightNode.getSymbol(),
                    leftNode.getWeight() + rightNode.getWeight(),
                    leftNode,
                    rightNode));
        }
        return nodeQueue.poll();
    }

    protected static Map<String, Integer> readRepetitionsTable() throws Exception {
        FileReader table = new FileReader("src/main/java/frequencyTable.txt");
        Scanner scanner = new Scanner(table);

        Map<String, Integer> symbolRepetitions = new HashMap<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            symbolRepetitions.put(data.split(" : ")[0], Integer.parseInt(data.split(" : ")[1]));
        }
        return symbolRepetitions;
    }
}

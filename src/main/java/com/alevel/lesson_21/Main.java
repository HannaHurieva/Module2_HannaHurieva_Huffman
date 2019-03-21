package com.alevel.lesson_21;

import java.io.FileReader;
import java.util.Scanner;

import static com.alevel.lesson_21.Decompressor.DecompressorText;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader input = new FileReader("src/main/java/input.txt");
        Scanner scanner = new Scanner(input);
        String string = scanner.nextLine();
        System.out.println(string);
        System.out.println("Length of input string = " + string.length());
        StringBuffer encodedString = Compressor.stringCompression(string);
        System.out.println(encodedString.toString());
        System.out.println("Length of encoded string = " + encodedString.length());

        DecompressorText();
    }
}

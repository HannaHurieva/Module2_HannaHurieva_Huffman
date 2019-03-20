package com.alevel.lesson_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/main/java/input.txt"));
        String string = input.nextLine();
        System.out.println(string);
        System.out.println("Length of input string = " + string.length());
        StringBuffer encodedString = Compressor.stringCompression(string);
        System.out.println(encodedString.toString());
        System.out.println("Length of encoded string = " + encodedString.length());
    }
}

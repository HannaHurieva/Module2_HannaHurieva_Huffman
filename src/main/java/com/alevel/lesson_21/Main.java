package com.alevel.lesson_21;

import static com.alevel.lesson_21.Decompressor.DecompressorText;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Run Compressor:");
        Compressor.CompressionText();
        System.out.println();
        System.out.println("Run Decompressor:");
        DecompressorText();
    }
}
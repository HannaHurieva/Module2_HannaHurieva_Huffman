package com.alevel.lesson_21;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class DecompressorTest extends Decompressor {
    @Test
    public void buildDecodedStringTest() throws Exception {
        Map<String, String> testMatchTable = new HashMap<>();
        testMatchTable.put(" ", "01");
        testMatchTable.put("a", "00");
        testMatchTable.put("b", "110");
        testMatchTable.put("c", "101");
        testMatchTable.put("d", "100");
        testMatchTable.put("e", "1110");
        testMatchTable.put("f", "11111");
        testMatchTable.put("g", "11110");

        FileInputStream text = new FileInputStream("src/main/java/out_compressor/input.txt.hf");
        byte[] buffer = new byte[text.available()];
        text.read(buffer, 0, buffer.length);

        StringBuffer expected = buildDecodedStringFromByte(testMatchTable, buffer);

        StringBuffer actual = new StringBuffer();
        actual.append("abcd adbbe accdfe aaaa bbb cc d e fg");

        Assert.assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public void buildDecodedStringTest1() throws Exception {
        Map<String, String> testMatchTable = new HashMap<>();
        testMatchTable.put("a", "0");
        testMatchTable.put("b", "10");
        testMatchTable.put("c", "110");
        testMatchTable.put("d", "111");

        FileInputStream text = new FileInputStream("src/main/java/encodingText.txt");
        byte[] buffer = new byte[text.available()];
        text.read(buffer, 0, buffer.length);

        StringBuffer expected = buildDecodedStringFromByte(testMatchTable, buffer);

        StringBuffer actual = new StringBuffer();
        actual.append("abacabad");

        Assert.assertEquals(expected.toString(), actual.toString());
    }

/*    @Test // with example from file : src/main/java/matchTable.txt
    public void readMatchTableSymbolCodeTest() throws FileNotFoundException {
        Map<String, String> actual = new HashMap<>();
        actual.put(" ", "01");
        actual.put("a", "00");
        actual.put("b", "110");
        actual.put("c", "101");
        actual.put("d", "100");
        actual.put("e", "1110");
        actual.put("f", "11111");
        actual.put("g", "11110");

        Map<String, String> expected = readingMatchTableToMapSymbolCode("src/main/java/matchTable.txt");
        Assert.assertEquals(expected, actual);
    }*/
}

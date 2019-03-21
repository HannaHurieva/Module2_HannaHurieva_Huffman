package com.alevel.lesson_21;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecompressorTest extends Decompressor {
    @Test
    public void buildDecodedStringTest() {
        Map<String, String> testMatchTable = new HashMap<>();
        testMatchTable.put(" ", "01");
        testMatchTable.put("a", "00");
        testMatchTable.put("b", "110");
        testMatchTable.put("c", "101");
        testMatchTable.put("d", "100");
        testMatchTable.put("e", "1110");
        testMatchTable.put("f", "11111");
        testMatchTable.put("g", "11110");

        List<String> testString = new ArrayList<>();
        String testData = "001101011000100100110110111001001011011001111111100100000000011101101100110110101100011110011111111110";
        char[] characters = testData.toCharArray();
        for (int i = 0; i < testData.length(); i++) {
            testString.add(String.valueOf(characters[i]));
        }

        StringBuffer actual = new StringBuffer();
        actual.append("abcd adbbe accdfe aaaa bbb cc d e fg");

        StringBuffer expected = buildDecodedString(testMatchTable, testString);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void buildDecodedStringTest1() {
        Map<String, String> testMatchTable = new HashMap<>();
        testMatchTable.put("a", "0");
        testMatchTable.put("b", "10");
        testMatchTable.put("c", "110");
        testMatchTable.put("d", "111");

        List<String> testString = new ArrayList<>();
        String testData = "01001100100111";
        char[] characters = testData.toCharArray();
        for (int i = 0; i < testData.length(); i++) {
            testString.add(String.valueOf(characters[i]));
        }

        StringBuffer actual = new StringBuffer();
        actual.append("abacabad");

        StringBuffer expected = buildDecodedString(testMatchTable, testString);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void readingEncodedTextToArrayListTest() throws FileNotFoundException {
        List<String> actual = new ArrayList<>();
        String testData = "001101011000100100110110111001001011011001111111100100000000011101101100110110101100011110011111111110";
        char[] characters = testData.toCharArray();
        for (int i = 0; i < testData.length(); i++) {
            actual.add(String.valueOf(characters[i]));
        }

        List<String> expected = readingEncodedStringToArrayList(testData);
        Assert.assertEquals(expected, actual);
    }

    @Test // with example from file : src/main/java/matchTable.txt
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
    }
}

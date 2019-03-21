package com.alevel.lesson_21;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CompressorTest extends Compressor {

    @Test
    public void repetitionsMapBuilderTest1() {
        String testString = "abcd adbbe accdfe aaaa bbb cc d e fg";
        Map<String, Integer> expected = buildSymbolRepetitionsMap(testString);
        Map<String, Integer> actual = new HashMap<>();
        actual.put(" ", 8);
        actual.put("a", 7);
        actual.put("b", 6);
        actual.put("c", 5);
        actual.put("d", 4);
        actual.put("e", 3);
        actual.put("f", 2);
        actual.put("g", 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void repetitionsMapBuilderTest2() {
        String testString = "abacabad";
        Map<String, Integer> expected = buildSymbolRepetitionsMap(testString);
        Map<String, Integer> actual = new HashMap<>();
        actual.put("a", 4);
        actual.put("b", 2);
        actual.put("c", 1);
        actual.put("d", 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void buildCodeHuffmanTest() {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("a", 4);
        testMap.put("b", 2);
        testMap.put("c", 1);
        testMap.put("d", 1);
        Map<String, Node> expected = buildCodeHuffman(testMap);

        Node node1 = new Node("a", 4, null, null);
        node1.buildCode("");
        Node node2 = new Node("b", 2, null, null);
        node2.buildCode("");
        Node node3 = new Node("c", 1, null, null);
        node3.buildCode("");
        Node node4 = new Node("d", 1, null, null);
        node4.buildCode("");

        Map<String, Node> actual = new HashMap<>();
        actual.put("a", node1);
        actual.put("b", node2);
        actual.put("c", node3);
        actual.put("d", node4);

        Assert.assertEquals(expected, actual);
    }
}
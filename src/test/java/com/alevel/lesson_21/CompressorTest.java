package com.alevel.lesson_21;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CompressorTest extends Compressor {
/*    private Node element;
    private Node element1;
    private Node element2;
    private Node element3;
    private Node element4;
    private Node element5;
    private Node element6;
    private Node element7;

    @Before
    public void setUp() {
        element = new Node(" ", 8, null, null);
        element1 = new Node("a", 7, null, null);
        element2 = new Node("b", 6, null, null);
        element3 = new Node("c", 5, null, null);
        element4 = new Node("d", 4, null, null);
        element5 = new Node("e", 3, null, null);
        element6 = new Node("f", 2, null, null);
        element7 = new Node("g", 1, null, null);
    }*/

    @Test
    public void repetitionsMapBuilderTest1() {
        String testString = "abcd adbbe accdfe aaaa bbb cc d e fg";
        Map<String, Integer> expected = buildRepetitionsMap(testString);
/*        for (String symbol : expected.keySet()) {
            System.out.println(symbol + " : " + expected.get(symbol));
        }*/
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
        Map<String, Integer> expected = buildRepetitionsMap(testString);
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

//        System.out.println(expected.toString());

/*        Node node1 = new Node("gf", 3, element7, element6);
        Node node2 = new Node("egf", 6, element5, node1);
        Node node3 = new Node("dc", 9, element4, element3);
        Node node4 = new Node("begf", 12, element2, node2);
        Node node5 = new Node("a ", 15, element1, element);
        Node node6 = new Node("dcbegf", 21, node3, node4);
        Node actual = new Node("a dcbegf", 36, node5, node6);*/

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringCompressionTest1() {
        String testString = "abacabad";
        StringBuffer expected = stringCompression(testString);
        StringBuffer actual = new StringBuffer("01001100100111");
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringCompressionTest2() {
        String testString = "a";
        StringBuffer expected = stringCompression(testString);
        StringBuffer actual = new StringBuffer("0");
        Assert.assertEquals(expected.toString(), actual.toString());
    }
}
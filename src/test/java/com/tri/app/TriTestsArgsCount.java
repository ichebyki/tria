package com.tri.app;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for input values count testing
 *
 * Created by ichebyki on 25.04.2017.
 */
public class TriTestsArgsCount extends TriTestsArgsBase {

    // Empty sides list test
    @Test
    public void test() {
        String expected = "ERROR: Invalid arguments count. Must be either zero or three";
        Assert.assertEquals(expected, testTemplate(new String[]{}));
    }

    // Only one side is defined
    @Test
    public void test1() {
        String expected = "ERROR: Invalid arguments count. Must be either zero or three";
        Assert.assertEquals(expected, testTemplate(new String[]{"1"}));
    }

    // Only two sides are defined
    @Test
    public void test12() {
        String expected = "ERROR: Invalid arguments count. Must be either zero or three";
        Assert.assertEquals(expected, testTemplate(new String[]{"1", "2"}));
    }

    // Four sides are defined - it's ok, we use only first three of them
    @Test
    public void test2345() {
        String expected = "Triangle (2.0, 3.0, 4.0)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2", "3", "4", "5"}));
    }

    // Three sides are defined, but values are not good for triangle
    @Test
    public void test123() {
        String expected = "ERROR: Invalid triangle sides values. The values does not correspond to any Euclidean geometry triangle";
        Assert.assertEquals(expected, testTemplate(new String[]{"1", "2", "3"}));
    }

    // Three sides are defined, and values are good for triangle
    @Test
    public void test234() {
        String expected = "Triangle (2.0, 3.0, 4.0)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2", "3", "4"}));
    }

}

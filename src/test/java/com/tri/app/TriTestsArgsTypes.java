package com.tri.app;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for testing input format
 *
 * Created by ichebyki on 24.04.2017.
 */
public class TriTestsArgsTypes extends TriTestsBase {

    // Three sides are defined, and values are doubles, but one of them has non-decimal format
    @Test
    public void testHex() {
        String expected = "ERROR: Invalid input format. Must be valid numeric";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2", "3.33", "4.abc"}));
    }

    // Three sides are defined, and two values are double, but third is long
    @Test
    public void testLong() {
        String expected = "ERROR: Invalid input format. Must be valid numeric";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2", "3.33", "4L"}));
    }

    // Three sides are defined, and two values are double, but third is float
    @Test
    public void testFloat() {
        String expected = "ERROR: Invalid input format. Must be valid numeric";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2", "3.33", "4F"}));
    }

    // Three sides are defined, and values are doubles
    @Test
    public void testNewLines() {
        String expected = "Triangle (2.2, 3.33, 4.44)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2\n\t\n\n", "3.33\n", "4.44\n"}));
    }

}
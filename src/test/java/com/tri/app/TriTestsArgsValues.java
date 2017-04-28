package com.tri.app;

import org.junit.Assert;
import org.junit.Test;

/**
 * The testsuite for testing different triangles sides
 *
 * Created by ichebyki on 24.04.2017.
 */
public class TriTestsArgsValues extends TriTestsArgsBase {

    // Three sides are defined, and values are doubles
    @Test
    public void test234d() {
        String expected = "Triangle (2.2, 3.33, 4.444)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2", "3.33", "4.444"}));
    }

    // Three sides are defined, and values are doubles with power 10
    @Test
    public void test234e10() {
        String expected = "Triangle (2.2E10, 3.33E10, 4.444E10)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2e10", "3.33e10", "4.444e10"}));
    }

    // Three sides are defined, but some value is negative
    @Test
    public void test23_4() {
        String expected = "ERROR: Invalid triangle sides values. The values does not correspond to any Euclidean geometry triangle";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2e10", "3.33e10", "-4.444e10"}));
    }

    // Three sides are defined, and values are small
    @Test
    public void test234e_10() {
        String expected = "Triangle (2.2E-10, 3.33E-10, 4.444E-10)  - Triangle is scalene";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2e-10", "3.33e-10", "4.444e-10"}));
    }

    // Three sides are defined, and some value is zero
    @Test
    public void test230() {
        String expected = "ERROR: Invalid triangle sides values. The values does not correspond to any Euclidean geometry triangle";
        Assert.assertEquals(expected, testTemplate(new String[]{"2.2e-10", "3.33e-10", "0"}));
    }

    // Three sides are defined, and values are half of max double
    @Test
    public void testMaxDoubleHalf() {
        String halfMaxD = Double.toString(Double.MAX_VALUE / 2);
        String expected = "Triangle (8.988465674311579E307, 8.988465674311579E307, 8.988465674311579E307)  - Triangle is equilateral";
        Assert.assertEquals(expected, testTemplate(new String[]{halfMaxD, halfMaxD, halfMaxD}));
    }

    // Three sides are defined, and values are max double
    @Test
    public void testMaxDouble() {
        String maxD = Double.toString(Double.MAX_VALUE);
        String expected = "Triangle (1.7976931348623157E308, 1.7976931348623157E308, 1.7976931348623157E308)  - Triangle is equilateral";
        Assert.assertEquals(expected, testTemplate(new String[]{maxD, maxD, maxD}));
    }

    // Three sides are defined, and try values with twice of max double (actually its are Infinity)
    @Test
    public void testInfinity() {
        String infinity = Double.toString(Double.MAX_VALUE * 2);
        String expected = "ERROR: Invalid triangle sides values. The values does not correspond to any Euclidean geometry triangle";
        Assert.assertEquals(expected, testTemplate(new String[]{infinity, infinity, infinity}));
    }

    // Three sides are defined, and values are min double
    @Test
    public void testMinDouble() {
        String minD = Double.toString(Double.MIN_VALUE);
        String expected = "Triangle (4.9E-324, 4.9E-324, 4.9E-324)  - Triangle is equilateral";
        Assert.assertEquals(expected, testTemplate(new String[]{minD, minD, minD}));
    }

    // Three sides are defined, and two values are max double but one is min double
    @Test
    public void testMinMaxDouble() {
        String maxD = Double.toString(Double.MAX_VALUE);
        String minD = Double.toString(Double.MIN_VALUE);
        String expected = "Triangle (1.7976931348623157E308, 4.9E-324, 1.7976931348623157E308)  - Triangle is isosceles";
        Assert.assertEquals(expected, testTemplate(new String[]{maxD, minD, maxD}));
    }

}
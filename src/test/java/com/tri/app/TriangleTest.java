package com.tri.app;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 *  Testsuite for Triangle class
 *
 * Created by ichebyki on 29.04.2017.
 */
public class TriangleTest {
    static {
        TriFactory.setLoggingLevel(Level.OFF);
    }

    public Triangle createTriangle(String args[]) {
        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", args).getBytes());
        PrintStream output = System.out;
        TriFactory factory = new TriFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);

        // Create triangle object
        // Use default console for input/output
        return factory.createTriangle();
    }

    @Test
    public void getA() throws Exception {
        Triangle tri = createTriangle(new String[]{"2.2\n\t\n\n", "3.33\n", "4.444\n"});
        Assert.assertEquals(2.2, tri.getA(), 0);
    }

    @Test
    public void setA() throws Exception {
        Triangle tri = createTriangle(new String[]{"2.2\n\t\n\n", "3.33\n", "4.444\n"});
        tri.setA(6.66666);
        Assert.assertEquals(6.66666, tri.getA(), 0);
    }

    @Test
    public void setA1() throws Exception {
        Triangle tri = createTriangle(new String[]{"2.2\n\t\n\n", "3.33\n", "4.444\n"});
        tri.setA(6L);
        Assert.assertEquals(6.0, tri.getA(), 0);
    }

    @Test
    public void getB() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "2.2\n\t\n\n", "4.444\n"});
        Assert.assertEquals(2.2, tri.getB(), 0);
    }

    @Test
    public void setB() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "2.2\n\t\n\n", "4.444\n"});
        tri.setB(6.66666);
        Assert.assertEquals(6.66666, tri.getB(), 0);
    }

    @Test
    public void setB1() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "2.2\n\t\n\n", "4.444\n"});
        tri.setB(6L);
        Assert.assertEquals(6.0, tri.getB(), 0);
    }

    @Test
    public void getC() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "4.444\n", "2.2\n\t\n\n"});
        Assert.assertEquals(2.2, tri.getC(), 0);
    }

    @Test
    public void setC() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "4.444\n", "2.2\n\t\n\n"});
        tri.setC(6.66666);
        Assert.assertEquals(6.66666, tri.getC(), 0);
    }

    @Test
    public void setC1() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "4.444\n", "2.2\n\t\n\n"});
        tri.setC(6L);
        Assert.assertEquals(6.0, tri.getC(), 0);
    }

    @Test
    public void getKind() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "4.444\n", "2.2\n\t\n\n"});
        Assert.assertEquals(Triangle.TriKind.SCALENE, tri.getKind());
    }

    @Test
    public void getKind1() throws Exception {
        Triangle tri = createTriangle(new String[]{"3.33\n", "4.444\n", "3.33\n\t\n\n"});
        Assert.assertEquals(Triangle.TriKind.ISOSCELES, tri.getKind());
    }

    @Test
    public void getKind2() throws Exception {
        Triangle tri = createTriangle(new String[]{"4.444\n", "4.444\n", "4.444\n\t\n\n"});
        Assert.assertEquals(Triangle.TriKind.EQUILATERAL, tri.getKind());
    }

}
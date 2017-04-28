package com.tri.app;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

/**
 * Testsuite for TriFactory class
 *
 * Created by ichebyki on 28.04.2017.
 */
public class TriFactoryTest {
    static {
        TriFactory.setLoggingLevel(Level.OFF);
    }

    @Test
    public void getLocale() throws Exception {
        TriFactory factory = new TriFactory();
        Locale expected = Locale.US;
        Assert.assertEquals(expected, factory.getLocale());
    }

    @Test
    public void getLocaleFRENCH() throws Exception {
        TriFactory factory = new TriFactory();
        Locale expected = Locale.FRENCH;
        factory.setLocale(Locale.FRENCH);
        Assert.assertEquals(expected, factory.getLocale());
    }

    @Test
    public void getLocaleRU() throws Exception {
        TriFactory factory = new TriFactory();
        Locale expected = new Locale("RU");
        factory.setLocale(expected);
        Assert.assertEquals(expected, factory.getLocale());
    }

    @Test
    public void getErrorLast() throws Exception {
        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", new String[]{"2.2", "3.33", "4.abc"}).getBytes());
        PrintStream output = System.out;
        TriFactory factory = new TriFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);

        // Create triangle object
        // Use default console for input/output
        Triangle triangle = factory.createTriangle();
        String expected = "Invalid input format. Must be valid decimal double.";

        Assert.assertEquals(expected, factory.getErrorLast());
    }

    @Test
    public void getTriangleLast() throws Exception {
        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", new String[]{"2.2", "3.33", "4.444"}).getBytes());
        PrintStream output = System.out;
        TriFactory factory = new TriFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);

        // Create triangle object
        // Use default console for input/output
        Triangle triangle = factory.createTriangle();

        Assert.assertEquals(triangle, factory.getTriangleLast());
    }

    @Test
    public void setLocale() throws Exception {
        getLocaleRU();
        getLocaleFRENCH();
    }

    @Test
    public void isShowPrompt() throws Exception {
        TriFactory factory = new TriFactory();
        boolean expected = true;
        Assert.assertEquals(expected, factory.isShowPrompt());
    }

    @Test
    public void setShowPrompt() throws Exception {
        TriFactory factory = new TriFactory();
        boolean expected = false;
        factory.setShowPrompt(expected);
        Assert.assertEquals(expected, factory.isShowPrompt());
    }

    @Test
    public void createTriangle() throws Exception {
        getTriangleLast();
    }

    @Test
    public void setLoggingLevel() throws Exception {
        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", new String[]{"2.2", "3.33", "4.444"}).getBytes());
        PrintStream output = System.out;
        TriFactory factory = new TriFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);
        TriFactory.setLoggingLevel(Level.OFF);

        Assert.assertTrue(true);
    }

}
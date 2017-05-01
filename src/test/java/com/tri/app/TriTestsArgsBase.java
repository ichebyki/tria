package com.tri.app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Base test template
 *
 * Created by ichebyki on 24.04.2017.
 */
public class TriTestsArgsBase {
    private static final Logger LOG = Logger.getLogger("server." + new Object(){}.getClass().getEnclosingClass().getSimpleName());
    static {
        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();

        // Set logging off for tests
        TriFactory.setLoggingLevel(Level.OFF);
    }

    // The same as main method from TriMain class
    // The difference is just we use ByteArrayInputStream instead of System.in
    public String testTemplate(String args[]) {

        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", args).getBytes());
        PrintStream output = System.out;
        TriFactory factory = new TriFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);

        // Create triangle object
        // Use default console for input/output
        Triangle triangle = factory.createTriangle();

        // Verify triangle object
        if (triangle == null) {
            return "ERROR: " + factory.getErrorLast();
        }

        // Get and print triangle kind:
        // equilateral, isosceles or scalene
        Triangle.TriKind kind = triangle.getKind(false);
        return "Triangle (" + triangle.getA() + ", " + triangle.getB() + ", " + triangle.getC() + ") " + " - " + kind.getMsg();
    }
}
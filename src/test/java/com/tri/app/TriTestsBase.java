package com.tri.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by ichebyki on 24.04.2017.
 */
public class TriTestsBase {

    // The same as main method from TriMain class
    // The difference is just we use ByteArrayInputStream instead of System.in
    public String testTemplate(String args[]) {
        InputStream input;
        input = new ByteArrayInputStream(String.join(" ", args).getBytes());
        PrintStream output = System.out;
        TriangleFactory factory = new TriangleFactory(input, output);

        // Hide prompt while testing
        factory.setShowPrompt(false);

        // Create triangle object
        // Use default console for input/output
        Triangle triangle = factory.createTriangle();

        // Verify triangle object
        if (triangle == null) {
            return "ERROR: " + factory.getErrorLast().getMsg();
        }

        // Get and print triangle kind:
        // equilateral, isosceles or scalene
        Triangle.TriKind kind = triangle.getKind();
        return "Triangle (" + triangle.getA() + ", " + triangle.getB() + ", " + triangle.getC() + ") " + " - " + kind.getMsg();
    }
}
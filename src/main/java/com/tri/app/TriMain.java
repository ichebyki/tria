package com.tri.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Simple wrapper class with startup point method  main(...)
 * The method accept args - three double values for triangle sides
 * If args is empty, user is prompted to input the sides in console
 * The sides values are verified against some bad values and then
 * the Triangle object is created and the kind of the triangle is printed
 *
 * Created by ichebyki on 21.04.2017.
 */
public class TriMain {

    // Startup point
    public static void main(String args[]) {

        // If args is not empty, we use args for sides values
        InputStream input;
        if (args.length > 0) {
            input = new ByteArrayInputStream(String.join(" ", args).getBytes());
        }
        else {
            input = System.in;
        }
        PrintStream output = System.out;

        // Create factory for Triangle object
        // the default Locale.US is used for double format
        TriFactory factory = new TriFactory(input, output);

        // Create triangle object
        // Use input/output defined above
        Triangle triangle = factory.createTriangle();

        // Verify triangle object
        if (triangle == null) {
            output.println("ERROR: " + factory.getErrorLast());
            System.exit(-1);
        }

        // Get and print triangle kind:
        // equilateral, isosceles or scalene
        // one more kind is INVALID - the sides values are wrong
        Triangle.TriKind kind = triangle.getKind();
        output.print("Triangle (" + triangle.getA() + ", " + triangle.getB() + ", " + triangle.getC() + ") " + " - " + kind.getMsg());
    }

}

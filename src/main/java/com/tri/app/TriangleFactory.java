package com.tri.app;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * Simple wrapper for input triangle sides and creating Triangle object
 * We use this factory s we want to separate triangle object from entering sides by users
 *
 * Created by ichebyki on 24.04.2017.
 */
public class TriangleFactory {
    // We want '.' as decimal delimiter
    // So, set US format as default
    private Locale locale = Locale.US;

    // Input stream, System.in is default
    private InputStream in;

    // Output stream, System.out is default
    private PrintStream out;

    // Error code after creating triangle
    private TriError errorLast = TriError.NO_ERROR;

    // The latest created triangle
    private Triangle triangleLast;

    // Sometimes, we want to hide user prompt
    private boolean showPrompt = true;

    // Default constructor
    public TriangleFactory() {
        this(Locale.US, System.in, System.out);
    }

    // Setting undefined Locale
    public TriangleFactory(Locale locale) {
        this(locale, System.in, System.out);
    }

    // Setting undefined input/output streams
    public TriangleFactory(InputStream in, PrintStream out) {
        this(Locale.US, in, out);
    }

    // Full customization of Locale and input/output streams
    public TriangleFactory(Locale locale, InputStream in, PrintStream out) {
        this.locale = locale;
        this.in = in;
        this.out = out;
    }

    public Locale getLocale() {
        return locale;
    }

    public TriError getErrorLast() {
        return errorLast;
    }

    public Triangle getTriangleLast() {
        return triangleLast;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isShowPrompt() {
        return showPrompt;
    }

    public void setShowPrompt(boolean showPrompt) {
        this.showPrompt = showPrompt;
    }

    /** Actual triangle creation with possible interactive user input
     * Here we may add some special verification (no any now)
     * For example we can enable non-obtuse triangle only or vise versa
     * Another input args verification may be added for fine grained diagnostics
     * For example if input contains less than three sides values or wrong format
     *
     * @return Triangle object
     */
    public Triangle createTriangle() {
        double A, B, C;
        Scanner input = new Scanner(in);

        errorLast = TriError.NO_ERROR;
        triangleLast = null;

        // Set locale, default is US
        // Some locale defines ',' as decimal point
        input.useLocale(locale);

        // Just prompt user for triangles sides
        if (showPrompt) {
            out.print("Please enter three triangle sides values\n"
                    + "The values must have decimal(10) radix,\n"
                    + "double format and are separated by spaces:\n");
        }

        // Read a-side value
        if (input.hasNextDouble()) {
            A = input.nextDouble();
        } else {
            errorLast = TriError.INVALID_INPUT_FORMAT;
            return null;
        }

        // Read b-side value
        if (input.hasNextDouble()) {
            B = input.nextDouble();
        } else {
            errorLast = TriError.INVALID_INPUT_FORMAT;
            return null;
        }

        // Read c-side value
        if (input.hasNextDouble()) {
            C = input.nextDouble();
        } else {
            errorLast = TriError.INVALID_INPUT_FORMAT;
            return null;
        }

        // closing the scanner
        input.close();

        // Create triangle object
        // If A, B, C contain some non-good values return null
        triangleLast = new Triangle(A, B, C);
        if (triangleLast.getKind() == Triangle.TriKind.INVALID) {
            errorLast = TriError.INVALID_SIDES_VALUES;
            return null;
        }

        return triangleLast;
    }
}

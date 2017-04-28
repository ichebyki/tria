package com.tri.app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

/**
 * Simple wrapper for input triangle sides and creating Triangle object
 * We use this factory s we want to separate triangle object from entering sides by users
 *
 * Created by ichebyki on 24.04.2017.
 */
public class TriFactory {
    // Logger
    private static final Logger LOG = Logger.getLogger("server." + new Object(){}.getClass().getEnclosingClass().getSimpleName());

    // Properties
    static private String propsFileName = "com/tri/app/TriFactory.properties";
    static private Properties props;

    static {
        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();

        // Load properties file for errors diag
        props = parseProps(propsFileName);
        if (props == null) {
            System.exit(-2);
        }
    }

    // We want '.' as decimal delimiter
    // So, set US format as default
    private Locale locale = Locale.US;

    // Input stream, System.in is default
    private InputStream in;

    // Output stream, System.out is default
    private PrintStream out;

    // Error code after creating triangle
    private String errorLast = "NO_ERROR";

    // The latest created triangle
    private Triangle triangleLast;

    // Sometimes, we want to hide user prompt
    private boolean showPrompt = true;

    // Default constructor
    public TriFactory() {
        this(Locale.US, System.in, System.out);
    }

    // Setting undefined Locale
    public TriFactory(Locale locale) {
        this(locale, System.in, System.out);
    }

    // Setting undefined input/output streams
    public TriFactory(InputStream in, PrintStream out) {
        this(Locale.US, in, out);
    }

    // Full customization of Locale and input/output streams
    public TriFactory(Locale locale, InputStream in, PrintStream out) {
        this.locale = locale;
        this.in = in;
        this.out = out;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getErrorLast() {
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
        errorLast = props.getProperty("NO_ERROR");
        triangleLast = null;

        // Just prompt user for triangles sides
        if (showPrompt) {
            out.print("Please enter three triangle sides values\n"
                    + "The values must have decimal(10) radix,\n"
                    + "double format and are separated by spaces:\n");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            double[] abc = new double[3];
            int side_index = 0;
            String line;

            while (side_index < 3) {
                line = reader.readLine();
                if (line == null) {
                    errorLast = props.getProperty("INVALID_ARGS_COUNT");
                    LOG.error(errorLast);
                    return null;
                }

                try (Scanner input = new Scanner(new ByteArrayInputStream(line.getBytes()))) {
                    // Set locale, default is US
                    // Some locale defines ',' as decimal point
                    input.useLocale(locale);

                    // Read sides values
                    while (side_index < 3 && input.hasNext()) {
                        if (input.hasNextDouble()) {
                            abc[side_index++] = input.nextDouble();
                        } else {
                            errorLast = props.getProperty("INVALID_INPUT_FORMAT");
                            LOG.error("Bad value for the side #" + side_index + ": " + errorLast);
                            return null;
                        }
                    }

                    // Verify that there are no more arguments
                    if (input.hasNext()) {
                        errorLast = props.getProperty("TOO_MANY_ARGUMENTS");
                        LOG.warn(errorLast);
                    }
                }
            }

            // Create triangle object
            // If A, B, C contain some non-good values exception will be thrown
            triangleLast = new Triangle(abc[0], abc[1], abc[2]);
            LOG.info("Triangle successfully created");
        } catch (Triangle.TriangleCreateException e) {
            errorLast = props.getProperty("INVALID_SIDES_VALUES");
            LOG.error("Can't create Triangle object. Please check sides values");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return triangleLast;
    }


    private static Properties parseProps(String propsFile) {
        Properties props = new Properties();
        ClassLoader cl = TriFactory.class.getClassLoader();

        try (InputStream is = cl.getResourceAsStream(propsFile)) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    public static void setLoggingLevel(Level off) {
        LOG.setLevel(Level.OFF);
    }

}

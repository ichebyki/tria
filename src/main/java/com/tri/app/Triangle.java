package com.tri.app;

/**
 * Actual triangle object class
 * It just saves three sides and kind of the triangle
 * While creating we verify sides values and calculate the kind
 *
 * Created by ichebyki on 22.04.2017.
 */
public class Triangle {
    private double a, b, c;
    private TriKind kind;

    // We want to create the triangle with actual sides values only
    // So hide the default constuctor
    private Triangle() {
    }

    /**
     * Actual triangle constructor
     * It saves three sides then verifies the sides values
     * Then determines the kind of the triangle
     * The kind is a TriKind enum
     *
     * @param a
     * @param b
     * @param c
     */
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        verifyThenKind();
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
        verifyThenKind();
    }

    public void setA(long a) {
        this.a = a;
        verifyThenKind();
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
        verifyThenKind();
    }

    public void setB(long b) {
        this.b = b;
        verifyThenKind();
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
        verifyThenKind();
    }

    public void setC(long c) {
        this.c = c;
        verifyThenKind();
    }

    /**
     *  Enum for triangle kind
     */
    public enum TriKind {
        EQUILATERAL(1, "Triangle is equilateral"),
        ISOSCELES(2, "Triangle is isosceles"),
        SCALENE(3, "Triangle is scalene"),
        INVALID_SIDES(4, "Bad sides values");

        private final int id;
        private final String msg;

        TriKind(int id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public int getId() {
            return this.id;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    /**
     * Find what triangle kind is
     *
     * @return TriKind
     */
    public TriKind getKind() {
        if (kind==null) {
            if (a == b && b == c) {
                kind = TriKind.EQUILATERAL;
            } else if (a == b || b == c || c == a) {
                kind = TriKind.ISOSCELES;
            } else {
                kind = TriKind.SCALENE;
            }
        }
        return kind;
    }

    /** Verify triangle sides
     * every side must be > 0 && <= MAX_DOUBLE && all three sides must
     * to satisfy triangle condition
     *
     * @return boolean
     */
    public boolean verify() {
        // The triangle sides must be non-zero positive positive
        if (a <= 0.0 || b <= 0.0 || c <= 0.0) {
            return false;
        }

        // We accept only finit values
        // i.e. not greater than Double.MAX_VALUE
        if (a > Double.MAX_VALUE || b > Double.MAX_VALUE || c > Double.MAX_VALUE) {
            return false;
        }

        // For any triangle there must be true the following:
        // (A + B) > C && (B + C) > A && (C + A) > B
        // It's ok if some (x + y) == +Infinity
        //
        // But, there is a problem with max and min values:
        // max_positive + min_positive == max_positive for double,
        // so we use one more special comparison
        if ((a + b) < c || (b + c) < a || (c + a) < b) {
            return false;
        }
        if ((a + b) == c && a != c && b != c) {
            return false;
        }
        if ((b + c) == a && b != a && c != a) {
            return false;
        }
        if ((a + c) == b && a != b && c != b) {
            return false;
        }

        return true;
    }

    /**
     *  Just call verify() then getKind() in one place
     */
    private void verifyThenKind() {
        if (verify()) {
            kind = getKind();
        }
        else {
            kind = TriKind.INVALID_SIDES;
        }
    }

}

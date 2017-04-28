package com.tri.app;

/**
 * Actual triangle object class
 * It just saves three sides and kind of the triangle
 * While creating we verify sides values and calculate the kind
 * Verification is simple,
 * so, created Triangle object may be still invalid triangle
 * which case is saved as INVALID kind
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
     * @param a - triangle side
     * @param b - triangle side
     * @param c - triangle side
     */
    public Triangle(double a, double b, double c) throws TriangleCreateException {
        this.a = a;
        this.b = b;
        this.c = c;

        verifyThenSetKind();
        if (this.kind == TriKind.UNDEFINED) {
            throw new TriangleCreateException();
        }
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
        verifyThenSetKind();
    }

    public void setA(long a) {
        this.a = a;
        verifyThenSetKind();
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
        verifyThenSetKind();
    }

    public void setB(long b) {
        this.b = b;
        verifyThenSetKind();
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
        verifyThenSetKind();
    }

    public void setC(long c) {
        this.c = c;
        verifyThenSetKind();
    }

    /**
     *  Enum for triangle kind
     */
    public enum TriKind {
        UNDEFINED(0, "UNDEFINED"),
        EQUILATERAL(1, "Triangle is equilateral"),
        ISOSCELES(2, "Triangle is isosceles"),
        SCALENE(3, "Triangle is scalene");

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
            kind = TriKind.UNDEFINED;
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
    private boolean verify() {
        // The triangle sides must be non-zero positive positive
        if (a <= 0.0 || b <= 0.0 || c <= 0.0) {
            return false;
        }

        // This a very simple verification
        // We accept only finit values
        // i.e. not greater than Double.MAX_VALUE
        // So, created Triangle object may be still invalid triangle
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
     *  Just call verify() then set kind in one place
     */
    private void verifyThenSetKind() {
        if (verify()) {
            kind = getKind();
        }
        else {
            kind = TriKind.UNDEFINED;
        }
    }

    public class TriangleCreateException extends Exception {}

}

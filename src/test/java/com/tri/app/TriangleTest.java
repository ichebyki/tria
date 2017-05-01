package com.tri.app;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Testsuite for Triangle class
 *
 * Created by ichebyki on 29.04.2017.
 */
public class TriangleTest {
    static {
        TriFactory.setLoggingLevel(Level.OFF);
    }

    public Triangle createTriangle(double a, double b, double c) throws Triangle.TriangleCreateException {
        return new Triangle(a, b, c);
    }

    @Test
    public void getA() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        Assert.assertEquals(2.2, tri.getA(), 0);
    }

    @Test
    public void setA() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        tri.setA(6.66666);
        Assert.assertEquals(6.66666, tri.getA(), 0);
    }

    @Test
    public void setA_minus() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        boolean set = tri.setA(-6.66666);
        Assert.assertFalse(set);
        Assert.assertEquals(2.2, tri.getA(), 0);
    }

    @Test
    public void setA1() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        tri.setA(6L);
        Assert.assertEquals(6.0, tri.getA(), 0);
    }

    @Test
    public void getB() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        Assert.assertEquals(3.33, tri.getB(), 0);
    }

    @Test
    public void setB() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        tri.setB(6.6);
        Assert.assertEquals(6.6, tri.getB(), 0);
    }

    @Test
    public void setB1() throws Exception {
        Triangle tri = createTriangle(2.2, 3.33, 4.444);
        tri.setB(6L);
        Assert.assertEquals(6.0, tri.getB(), 0);
    }

    @Test
    public void getC() throws Exception {
        Triangle tri = createTriangle(3.33, 4.444, 2.2);
        Assert.assertEquals(2.2, tri.getC(), 0);
    }

    @Test
    public void setC() throws Exception {
        Triangle tri = createTriangle(3.33, 4.444, 2.2);
        tri.setC(6.66666);
        Assert.assertEquals(6.66666, tri.getC(), 0);
    }

    @Test
    public void setC1() throws Exception {
        Triangle tri = createTriangle(3.33, 4.444, 2.2);
        tri.setC(6L);
        Assert.assertEquals(6.0, tri.getC(), 0);
    }

    @Test
    public void getKind() throws Exception {
        Triangle tri = createTriangle(3.33, 4.444, 2.2);
        Assert.assertEquals(Triangle.TriKind.SCALENE, tri.getKind(false));
    }

    @Test
    public void getKind1() throws Exception {
        Triangle tri = createTriangle(3.33, 4.444, 3.33);
        Assert.assertEquals(Triangle.TriKind.ISOSCELES, tri.getKind(false));
    }

    @Test
    public void getKind2() throws Exception {
        Triangle tri = createTriangle(4.444, 4.444, 4.444);
        Assert.assertEquals(Triangle.TriKind.EQUILATERAL, tri.getKind(false));
    }

    @Test
    public void changeKind() throws Exception {
        Triangle tri = createTriangle(4.444, 4.444, 4.444);
        Assert.assertEquals(Triangle.TriKind.EQUILATERAL, tri.getKind(false));
        tri.setA(5.5555);
        Assert.assertEquals(Triangle.TriKind.ISOSCELES, tri.getKind(false));
        tri.setB(4.4);
        Assert.assertEquals(Triangle.TriKind.SCALENE, tri.getKind(false));
    }

}
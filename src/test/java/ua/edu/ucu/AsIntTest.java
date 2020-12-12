package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class AsIntTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testAsIntStreamSum() {
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testAsIntStreamAverage() {
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.00001);
    }

    @Test
    public void testAsIntStreamMax() {
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testAsIntStreamMin() {
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }
}

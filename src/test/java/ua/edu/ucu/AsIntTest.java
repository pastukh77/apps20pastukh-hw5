package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class AsIntTest {
    private IntStream intStream;
    private IntStream empty;
    private IntStream maxMinStream;

    @Before
    public void init() {
        intStream = AsIntStream.of(-1, 0, 1, 2, 3);
        empty = AsIntStream.of();
        maxMinStream = AsIntStream.of(9, 8, 7, 6, 5);
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

    @Test
    public void testEmptyToArray() {
        int[] expResult = {};
        int[] result = empty.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        empty.sum();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCount() {
        empty.count();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage() {
        empty.average();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin() {
        empty.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax() {
        empty.max();
    }

    @Test
    public void testMax() {
        int expResult = 9;
        int result = maxMinStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMap() {
        int[] expResult = {0, 1, 2, 3, 4};
        int[] result = intStream.map(x -> x + 1).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        int[] expResult = {3};
        int[] result = intStream.filter(x -> x > 2).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = {-1, 1, 0, 0, 1, 1, 2, 4, 3, 9};
        int[] result = intStream.flatMap(x ->
                AsIntStream.of(x, x*x)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        int expResult = 5;
        int result = intStream.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, result);
    }
}

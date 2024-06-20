package org.example.page5;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FibonacciMemoization {
// Calculate the nth fibonacci number recursively using memoization in javascript observing closure.

    Map<Integer, Integer> fibMemo = new HashMap<>();

    int nthFib(int n) {
        if (n<0) throw new RuntimeException();
        if (n==1) return 0;
        if (n==2) return 1;

        if (fibMemo.get(n) != null) return fibMemo.get(n);

        int result = nthFib(n-1) + nthFib(n-2);
        fibMemo.put(n, result);
        return result;
    }

    private int[] fibonacciSequenceForTests = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
    @Test
    public void basicTests() {
        int[] fib = fibonacciSequenceForTests;
        assertEquals(fib[2], nthFib(3));
        assertEquals(fib[7], nthFib(8));
        assertEquals(fib[4], nthFib(5));
        assertEquals(fib[3], nthFib(4));
    }
    @Test
    public void beginningTests() {
        int[] fib = fibonacciSequenceForTests;
        assertEquals(fib[0], nthFib(1));
        assertEquals(fib[1], nthFib(2));
    }
}
/*
Learnt:
- memoization is essentially caching for algorithms and is normally kept in a hashmap
- recursive methods are okay!
 */

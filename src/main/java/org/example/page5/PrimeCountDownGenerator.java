package org.example.page5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class PrimeCountDownGenerator {
    /*
    Use a generator function to yield the primes down from n.
            E.g. function* countdownPrimes (n)
    Further points for tests and outputting the results as a single array afterwards.
     */
    private final List<Integer> primeMemo = new ArrayList<>(List.of(2));
    private int alreadyCalculatedUpTo = 2;

    public int[] countDownPrimes(int n) {
        if (n <= alreadyCalculatedUpTo) return outputArray(n);
        memoPrimesToN(n);
        return outputArray(n);
    }

    private void memoPrimesToN(int n) {
        if (n <= alreadyCalculatedUpTo) return;
        while (alreadyCalculatedUpTo < n) {
            addIsNextNumPrime();
        }
    }

    private void addIsNextNumPrime() {
        int n = alreadyCalculatedUpTo + 1;
        for (Integer prime : primeMemo) {
            if (n % prime == 0) {
                alreadyCalculatedUpTo++;
                return;
            }
        }
        primeMemo.add(n);
        alreadyCalculatedUpTo++;
    }


    private int[] outputArray(int n) {
        if (alreadyCalculatedUpTo < n) throw new RuntimeException("Using this method too early");
        return primeMemo.stream()
                .filter(p -> p <= n)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int[] testPrimeArray = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293};

    private int[] TestPrimeParser(int n) {
        return Arrays.stream(testPrimeArray)
                .filter(p -> p <= n)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Test
    public void basicTest() {
        assertArrayEquals(TestPrimeParser(5), countDownPrimes(5));
        assertArrayEquals(TestPrimeParser(10), countDownPrimes(10));
    }
    @Test
    public void testUsingMemo() {
        assertArrayEquals(TestPrimeParser(10), countDownPrimes(10));
        assertArrayEquals(TestPrimeParser(3), countDownPrimes(3));
    }
    @Test
    public void testBeginning() {
        assertArrayEquals(TestPrimeParser(0), countDownPrimes(0));
        assertArrayEquals(TestPrimeParser(1), countDownPrimes(1));
    }
}
/*
Learnt:
    Memos are useful!
    If using an array stream with ints, need to add .boxed if wanting to sort (TestPrimeParser)
    To collect a stream into an array need to
                .mapToInt(Integer::intValue)
                .toArray();
    To sort a stream into reverse order
                .sorted(Comparator.reverseOrder())
    To test two arrays equal use "assertArrayEquals"
 */

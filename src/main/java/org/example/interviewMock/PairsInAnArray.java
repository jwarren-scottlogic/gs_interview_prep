package org.example.interviewMock;

import org.junit.*;
import java.util.*;

public class PairsInAnArray {



     @Test
     public void test1Pair() {
       int[] array = {1,5};
       int target = 6;
       int expected = 1;
       int actual = getNumberOfPairs(array, target);
       Assert.assertEquals(expected, actual);
     }

     @Test
     public void test2Pairs() {
       int[] array = {1,5,3,3};
       int target = 6;
       int expected = 2;
       int actual = getNumberOfPairs(array, target);
       Assert.assertEquals(expected, actual);
     }

    @Test
    public void test3PairsIn3Numbers() {
        int[] array = {3,3,3};
        int target = 6;
        int expected = 3;
        int actual = getNumberOfPairs(array, target);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDuffNumbers() {
        int[] array = {1,5,2,2,3,3};
        int target = 6;
        int expected = 2;
        int actual = getNumberOfPairs(array, target);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNegatives() {
        int[] array = {1,5,2,-2,3,3,8};
        int target = 6;
        int expected = 3;
        int actual = getNumberOfPairs(array, target);
        Assert.assertEquals(expected, actual);
    }


    public static int getNumberOfPairs(int[] array, int target) {
        int numberOfPairs = 0;
        for(int j=0;j<array.length;j++) {
            int firstPart = array[j];
            int[] newArray = Arrays.copyOfRange(array,j+1,array.length);
            // System.out.println(Arrays.toString(newArray));
            numberOfPairs += getNumberOfPairsIterate(newArray, (target-firstPart));
            // System.out.println("numberOfPairs: "+numberOfPairs);
        }
        return numberOfPairs;
    }

    public static int getNumberOfPairsIterate(int[] array, int otherPair) {
        System.out.println("-------------------------");
        System.out.println(Arrays.toString(array));
        System.out.println("otherPair"+otherPair);

        int numberOfPairs = 0;
        for (int j : array) {
            if (otherPair == j) numberOfPairs++;
        }
        System.out.println("numberOfPairs: "+numberOfPairs);

        System.out.println("-------------------------");
        return numberOfPairs;
    }


}

/*
learnt:
To see arrays: Arrays.toString(newArray));
Don't just delete values to replace them, think whether there was a reason I put it like that.
look at Ryan's solution
loop for arrays: for (int j : array) {

 */


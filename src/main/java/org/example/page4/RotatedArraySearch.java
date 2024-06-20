package org.example.page4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RotatedArraySearch {
// Find the smallest number in a rotated sorted array in O(log n) time i.e. [4,5,6,1,2,3] would be 1

    public int findSmallestNumber(int[] array) {
        int size = array.length;
        int midInd = getMidInd(size);
        int endInd = size - 1;

        if (firstElementSmallest(array)) return array[0];
        int[] bounds = getLowerAndUpperBoundIndex(0, midInd, endInd, array);
        int[] subArray = Arrays.copyOfRange(array, bounds[0], bounds[1]+1);

        while (!firstElementSmallest(subArray)) {
            if (subArray.length == 2) return subArray[1]; // if it's not the first element (while condition), it's the second
            size = subArray.length;
            midInd = getMidInd(size);
            endInd = size - 1;
            bounds = getLowerAndUpperBoundIndex(0, midInd, endInd, subArray);
            subArray = Arrays.copyOfRange(subArray, bounds[0], bounds[1]+1);
        }

        return subArray[0];
    }

    private boolean firstElementSmallest(int[] array) {
        int firstLastElementDiff = array[array.length-1]-(array[0]-1); // subtracting the imaginary entry before the 0th which would be one less
        return firstLastElementDiff == array.length;
    }

    private int[] getLowerAndUpperBoundIndex(int beginInd, int midInd, int endInd, int[] array) {
        boolean numberToFindInTopHalf = (midInd - beginInd) == (array[midInd] - array[beginInd]);

        int lowerBoundInd = numberToFindInTopHalf ? midInd : beginInd ;
        int upperBoundInd = numberToFindInTopHalf ? endInd : midInd;
        return new int[]{lowerBoundInd, upperBoundInd};
    }

    private int getMidInd(int size) {
        boolean isSizeOdd = size%2 == 1;
        int midInd = Math.floorDiv(size, 2)-1;
        if (isSizeOdd) midInd++;
        return midInd;
    }


    @Test
    public void exampleTest(){
        int[] array = {4,5,6,1,2,3};
        assertEquals(1, findSmallestNumber(array));
    }
    @Test
    public void oddTest(){
        int[] array = {4,5,6,7,1,2,3};
        assertEquals(1, findSmallestNumber(array));
    }
    @Test
    public void oddTest2(){
        int[] array = {7,1,2,3,4,5,6};
        assertEquals(1, findSmallestNumber(array));
    }
    @Test
    public void beginningTest(){
        int[] array = {2,3,4,5,6,7};
        assertEquals(2, findSmallestNumber(array));
    }
    @Test
    public void endTest(){
        int[] array = {2,3,4,5,6,1};
        assertEquals(1, findSmallestNumber(array));
    }
}

/*
 * Learnt:
 * Very easy to write tests in the class, just add @Test on top of method (if no mocking)
 * Arrays are immutable, so they need to be copied -> Arrays.copyOfRange(array, bounds[0], bounds[1]+1);
 *          (note the above needs to have an index+1)
 * Try to simplify problem to easiest solution
 * big O: O(logn) -> this speed if you half the possibilities each iteration. (Or reduce by a third, or a quarter etc).
 */
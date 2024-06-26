package org.example.interviewMock;

import org.junit.*;
import java.util.*;

public class SnowAndHills {
    public static int getSnowInValley(int[] hills) {
        int snow = 0;
        int nextHillIndex = 0;
        while (nextHillIndex < hills.length) {
            int currentHillIndex = getStartHill(hills, nextHillIndex);
            if (currentHillIndex == -1) break;
            nextHillIndex = getEndHill(hills, currentHillIndex);
            if (nextHillIndex == -1) break;
            int snowInValley = getSnowInValley(hills, currentHillIndex, nextHillIndex);
            snow += snowInValley;
        }
        return snow;
    }

    private static int getStartHill(int[] hills, int previousEndIndex) {
        for (int i = previousEndIndex; i < hills.length; i++) {
            if (i == hills.length - 1) return -1;
            boolean nextSectionBelow = hills[i + 1] < hills[i];
            if (nextSectionBelow) return i;
        }
        return -1;
    }

    private static int getEndHill(int[] hills, int startHillIndex) {
        for (int i = startHillIndex; i < hills.length; i++) {
            boolean nextHillAbove = hills[startHillIndex] < hills[i];
            if (nextHillAbove) return i;
        }
        //the next tallest hill is below
        int[] laterHills = Arrays.copyOfRange(hills, startHillIndex + 1, hills.length - 1);
        if (laterHills.length == 0) return -1;
        int laterHillsMax = Arrays.stream(laterHills).max().orElse(laterHills[0]);
        int laterHillsMaxIndex = Arrays.binarySearch(laterHills, laterHillsMax);

        return (startHillIndex + 1) + laterHillsMaxIndex;
    }

    private static int getSnowInValley(int[] hills, int startIndex, int endIndex) {
        int height = hills[startIndex] > hills[endIndex] ? hills[endIndex] : hills[startIndex];
        int snow = height * (endIndex - (startIndex + 1));
        for (int i = startIndex + 1; i < endIndex; i++) {
            snow -= hills[i];
        }
        return snow;
    }


    @Test
    public void getSnowTest() {
        int[] hills = new int[]{0, 1, 3, 0, 2, 1, 0, 4, 0, 1, 3, 0};
        int actualSnow = getSnowInValley(hills);
        int expectedSnow = 14;
        Assert.assertEquals(expectedSnow, actualSnow);
    }

    @Test
    public void getSnowWithNegativesTest() {
        int[] hills = new int[]{0, 1, 3, -1, 2, 1, 0, 4, 0, 1, 3, 0};
        int actualSnow = getSnowInValley(hills);
        int expectedSnow = 15;
        Assert.assertEquals(expectedSnow, actualSnow);
    }

    @Test
    public void getSnowWithEqualsTest() {
        int[] hills = new int[]{0, 3, 3, 3, 0, 3, 0, 0, 0, 3, 3, 0};
        int actualSnow = getSnowInValley(hills);
        int expectedSnow = 12;
        Assert.assertEquals(expectedSnow, actualSnow);
    }

    @Test
    public void getSnowWithZerosTest() {
        int[] hills = new int[]{0, 0, 0, 0};
        int actualSnow = getSnowInValley(hills);
        int expectedSnow = 0;
        Assert.assertEquals(expectedSnow, actualSnow);
    }
}

/*
Learnt:
    To turn an int[] into List<Integer> can't use Arrays.asList because int and Integer don't match. Need to stream: Arrays.stream(intArray).boxed().toList();
    Get index of a value for an array: Arrays.binarySearch(anArray, theValue);
    Always think of edge cases -> -ve / 0 inputs, or assumptions made which may not be correct
    Try and simplify - I tried for the case for 3 peaks. But should have done for 2 peaks because that generalises more nicely.
 */
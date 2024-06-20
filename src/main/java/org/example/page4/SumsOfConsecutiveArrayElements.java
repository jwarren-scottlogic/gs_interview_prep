package org.example.page4;

import java.util.Arrays;

public class SumsOfConsecutiveArrayElements {
    public static void main (String[] args) {
        int[] array = {0};
        int target = 7;
        System.out.println("Smallest length of consecutive elements summing to "+target+ ", is: "+ consecutiveElementsLength(array, target));
    }
    private static int consecutiveElementsLength(int[] array, int target){
        int arraySum = Arrays.stream(array).sum();
        int sumBelowTarget = arraySum-target;
        if (sumBelowTarget<0) return sumBelowTarget;

        int minLength = Integer.MAX_VALUE;
        int increaseIndexAmount;
        for (int i=0; i < array.length; i += increaseIndexAmount){
            boolean reachedFinalElement = i == array.length-1;
            if (reachedFinalElement && array[i] < target) {
                break;
            }

            int currentSum = array[i];
            int currentLength = 1;
            while (currentSum<target){
                if (i+currentLength == array.length) {
                    currentLength = Integer.MAX_VALUE;
                    break;
                }
                currentSum += array[i+currentLength];
                currentLength++;
            }
            minLength = Math.min(minLength, currentLength);
            if (minLength ==1) return minLength;

            increaseIndexAmount = increaseIndexAmount(i,currentLength, array);
        }
        return minLength;
    }

    private static int increaseIndexAmount(int i, int currentLength, int[] array) {
        int increaseIndexAmount = 1;
        int indexOfNextElement = (i+currentLength-1)+ increaseIndexAmount;
        int nextElementInRange = (array.length-1) - indexOfNextElement;

        if (nextElementInRange>=0) {
            boolean nextIndexIsGreaterOrEqual = array[i + increaseIndexAmount] <= array[indexOfNextElement];
            while (!nextIndexIsGreaterOrEqual) {
                increaseIndexAmount++;
                indexOfNextElement++;
                nextElementInRange--;
                if (nextElementInRange>=0){
                    nextIndexIsGreaterOrEqual = array[i + increaseIndexAmount] >= array[indexOfNextElement];
                } else {
                    break;
                }
            }
        }
        return increaseIndexAmount;

    }
}

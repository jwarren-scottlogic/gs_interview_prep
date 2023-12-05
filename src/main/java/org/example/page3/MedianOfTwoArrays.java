package org.example.page3;

public class MedianOfTwoArrays {

    public static void main (String[] args) {
        int[] array1 = new int[]{1};
        int[] array2 = new int[]{2,4,5};
        double median = findMedian(array1, array2);
        System.out.println("Median: "+ median);
    }
    private static double findMedian(int[] array1, int[] array2) {
        int totalNumberOfInts = array1.length + array2.length;
        boolean isNumberOfIntsEven = totalNumberOfInts%2 == 0;
        int forLoopLimit = isNumberOfIntsEven ? totalNumberOfInts / 2 : (totalNumberOfInts -1) / 2;
        int i=0;
        int j=0;
        double maxValue = 0;
        double secondMaxValue = 0;
        for (int t=0; t<forLoopLimit;t++) {
            boolean nextNumberIsFromArray1 = (i< array1.length-1) && ((j < array2.length-1) || (array1[i] <= array2[j]));
            if (nextNumberIsFromArray1) {
                secondMaxValue = maxValue;
                maxValue = array1[i];
                i++;
            } else {
                secondMaxValue = maxValue;
                maxValue = array2[j];
                j++;
            }
        }
        return isNumberOfIntsEven ? (maxValue + secondMaxValue) / 2 : maxValue;
    }
}

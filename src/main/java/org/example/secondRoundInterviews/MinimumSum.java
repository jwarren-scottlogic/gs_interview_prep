package org.example.secondRoundInterviews;


import org.junit.*;
import java.util.*;
import java.util.stream.Collectors;

//aka Minimum Number of Numbers That Equal Target Number
public class MinimumSum {

    public int minSumNumber(int[] numbers, int target){
        if (target == 0) return 0;
        List<Integer> numbersSorted = Arrays.stream(numbers).boxed().sorted().collect(Collectors.toList());
        if (numbersContainTarget(numbersSorted, target)) return 1;
        return minSumNumberIterative(numbersSorted, target, 0);
    }

    private int minSumNumberIterative(List<Integer> numbers, int target, int numberOfIterations) {

        System.out.println("Target: " + target);
        System.out.println("Number of iterations: ");
        System.out.println(numberOfIterations);

        if (numbersContainTarget(numbers, target)) return numberOfIterations+1;
        if (numbers.size() == 1) return -1;
        int minSumNumber = -1;
        for (int i=0; i<numbers.size(); i++) {
            int largestNumber = numbers.get(i);
            if (largestNumber > target) {continue;}
//            System.out.println("largestNumber: " + largestNumber);
            List<Integer> newNumbers = numbers.subList(i+1, numbers.size());
//            System.out.println("newNumbers: " + newNumbers);
            int newTarget = target - largestNumber;
            int potentialMinSumNumber = minSumNumberIterative(newNumbers, newTarget, numberOfIterations+1);

//            System.out.println("potentialMinSumNumber: " + potentialMinSumNumber);
            boolean updateMinSumNumber = (minSumNumber == -1) ||((potentialMinSumNumber!=-1) && (potentialMinSumNumber < minSumNumber));

            if (updateMinSumNumber){
                minSumNumber = potentialMinSumNumber;
//                System.out.println("minSumNumber was updated to: " + minSumNumber);
            }
        }
        return minSumNumber;
    }

    private boolean numbersContainTarget(List<Integer> numbers, int target) {
        return numbers.stream().anyMatch(n -> n == target);
    }



 @Test
 public void iterative_numberContainsTarget_Test1() {
   int expected = 2;
   List<Integer> inputNumbers = List.of(5,0,3);
   int inputTarget = 5;
   int inputNumberOfIterations = 1;
   int actual = minSumNumberIterative(inputNumbers, inputTarget, inputNumberOfIterations);

   Assert.assertEquals(expected, actual);
 }

 @Test
 public void iterative_numberContainsTarget_Test2() {
   int expected = 18;
   List<Integer> inputNumbers = List.of(5,0,3);
   int inputTarget = 5;
   int inputNumberOfIterations = 17;
   int actual = minSumNumberIterative(inputNumbers, inputTarget, inputNumberOfIterations);

   Assert.assertEquals(expected, actual);
 }



    @Test
    public void iterative_contains1cycle_Test() {
        int expected = 2;
        List<Integer> inputNumbers = List.of(6,5,0,3);
        int inputTarget = 8;
        int inputNumberOfIterations = 0;
        int actual = minSumNumberIterative(inputNumbers, inputTarget, inputNumberOfIterations);

        System.out.println("final: " + actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void iterative_contains1cycle_Test2() {
        int expected = 4;
        List<Integer> inputNumbers = List.of(6,5,0,3);
        int inputTarget = 8;
        int inputNumberOfIterations = 2;
        int actual = minSumNumberIterative(inputNumbers, inputTarget, inputNumberOfIterations);

        System.out.println("final: " + actual);

        Assert.assertEquals(expected, actual);
    }

    private int[][][] testInputs = new int[][][] {
            // {input numbers to sum}, {target}, {answer}
            {{}, {0}, {0}},
            {{6, 6, 2, 3, 6, 6}, {11}, {3}}, //e.g. 6+2+3=11 (3 numbers)
            {{5,6,1,1,1,1,8,1,1,1,1,4}, {12}, {2}}, // 8+4=12
            {{5,6,8,10}, {30}, {-1}}, // no combination of these numbers = 30
    };

    @Test
    public void testInput0() {
        int[][] data = testInputs[0];
        int actual = minSumNumber(data[0], data[1][0]);
        int expected = data[2][0];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInput1() {
        int[][] data = testInputs[1];
        int actual = minSumNumber(data[0], data[1][0]);
        int expected = data[2][0];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInput2() {
        int[][] data = testInputs[2];
        int actual = minSumNumber(data[0], data[1][0]);
        int expected = data[2][0];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInput3() {
        int[][] data = testInputs[3];
        int actual = minSumNumber(data[0], data[1][0]);
        int expected = data[2][0];
        Assert.assertEquals(expected, actual);
    }
}

/*
Learnt:
    .filter(n -> n == target).findFirst().isPresent() can be swapped with .anyMatch(n -> n == target);
 */
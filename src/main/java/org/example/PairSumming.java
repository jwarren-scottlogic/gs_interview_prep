package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PairSumming {
    public static int pairCounter(List<Integer> inputList, int sum) {
        List<Integer> listOfNumbers = inputList.stream().sorted().collect(Collectors.toList());
        int maxNumberOfPairs = 0;
        int iteratorMax = listOfNumbers.size()/ 2 + 1;
        for (int i = 0;  i <= iteratorMax; i++){
            int listNumber = listOfNumbers.get(0);
            listOfNumbers.remove(0);
            int numberOfPairs = (int) listOfNumbers.stream().filter(n -> sum - listNumber == n).count();
            maxNumberOfPairs += numberOfPairs;
//            System.out.println(listOfNumbers);
//            System.out.println(numberOfPairs);
        }
        return maxNumberOfPairs;
    }

    public static void main(String[] args){
        List<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(5,4,2,6,2,3,1));
        int sumNumber = 8;
        System.out.println("Number of summing pairs to "+sumNumber+" => " + PairSumming.pairCounter(listOfNumbers, sumNumber));
    }
}








//public class PairSumming { //first attempt
//    public static int pairCounter(List<Integer> listOfNumbers, int sum) {
//        int maxNumberOfPairs = 0;
//        for (int listNumber : listOfNumbers){
//            int numberOfPairs = (int) listOfNumbers.stream().filter(n -> sum - listNumber == n).count();
//            maxNumberOfPairs += numberOfPairs;
//        }
//        return maxNumberOfPairs/2;
//    }
//
//    public static void main(String[] args){
//        List<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(5,4,2,6,2,3,1));
//        int sumNumber = 7;
//        System.out.println("Number of summing pairs to "+sumNumber+" => " + PairSumming.pairCounter(listOfNumbers, sumNumber));
//    }
//}

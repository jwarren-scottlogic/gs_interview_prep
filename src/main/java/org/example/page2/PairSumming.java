package org.example.page2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PairSumming {
    public static int pairCounter(List<Integer> inputList, int sum) {
        List<Integer> listOfNumbers = inputList.stream().sorted().collect(Collectors.toList());
        int maxNumberOfPairs = 0;
        for (int i = 0;  i <= listOfNumbers.size()/ 2 + 1; i++){
            int listNumber = listOfNumbers.get(0);
            listOfNumbers.remove(0);
            int numberOfPairs = (int) listOfNumbers.stream().filter(n -> sum - listNumber == n).count();
            maxNumberOfPairs += numberOfPairs;
        }
        return maxNumberOfPairs;
    }

    public static void main(String[] args){
        List<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(4,4,4));
        int sumNumber = 8;
        System.out.println("Number of summing pairs to "+sumNumber+" => " + PairSumming.pairCounter(listOfNumbers, sumNumber));
    }
}




//Recommended solution only O(n): (Jack Roper)
//public static int solution(int[] inputArray, int target) {
//    Map<Integer, Long> occurencesMap = Arrays.stream(inputArray).boxed().collect(Collectors.groupingBy(val -> val, Collectors.counting()));
//
//    int result = 0;
//    for(Map.Entry<Integer, Long> entry : occurencesMap.entrySet()) {
//        int currentVal = (int) entry.getKey();
//        if(currentVal > target / 2) {
//            continue;
//        }
//        int currentTarget = target - currentVal;
//        if(currentTarget == currentVal) { // if target is even and this is the midpoint
//            int occurences = entry.getValue().intValue();
//            if(occurences > 1) {
//                result += (occurences * (occurences - 1)) / (double) 2;
//            }
//        } else {
//            Long correspondingOccurences = occurencesMap.get(currentTarget);
//            if (correspondingOccurences == null) {
//                continue;
//            } else {
//                result += entry.getValue() * correspondingOccurences;
//            }
//
//        }
//    }
//
//    return result;



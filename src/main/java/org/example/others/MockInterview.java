package org.example.others;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

import static org.junit.Assert.assertEquals;


/**
 * Thom Hull 5/12/23
     * Given an array of length-2 String arrays, with each length-2 array containing a student’s
     * name and a string representation of an integral examination score, find the highest mean
     * score across all students.
     *
     * - different students could have sat different numbers of exams;
     * - exam scores can be negative;
     * - no need to return the name of the student who did best, just the score;
     * - if there are no students at all, return zero;
     * - if the mean for a student is not an integer, then use the highest integer that is
     *   lower than the exact mean
     */
    public class MockInterview {
        @Test
        public void testScores() {
            assertEquals(67, calculateHighestMeanScore(
                    new String[][]
                            {{"Charles", "50"},
                                    {"Bella", "67"},
                                    {"Charles", "80"},
                                    {"John", "49"}}));
        }

        private int calculateHighestMeanScore(String[][] scores) {
            if (scores.length ==0){
                return 0;
            }
            return getMaxScore(combineScores(scores));
        }

        private Map<String, List<Integer>> combineScores(String[][] scores){
            Map<String, List<Integer>> dictionary = new HashMap<>();

            for (String[] score : scores) {
                List<Integer> newDictionaryValue;
                if (dictionary.containsKey(score[0])){
                    List<Integer> currentDictionaryValue = dictionary.get(score[0]);
                    newDictionaryValue = List.of(currentDictionaryValue.get(0)+Integer.parseInt(score[1]), currentDictionaryValue.get(1) + 1);
                } else {
                    newDictionaryValue = List.of(Integer.parseInt(score[1]), 1);
                }
                dictionary.put(score[0], newDictionaryValue);
            }
            return dictionary;
        }

        private int getMaxScore(Map<String, List<Integer>> combinedScores){
            int maxScore = Integer.MIN_VALUE;
            for (List<Integer> combinedScore : combinedScores.values()){
                int score = combinedScore.get(0)/combinedScore.get(1);
                if (score > maxScore) {
                    maxScore = score;
                }
            }
            return maxScore;
        }

        public static void main(String[] args) {
            JUnitCore.main("MockInterview");
        }
    }


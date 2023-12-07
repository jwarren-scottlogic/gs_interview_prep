package org.example.page4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AnagramSets {

    public static void main(String[] args) {
        Set<String> inputWords = new HashSet<>(Arrays.asList("cat", "dog", "god", "cat", "odgcat", "dogcat"));
        System.out.println(getAnagramSet(inputWords));
    }

    private static Set<Set<String>> getAnagramSet(Set<String> inputWords) {
        Set<Set<String>> allAnagrams = new HashSet<>();
        for (String word : inputWords) {
            Set<String> anagramSet = new HashSet<>();
            anagramSet.add(word);
            inputWords.stream().filter(e ->wordsAreAnagrams(sortAlphabetically(word), e)).forEach(anagramSet::add);
            allAnagrams.add(anagramSet);
        }
        return allAnagrams;
    }

    private static String sortAlphabetically(String word){
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return Arrays.toString(chars);
    }

    private static boolean wordsAreAnagrams(String alphabeticallySortedWord, String word2) {
        return Objects.equals(alphabeticallySortedWord, sortAlphabetically(word2));
    }
}

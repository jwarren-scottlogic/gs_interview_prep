package org.example.page4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LargestAnagramFromDictionary {
    public static void main(String[] args) throws Exception {
        List<String> dictionary = List.of("apple", "pear", "reap", "peal", "paple");
        String anagram = "pplea";
        System.out.println(getLongestAnagrams(dictionary,anagram));
    }
    private static List<String> getLongestAnagrams(List<String> dictionary, String string) throws Exception {
        List<String> dictionaryAnagrams = new ArrayList<>();
        Set<String> allAnagrams = getAllAnagrams(string);
        int largestDictionaryWord = dictionary.stream().mapToInt(String::length).max().orElseThrow(() -> new Exception("Empty Dictionary"));
        int anagramLength = string.length();
        for (int i=0; i < anagramLength-(largestDictionaryWord); i++) {
            allAnagrams = removeFirstCharacter(allAnagrams);
        }

        boolean firstTime =true;
        while (dictionaryAnagrams.isEmpty()) {
            if (allAnagrams.contains("") || allAnagrams.isEmpty()) return Collections.emptyList();
            if(!firstTime) allAnagrams = removeFirstCharacter(allAnagrams);
            firstTime = false;

            for (String anagram : allAnagrams) {
                if (dictionary.contains(anagram)){
                    dictionaryAnagrams.add(anagram);
                }
            }
        }

        return dictionaryAnagrams;
    }

    private static Set<String> getAllAnagrams(String anagram){
        return (permute(anagram, 0, anagram.length()-1, new HashSet<>()));
    }

    private static Set<String> permute(String string, int startingIndex, int endIndex, Set<String> allAnagrams) {

        if (startingIndex == endIndex)
            allAnagrams.add(string);
        else {
            for (int i = startingIndex; i <= endIndex; i++) {
                string = swapStringLetters(string, startingIndex, i);
                permute(string, startingIndex + 1, endIndex, allAnagrams);
                string = swapStringLetters(string, startingIndex, i);
            }
        }
        return allAnagrams;
    }

    public static String swapStringLetters(String string, int letter1Index, int letter2Index)
    {
        char temp;
        char[] charArray = string.toCharArray();
        temp = charArray[letter1Index];
        charArray[letter1Index] = charArray[letter2Index];
        charArray[letter2Index] = temp;
        return String.valueOf(charArray);
    }

    private static Set<String> removeFirstCharacter(Set<String> allAnagrams){
        return allAnagrams.stream().map(s -> s.substring(1)).collect(Collectors.toSet());
    }

}

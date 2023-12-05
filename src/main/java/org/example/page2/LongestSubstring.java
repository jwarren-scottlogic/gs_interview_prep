package org.example.page2;
import java.util.Map;

public class LongestSubstring {
    public static void main(String[] args) {
        String input = "aaaaabbbccddddaaa";
        Map.Entry<String, Integer> longestSubstring = longestSubstring(input);
        System.out.println("longestSubstring: " + longestSubstring.getKey() +". First appearing at index:" +longestSubstring.getValue());
        }

        public static Map.Entry<String, Integer> longestSubstring(String input) {
        Map.Entry<String, Integer> longestSubstring = Map.entry("", 0);
        int counter = 0;
        char currentLetter = 0;
        int subStringIndexStart = 0;
        for (int i = 0; i < input.length(); i++) {
            if (currentLetter != input.charAt(i)) {
                if (longestSubstring.getKey().length() <= counter) {
                    longestSubstring = Map.entry(String.valueOf(currentLetter).repeat(counter), subStringIndexStart);
                }
                currentLetter = input.charAt(i);
                counter = 1;
                subStringIndexStart = i;
            } else {
                counter++;
            }
        }
        return longestSubstring;
        }
}

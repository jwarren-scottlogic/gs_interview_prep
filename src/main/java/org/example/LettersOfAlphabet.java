package org.example;

public class LettersOfAlphabet {

    public static String missingCharacters(String inputString){
        StringBuilder missingCharacters = new StringBuilder();
        for (char letter = 'a'; letter <= 'z'; letter++){
            if (!inputString.contains(String.valueOf(letter))) {
                missingCharacters.append(letter);
            }
        }
        return missingCharacters.toString();
    }


    public static void main(String[] args){
        String inputString = "abcaaaadefg";
        System.out.println(missingCharacters(inputString));
    }
}

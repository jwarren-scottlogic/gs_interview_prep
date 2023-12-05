package org.example.page1;

public class SingleCharacterSearch {

    // This is n^2 (can do in n apparently big O)
    public static char findCharacter(String inputString) throws Exception{
        while (inputString.length()>0){
            char letter = inputString.charAt(0);
            int nextOccurrence = inputString.indexOf(letter, 1);
            if (nextOccurrence == -1) {
                return inputString.charAt(0);
            }
            inputString=inputString.replaceAll(String.valueOf(letter), "");
        }
        throw new Exception("No single character found.");
    }


    public static void main(String[] args) {
        String input = "";
        try {
            char singleCharacter = findCharacter(input);
            System.out.println("single character: " + singleCharacter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

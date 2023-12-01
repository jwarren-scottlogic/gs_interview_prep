package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RunLengthEncoder {
    public static String runLengthEncoder(String inputString) {
        String outputString = "";
        List<String> inputStringList = new ArrayList<>(List.of(inputString.split("")));
        for (int j = 0; j < inputStringList.size();) {
            int counter = 0;
            String letter = inputStringList.get(j);

            for (int i = j; i < inputStringList.size(); i++) {
                boolean currentLetterEqualsPrevious = Objects.equals(inputStringList.get(i), letter);

                if (currentLetterEqualsPrevious) {
                    counter++;
                }

                boolean isiLastCharacter = (i == inputStringList.size()-1);
                if (!currentLetterEqualsPrevious || (isiLastCharacter)){
                    j = i;
                    outputString = outputString.concat(letter + counter);
                    break;
                }
            }

            boolean isjLastCharacter = (j == inputStringList.size()-1);
            if ((isjLastCharacter)) {
                String lastCharacter = inputStringList.get(inputStringList.size()-1);
                if (!Objects.equals(lastCharacter, letter)) {
                    outputString = outputString.concat(lastCharacter + counter);
                }
                break;
            }
        }
        return outputString;
    }

        public static void main(String[] args){
            System.out.println(runLengthEncoder("abggsss"));
        }
    }

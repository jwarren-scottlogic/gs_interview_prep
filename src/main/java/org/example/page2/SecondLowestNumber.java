package org.example.page2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SecondLowestNumber {
    public static void main(String[] args) {
        List<Number> inputList = List.of(3.3, 2.2,5,-2,-3,7,8343);
        System.out.println("Second Lowest Number: " + secondLowestNumber(inputList));
    }

    private static Number secondLowestNumber(List<Number> inputList) {
        if (inputList.size() < 2){
            return 0;
        }
        List<Number> outputList = new ArrayList<>(inputList);
        outputList.sort(Comparator.comparing(Number::doubleValue));
        return outputList.get(1);
    }
}

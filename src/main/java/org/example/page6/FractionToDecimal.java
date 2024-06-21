package org.example.page6;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FractionToDecimal {
//    Question: given a numerator and denominator [num, denom],
//    show the result as a string in the way that ½ -> 0.5 or 4/3 -> 1.(3).
//    In case the decimal part is cyclic it should be surrounded by brackets.

    public String fractionToDecimal(int num, int denom) {
        validation(denom);

        // if negative
        StringBuilder answerBuilder = new StringBuilder();
        if (num < 0 ^ denom < 0) answerBuilder.append("-");
        int numSanitised = num > 0 ? num : num * -1;
        int denomSanitised = denom > 0 ? denom : denom * -1;


        int wholes = numSanitised / denomSanitised;
        int remainder = numSanitised % denomSanitised;

        Map<Integer, RemainderPosition> wholesRemainderMap = new HashMap<>();

        int decimalRepeatedFrom = -1;
        answerBuilder.append(wholes).append(".");

        int decimalPlaces = 0;
        while (remainder != 0) {
            int newNum = 10 * remainder;
            wholes = newNum / denomSanitised;
            remainder = newNum % denomSanitised;

            boolean wholesRemainderComboAppeared =
                    wholesRemainderMap.containsKey(wholes)
                            && wholesRemainderMap.get(wholes).remainder == remainder;

            if (wholesRemainderComboAppeared) {
                decimalRepeatedFrom = wholesRemainderMap.get(wholes).position;
                break;
            }

            answerBuilder.append(wholes);
            wholesRemainderMap.put(wholes, new RemainderPosition(remainder, decimalPlaces));
            decimalPlaces ++;
        }

        return formatOutputDecimal(answerBuilder.toString(), decimalRepeatedFrom);
    }

    private void validation(int denom) {
        if (denom == 0) throw new RuntimeException("Denominator cannot be 0.");
    }

    private String formatOutputDecimal(String answer, int decimalRepeatedFrom) {
        if (decimalRepeatedFrom == -1) return answer;
        String wholes = answer.split("\\.")[0];
        String decimals = answer.split("\\.")[1];

        String nonRepeatingDecimals = decimals.substring(0,decimalRepeatedFrom);
        String repeatingDecimals = decimals.substring(decimalRepeatedFrom);

        return wholes + "." + nonRepeatingDecimals + "(" + repeatingDecimals + ")";
    }

    // would use record if sdk 14+
    private static class RemainderPosition {
        int remainder;
        int position;
        public RemainderPosition(int remainder, int position) {
            this.remainder = remainder;
            this.position = position;
        }
    }

    @Test
    public void halfTest() {
        assertEquals("0.5", fractionToDecimal(1, 2));
    }

    @Test
    public void nineTenthsTest() {
        assertEquals("0.9", fractionToDecimal(9, 10));
    }

    @Test
    public void multipleDecimalPlacesTest() {
        assertEquals("0.125", fractionToDecimal(1, 8));
    }

    @Test
    public void topHeavyTest() {
        assertEquals("1.375", fractionToDecimal(11, 8));
    }

    @Test
    public void singleCyclicalDecimaltest() {
        assertEquals("0.(3)", fractionToDecimal(1, 3));
    }

    @Test
    public void multipleCyclicalDecimaltest() {
        assertEquals("0.(09)", fractionToDecimal(1, 11));
    }

    @Test
    public void multipleCyclicalDecimaltest2() {
        assertEquals("0.(123)", fractionToDecimal(123, 999));
    }

    @Test
    public void mixedCyclicalDecimaltest() {
        assertEquals("0.1(6)", fractionToDecimal(1, 6));
    }
    @Test
    public void negativeDecimaltest() {
        assertEquals("-0.5", fractionToDecimal(-1, 2));
    }

}
/*
Learnt:
    Doubles are hard to deal with, especically when converting to and from strings. Best keep as a string if possible
    If I want to split a string by a fullstop, the input for split is a regex so \\ is required : number.split("\\.")
    Shouldn't add strings with + in a loop. Should use StringBuilder, then .append
    ^ is the XOR operator
 */
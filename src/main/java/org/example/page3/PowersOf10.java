package org.example.page3;

public class PowersOf10 {

    public static void main(String[] args) {
        System.out.println("The number provided <" + (isPowerOf10(110) ? "IS" : "is NOT") +"> a power of 10.");
    }

    private static boolean isPowerOf10(int N) {
        String stringN = String.valueOf(N);
        stringN = stringN.substring(1);
        return Integer.parseInt(stringN) == 0;
    }

}
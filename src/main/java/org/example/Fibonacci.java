package org.example;

public class Fibonacci {

    public static int fibonacci(int n) {
        int nthMinus2 =0;
        int nthMinus1 =1;
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int nth = 0;
        for (int i = 0; i <= n-1; i++){
            nth = nthMinus1 + nthMinus2;
            nthMinus2 = nthMinus1;
            nthMinus1 = nth;
        }
        return nth;
    }

    public static void main (String[] args) {
        System.out.println("The nth fibonacci number is: " + fibonacci(14));
    }
}
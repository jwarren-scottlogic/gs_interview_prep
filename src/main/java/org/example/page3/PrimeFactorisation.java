package org.example.page3;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorisation {

    public static void main (String[] args) {
        int n = 65;
        System.out.println("The prime factorisation of "+n+", is:");
        int[] factorisation = primeFactorisation(n);
        System.out.print("{");
        for (int e : factorisation) {
            System.out.print(e+", ");
        }
        System.out.print("}");
    }

    private static int[] primeFactorisation(int n) {
        List<Integer> factorisationList = new ArrayList<>();

        while (n != 1) {
            if (n % 2 == 0 ) {
                n = n/2;
                factorisationList.add(2);
            }
            for (int i = 3; i<=n; i += 2) {
                if (n % i == 0 ) {
                    n = n/i;
                    factorisationList.add(i);
                    break;
                }
            }
        }
        return factorisationList.stream().mapToInt(Integer::intValue).toArray();
    }
}

package org.example.page4;

public class ChildClimbingSteps {
    public static void main(String[] args) {
        System.out.println(permutationsToClimbStairs(3));
    }

    private static int permutationsToClimbStairs(int stairs){
        int f0 =1;
        int f1 =2;
        int f2 =4;

        if (stairs==0) {
            return 0;
        } else if (stairs==1){
            return f0;
        } else if (stairs==2){
            return f1;
        } else if (stairs==3) {
            return f2;
        }

        for (int i=0; i< stairs-3; i++) {
            int previousF0 = f0;
            f0=f1;
            f1=f2;
            f2=previousF0+f0+f1;
        }
        return f2;
    }
}

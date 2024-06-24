package org.example.secondRoundInterviews;

import java.util.Objects;

public class RobotMovement {

    public static int[] moveRobot(String instructions) {
        int[] robotPosition = {0,0};
        while (instructions.length() > 0) {
            char instruction = instructions.charAt(0);
            makeOneMovement(robotPosition, instruction); // don't need to assign this to robot position because the array carries the changes
            instructions = instructions.length() == 1 ? "" : instructions.substring(1);
        }
        return robotPosition;
    }

    private static void makeOneMovement(int[] robotPosition, char instruction) {
        if (instruction == 'u' || instruction == 'U') {
            robotPosition[0] ++;
        }else if (instruction == 'd' || instruction == 'D') {
            robotPosition[0] --;
        }else if (instruction == 'r' || instruction == 'R') {
            robotPosition[1] ++;
        }else if (instruction == 'l' || instruction == 'L') {
            robotPosition[1] --;
        } else {
            System.err.printf("Invalid character %s ignored.%n", instruction);
        }
    }


    private static boolean testUp() {
        int[] expected = {3,0};
        int[] actual = moveRobot("UUU");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testUp passed");
            return true;
        }
        System.err.println("testUp failed");
        return false;
    }


    private static boolean testLeft() {
        int[] expected = {0,-2};
        int[] actual = moveRobot("LL");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testLeft passed");
            return true;
        }
        System.err.println("testLeft failed");
        return false;
    }


    private static boolean testRight() {
        int[] expected = {0,2};
        int[] actual = moveRobot("rr");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testRight passed");
            return true;
        }
        System.err.println("testRight failed");
        return false;
    }

    private static boolean testDown() {
        int[] expected = {-4,0};
        int[] actual = moveRobot("dddd");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testDown passed");
            return true;
        }
        System.err.println("testDown failed");
        return false;
    }


    private static boolean testUpDown() {
        int[] expected = {1,0};
        int[] actual = moveRobot("UUD");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testUpDown passed");
            return true;
        }
        System.err.println("testUpDown failed");
        return false;
    }


    private static boolean testMixed() {
        int[] expected = {-1,1};
        int[] actual = moveRobot("UDDLRRLR");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testMixed passed");
            return true;
        }
        System.err.println("testMixed failed");
        return false;
    }

    private static boolean testFalseCharacters() {
        int[] expected = {-1,1};
        int[] actual = moveRobot("1x2UxDv2DL4RcRLR6v");
        //  System.out.println("actual: ["+actual[0] + ","+ actual[1]+"]");
        if (Objects.deepEquals(expected, actual)){
            System.out.println("testFalseCharacters passed");
            return true;
        }
        System.err.println("testFalseCharacters failed");
        return false;
    }

    private static boolean runTests() {
        boolean passed = true;
        passed = passed && testUp();
        passed = passed && testDown();
        passed = passed && testLeft();
        passed = passed && testRight();
        passed = passed && testUpDown();
        passed = passed && testMixed();
        passed = passed && testFalseCharacters();
        return passed;
    }


    public static void main(String[] args) {
        if (runTests()) {
            System.out.println("All tests passed.");
        } else {
            System.err.println("At least one test failed.");
        }
    }
}

/*
Learnt:
    Use Objects.deepEquals to compare arrays outside of test asserts
    To test within coderpad need a test method returning a boolean and then an all tests method which is called by main also returning a boolean
    Need to ensure I read question properly
    System.out.printf is a formatted system out but needs a line break after %n (in this case)
    See ln 11, don't need to reassign an array type because it's a pointer to the location of values
 */
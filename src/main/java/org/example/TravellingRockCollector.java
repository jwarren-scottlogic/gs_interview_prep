package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravellingRockCollector {

    public static int getMaxRocks(List<List<Integer>> map) {

        int numberOfSteps = (map.size()-1) + (map.get(0).size()-1);
        int numberOfRocks = 0;
        boolean transposed = false;
        for (int i=0; i<=numberOfSteps-1; i++) {
            boolean isFinalRowOrColumn = map.get(0).size() == 1 || map.size() == 1;
            if (isFinalRowOrColumn) {
                numberOfRocks += finalRowOrColumnCalculation(map);
                break;
            } else {
                numberOfRocks += map.get(map.size()-1).get(0);
            }
            boolean needToTranspose = map.size()>map.get(0).size();
            if (needToTranspose) {
                transposed = !transposed;
                map = altTransposeList(map);
            }
            removeUnnecessaryPath(map, goNorth(map, transposed), transposed);
        }
        return numberOfRocks;
    }

    private static int finalRowOrColumnCalculation(List<List<Integer>> map) {
        int rocksToAdd =0;
        if (map.size() > 1) {
            String direction = "goNorth";
            System.out.println(direction.repeat(map.size()-1));
        } else {
            String direction = "goEast";
            System.out.println(direction.repeat(map.get(0).size()-1));
        }
        for (List<Integer> row : map) {
            for (int num : row) {
                rocksToAdd += num;
            }
        }
        return rocksToAdd;
    }


    private static void removeUnnecessaryPath(List<List<Integer>> map, boolean goNorth, boolean transposed) {
        if (goNorth) {System.out.println("goNorth"); }
        else { System.out.println("goEast"); }
        boolean removeSouthPath = goNorth ^ transposed; // XOR
        if (removeSouthPath) {
            map.remove(map.size()-1);
            return;
        }
        for (int j = 0; j<map.size() ; j++){
            map.get(j).remove(0);
        }
    }


    public static boolean goNorth(List<List<Integer>> map, boolean transposed) {
        boolean goNorth = !transposed;
        if (map.size() == 1) {
            return goNorth;
        }
        if (map.get(0).size() == 1){
            return goNorth;
        }
        int benefitOfStayingOnCurrentRow = 0;
        //comparing column and row (assuming more columns than rows... if not transpose before)
        for (int i = 1; i < map.size(); i++){
//            int rocksOnBottomRow = map.get(map.size()-1).get(i);
//            int rocksOnColumn = map.get(map.size()-1-i).get(0);
//            benefitOfStayingOnCurrentRow += rocksOnBottomRow - rocksOnColumn;
            int initialRocksOnBottomRow = map.get(map.size()-1).get((i));
            int initialRocksOnColumn = map.get((map.size()-1)-i).get(0);
            int minDifference = initialRocksOnBottomRow - initialRocksOnColumn;
            for (int j = 1; j < i; j++){
                int rocksOnBottomRow = map.get(map.size()-1).get(i);
                int rocksOnColumn = map.get((map.size()-1)-i+j).get(j);
                if(minDifference > rocksOnBottomRow - rocksOnColumn){
                    minDifference = rocksOnBottomRow - rocksOnColumn;
                }
            }
            benefitOfStayingOnCurrentRow += minDifference;
        }

        //comparing rest of row
        for (int i = 0; i<map.get(0).size()-(map.size()); i++){
            int initialRocksOnBottomRow = map.get(map.size()-1).get((i+1)+map.size()-1);
            int initialRocksOnColumn = map.get(0).get(i+1);
            int minDifference = initialRocksOnBottomRow - initialRocksOnColumn;
            for (int j = 1; j < map.size()-1; j++){
                int rocksOnBottomRow = map.get(map.size()-1).get(i+1+map.size()-1);
                int rocksOnColumn = map.get(j).get(j+i+1);
                if(minDifference > rocksOnBottomRow - rocksOnColumn){
                    minDifference = rocksOnBottomRow - rocksOnColumn;
                }
            }
            benefitOfStayingOnCurrentRow += minDifference;
        }
        goNorth = transposed != benefitOfStayingOnCurrentRow < 0;
        return goNorth;
        }

    static <T> List<List<T>> altTransposeList(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (int j = 0; j<table.size(); j++) {
                List<T> row = table.get((table.size()-1)-j);
                col.add(row.get((table.get(0).size()-1)-i));
            }
            ret.add(col);
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<Integer> y0 = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 2));
        ArrayList<Integer> y1 = new ArrayList<>(Arrays.asList(5, 15, 1, 2, 6));
        ArrayList<Integer> y2 = new ArrayList<>(Arrays.asList(2, 3, 2, 2, 15));

        //my solution is quite complex, but it should work with any input, including the above

        List<List<Integer>> map = new ArrayList<>();
        map.add(y0);
        map.add(y1);
        map.add(y2);
        System.out.println("Directions:"+ getMaxRocks(map));
    }
}

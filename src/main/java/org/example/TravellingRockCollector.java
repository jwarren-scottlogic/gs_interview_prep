package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravellingRockCollector {

    public static int getMaxRocks(List<List<Integer>> map) {

        int numberOfSteps = (map.size()-1) + (map.get(0).size()-1);
        int numberOfRocks = map.get(map.size()-1).get(0);
        boolean transposed = false;
        for (int i=0; i<=numberOfSteps-1; i++) {
            if (map.size()>map.get(0).size()) {
                transposed = !transposed;
                map = transposeList(map);
            }
            if (goNorth(map, transposed)) {
                System.out.println("north");
                map.remove(map.size()-1);
            } else {
                System.out.println("east");
                for (int j = 0; j<map.size() ; j++){
                    map.get(j).remove(0);
                }
            }
            numberOfRocks += map.get(map.size()-1).get(0);
//            System.out.println(numberOfRocks);
        }
        return numberOfRocks;
    }

    public static boolean goNorth(List<List<Integer>> map, boolean transposed) {
        System.out.println(map);
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
//            System.out.println("BottomRow");
//            System.out.println("("+(i)+","+(map.size()-1)+")");
//            System.out.println("ColumnRow");
//            System.out.println("("+(0)+","+(map.size()-1-i)+")");
//            System.out.println(map.get(map.size()-1));
            int rocksOnBottomRow = map.get(map.size()-1).get(i);
            int rocksOnColumn = map.get(map.size()-1-i).get(0);
            benefitOfStayingOnCurrentRow += rocksOnBottomRow - rocksOnColumn;
        }
//        System.out.println("/////////////////////");

        //comparing rest of row
        for (int i = 0; i<map.get(0).size()-(map.size()); i++){
//            System.out.println("2BottomRow");
//            System.out.println("("+((i+1)+map.size()-1)+","+(map.size()-1)+")");
//            System.out.println("2ColumnRow");
//            System.out.println("("+(i+1)+","+(0)+")");
            int initialRocksOnBottomRow = map.get(map.size()-1).get((i+1)+map.size()-1);
            int initialRocksOnColumn = map.get(0).get(i+1);
            int minDifference = initialRocksOnBottomRow - initialRocksOnColumn;
            for (int j = 1; j < map.size()-1; j++){
//                    System.out.println("2BottomRow");
//                    System.out.println("("+(i+1+map.size()-1)+","+(map.size()-1)+")");
//                    System.out.println("2ColumnRow");
//                    System.out.println("("+(j+i+1)+","+(j)+")");
                int rocksOnBottomRow = map.get(map.size()-1).get(i+1+map.size()-1);
                int rocksOnColumn = map.get(j).get(j+i+1);
                if(minDifference > rocksOnBottomRow - rocksOnColumn){
                    minDifference = rocksOnBottomRow - rocksOnColumn;
                }
            }
            benefitOfStayingOnCurrentRow += minDifference;
        }
        System.out.println("benefitOfStayingOnCurrentRow: " + benefitOfStayingOnCurrentRow);
        goNorth = transposed != benefitOfStayingOnCurrentRow < 0;
        return goNorth;
        }

    static <T> List<List<T>> transposeList(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<Integer> y2 = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 2));
        ArrayList<Integer> y1 = new ArrayList<>(Arrays.asList(5, 0, 1, 2, 2));
        ArrayList<Integer> y0 = new ArrayList<>(Arrays.asList(2, 3, 1, 1, 16));

        List<List<Integer>> map = new ArrayList<>();
        map.add(y2);
        map.add(y1);
        map.add(y0);
        System.out.println("Directions:"+ getMaxRocks(map));
    }
}

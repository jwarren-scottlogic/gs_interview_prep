package org.example;

public class TravellingRockCollectorMatrix {

    public static int getMaxRocks(int[][] map) throws Exception {

        int numberOfSteps = (map.length-1) + (map[0].length-1);
        int numberOfRocks = map[map.length-1][0];
        boolean transposed = false;
        for (int i=0; i<=numberOfSteps-1; i++) {
            if (map.length>map[0].length) {
                transposed = !transposed;
                map = transposeMatrix(map);
            }
            if (goNorth(map, transposed)) {
                System.out.println("north");
                map = removeLastRow(map);
            } else {
                System.out.println("east");
                map = removeFirstColumn(map);
//                for (int j = 0; j<map.length ; j++){
//                    map[j].remove(0);
//                }
            }
            numberOfRocks += map[map.length-1][0];
//            System.out.println(numberOfRocks);
        }
        return numberOfRocks;
    }

    public static boolean goNorth(int[][] map, boolean transposed) {
        System.out.println(map);
        boolean goNorth = !transposed;
        if (map.length == 1) {
            return goNorth;
        }
        if (map[0].length == 1){
            return goNorth;
        }
        int benefitOfStayingOnCurrentRow = 0;
        //comparing column and row (assuming more columns than rows... if not transpose before)
        for (int i = 1; i < map.length; i++){
            System.out.println("BottomRow");
            System.out.println("("+(i)+","+(map.length-1)+")");
            System.out.println("ColumnRow");
            System.out.println("("+(0)+","+(map.length-1-i)+")");
            System.out.println(map[map.length-1]);
            int rocksOnBottomRow = map[map.length-1][i];
            int rocksOnColumn = map[map.length-1-i][0];
            benefitOfStayingOnCurrentRow += rocksOnBottomRow - rocksOnColumn;
        }
        System.out.println("/////////////////////");

        //comparing rest of row
        for (int i = 0; i<map[0].length-(map.length); i++){
            System.out.println("2BottomRow");
            System.out.println("("+((i+1)+map.length-1)+","+(map.length-1)+")");
            System.out.println("2ColumnRow");
            System.out.println("("+(i+1)+","+(0)+")");
            int initialRocksOnBottomRow = map[map.length-1][(i+1)+map.length-1];
            int initialRocksOnColumn = map[0][i+1];
            int minDifference = initialRocksOnBottomRow - initialRocksOnColumn;
            for (int j = 1; j < map.length-1; j++){
                    System.out.println("2BottomRow");
                    System.out.println("("+(i+1+map.length-1)+","+(map.length-1)+")");
                    System.out.println("2ColumnRow");
                    System.out.println("("+(j+i+1)+","+(j)+")");
                int rocksOnBottomRow = map[map.length-1][i+1+map.length-1];
                int rocksOnColumn = map[j][j+i+1];
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

    public static int[][] transposeMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] transposedMatrix = new int[n][m];

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix[x][y] = matrix[y][x];
            }
        }

        return transposedMatrix;
    }



    public static int[][] removeLastRow(int[][] map) {
        int[][] newMap = new int[map.length - 1][map[0].length];
        for (int i = 1; i < map.length; i++) {
            newMap[i - 1] = map[i];
        }
        return newMap;
    }

    public static int[][] removeFirstColumn(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length-1];
        for (int i = 0; i < map.length; i++) {
            int[] newRow = new int[map[0].length-1];
            for (int j = 0; j < map[0].length; j++) {
                newRow[j+1] = map[i][j];
            }
            newMap[i] = newRow;
        }
        return newMap;
    }

//    public static int[][] removeLastRow(int[][] map) {
//            int row = map.length;
//            int col = map[0].length;
//            int rowRemove = map.length;
//            int[][] newArray = new int[row][col - 1]; // You will have one column less
//
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    if (j != colRemove) {
//                        newArray[i][j > colRemove ? j -1 : j] = map[i][j]; // If you're looking at an index greater than the one to remove, you have to reduce index by one
//                    }
//                }
//            }
//            return newArray;
//        }
//
//    private static int[][] removeFirstColumn(int[][] map) {
//        int row = map.length;
//        int col = map[0].length;
//        int rowRemove = map.length-1;
//        int[][] newArray = new int[row][col - 1]; // You will have one column less
//
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (i != rowRemove) {
//                    newArray[i][j > rowRemove ? j -1 : j] = map[i][j]; // If you're looking at an index greater than the one to remove, you have to reduce index by one
//                }
//            }
//        }
//        return newArray;
//    }

    public static void main(String[] args) throws Exception {
        int[] y2= {1, 0, 0, 0, 2};
        int[] y1= {5, 0, 1, 2, 2};
        int[] y0= {2, 3, 1, 1, 1};

        int[][] map = {y2,y1,y0};
        System.out.println("Directions:"+ getMaxRocks(map));
    }
}

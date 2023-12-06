package org.example.page3.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearchTree {
    public static void main(String[] args) {
        Node firstNode = new Node(100);
        int[] array = {5,10,35,50,199, 200,250};
        int [] shuffledArray = shuffleArray(array);
        BST bst = buildBST(firstNode, shuffledArray);
        System.out.println(bst);
    }

    private static int[] shuffleArray(int[] array){
        List<Integer> list = new ArrayList<>();
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        Collections.shuffle(list);
        for (int i=0; i< array.length;i++){
            newArray[i] = list.get(i);
        }
        return newArray;
    }

    private static BST buildBST(Node firstNode, int[] array) {
        BST bst = new BST(firstNode);
        for (int arrayInt : array) {
            bst.addNode(arrayInt);
        }
        return bst;
    }
}


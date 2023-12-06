package org.example.page3.BinarySearchTree;

import java.util.Optional;

public class Node {
    int value;
    Optional<Node> lowerNode;
    Optional<Node> higherNode;


    public Node(int nodeValue) {
        value = nodeValue;
        lowerNode = Optional.empty();
        higherNode = Optional.empty();
    }

    public void setLowerNode(Node lower) {
        lowerNode = Optional.of(lower);
    }

    public void setHigherNode(Node higher) {
        higherNode = Optional.of(higher);
    }
}

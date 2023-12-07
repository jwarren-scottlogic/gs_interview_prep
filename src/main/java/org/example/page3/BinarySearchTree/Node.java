package org.example.page3.BinarySearchTree;

import java.util.Optional;

public class Node {
    int value;
    Optional<Node> lowerNode;
    Optional<Node> higherNode;


    public Node(int value) {
       this.value = value;
       this.lowerNode = Optional.empty();
       this.higherNode = Optional.empty();
    }

    public void setLowerNode(Node lowerNode) {
        this.lowerNode = Optional.of(lowerNode);
    }

    public void setHigherNode(Node higherNode) {
        this.higherNode = Optional.of(higherNode);
    }
}

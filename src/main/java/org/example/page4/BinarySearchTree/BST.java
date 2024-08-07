package org.example.page4.BinarySearchTree;

import java.util.HashSet;
import java.util.Set;

public class BST {
    Node firstNode;
    Set<Node> nodes;

    public BST(Node firstNode) {
        this.firstNode = firstNode;
        this.nodes = new HashSet<>();
        this.nodes.add(this.firstNode);
    }

    public void addNode(int integer) {
        Node node = new Node(integer);
        positionNewNode(node);
        this.nodes.add(node);
    }

    private void positionNewNode(Node newNode) {
        positionNewNodeRecursively(newNode, firstNode);
    }

    private void positionNewNodeRecursively(Node newNode, Node currentNode) {
        if (newNode.value < currentNode.value) {
            if (currentNode.lowerNode.isPresent()) {
                positionNewNodeRecursively(newNode, currentNode.lowerNode.get());
            } else {
                currentNode.setLowerNode(newNode);
            }
        } else {
            if (currentNode.higherNode.isPresent()) {
                positionNewNodeRecursively(newNode, currentNode.higherNode.get());
            } else {
                currentNode.setHigherNode(newNode);
            }
        }
    }
}

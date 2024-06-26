package org.example.secondRoundInterviews;

import java.util.*;
import java.util.stream.Collectors;


import org.junit.*;

public class Underground {

    static class Node {
        private final int name;
        private Set<Node> connectedNodes;
        public Node(int name) { this.name = name; }
        public int getName() { return name; }
        public Set<Node> getConnectedNodes() { return connectedNodes; }
        public void setConnectedNodes (List<Node> nodes) { connectedNodes = new HashSet<>(nodes); }
    }

    public List<Integer> getShortestPath(Set<Node> nodeList, int start, int end) {
        List<List<Integer>> pathsTaken = new ArrayList<>();
        Node startNode = getNode(nodeList, start);
        List<Node> startPath = new ArrayList<>(List.of(startNode));
        List<Node> nextNodes = nodesCanTake(startPath, pathsTaken);
        List<Integer> shortestPath = null;
        for (Node nextNode : nextNodes) {
            List<Node> currentPath = new ArrayList<>(startPath);
            currentPath.add(nextNode);
            List<Integer> shortestPathForNextNode = iterateForShortestPath(end, pathsTaken, currentPath);
            if (shortestPathForNextNode != null) {
                System.out.println(shortestPathForNextNode);
                boolean shouldUpdateShortestPath = shortestPath == null || shortestPath.size() > shortestPathForNextNode.size();
                if (shouldUpdateShortestPath) {
                    shortestPath = shortestPathForNextNode;
                    System.out.println("New shortest path is: ");
                    System.out.println(shortestPath);
                } else {
                    System.out.println("Did not update the shortest path because the path is longer than the previous shortest path.");
                }
            } else {
                System.out.println("shortestPath for one direction is null. Double check that one starting direction only goes to a dead end -> otherwise error. ");
            }
        }
        return shortestPath;
    }

    private List<Integer> iterateForShortestPath(int end, List<List<Integer>> pathsTaken, List<Node> inputCurrentPath) {
        printListOfNodes(inputCurrentPath);
        List<Integer> shortestPath = pathsTaken.stream().filter(p -> p.get(p.size()-1) == end)
                .min(Comparator.comparingInt(List::size)).orElse(null);
        List<Node> nextNodes = nodesCanTake(inputCurrentPath, pathsTaken);
        List<Node> currentPath = new ArrayList<>(inputCurrentPath);
        Optional<Node> endNode = nextNodes.stream()
                .filter(n -> n.getName() == end)
                .findFirst();

        boolean reachedEnd = endNode.isPresent();
        if (reachedEnd) {
            if (shortestPath == null || currentPath.size() + 1 < shortestPath.size()) {
                currentPath.add(endNode.get());
                List<Integer> currentPathInts = nodeToIntList(currentPath);
                pathsTaken.add(currentPathInts);
                return currentPathInts;
            }
            return shortestPath;
        }

        boolean deadEnd = nextNodes.isEmpty();
        if (deadEnd) {
            pathsTaken.add(nodeToIntList(currentPath));
            return shortestPath;
        }

        for (Node nextNode : nextNodes) {
            currentPath = new ArrayList<>(inputCurrentPath);
            currentPath.add(nextNode);

            boolean pathLongerThanShortestPath = shortestPath != null && currentPath.size() >= shortestPath.size();
            if (pathLongerThanShortestPath) continue;
            List<Integer> shortestPathForNextNode = iterateForShortestPath(end, pathsTaken, currentPath);

            boolean completedJourney = shortestPathForNextNode != null
                    && shortestPathForNextNode.get(shortestPathForNextNode.size()-1) == end;
            boolean shouldUpdateShortestPath = shortestPath == null || shortestPath.size() > shortestPathForNextNode.size();
            if (completedJourney && shouldUpdateShortestPath) {
                shortestPath = shortestPathForNextNode;
            }
        }
        return shortestPath;
    }

    private List<Integer> nodeToIntList(List<Node> nodes) {
        return nodes.stream().mapToInt(Node::getName).boxed().collect(Collectors.toList());
    }

    private List<Node> nodesCanTake(List<Node> currentPath, List<List<Integer>> pathsTaken) {
        Set<Integer> nodesCannotTake = new HashSet<>();
        int currentNodeIndex = currentPath.size() - 1;
        Node currentNode = currentPath.get(currentNodeIndex);
        if (currentPath.size() > 1) {
            Node previousNode = currentPath.get(currentPath.size() - 2);
            nodesCannotTake.add(previousNode.name);
        }
         pathsTaken.forEach(p -> {
             try{
                 int indexOfPreviousNodeInOtherPath = p.indexOf(currentNode.name);
                 boolean nodePassedEarlierInOtherPath = indexOfPreviousNodeInOtherPath <= currentNodeIndex;
                 if (nodePassedEarlierInOtherPath) {
                     int nextNodeAlreadyTaken = p.get(indexOfPreviousNodeInOtherPath + 1);
                     nodesCannotTake.add(nextNodeAlreadyTaken);
                 }
             } catch (NullPointerException e) {
                 //do nothing
             }
         });

        return currentNode.getConnectedNodes().stream()
                .filter(n -> !currentPath.contains(n))
                .filter(n -> !nodesCannotTake.contains(n.getName()))
                .collect(Collectors.toList());
    }

    private Node getNode(Set<Node> nodeList, int nodeName) {
        return nodeList.stream()
                .filter(n -> n.getName() == nodeName)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Start node does not exist"));
    }
    private void printListOfNodes(List<Node> nodes) {
        System.out.println("List of nodes: ");
        for (Node node: nodes) {
            System.out.print(node.getName() + ", ");
        }
        System.out.println();
    }

    @Test
    public void getShortestPathTest1To5() {
        List<Integer> expected = List.of(1,5,9);
        List<Integer> actual = getShortestPath(testMap, 1,9);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getShortestPathTest4To10() {
        List<Integer> expected = List.of(4,8,12,11,10);
        List<Integer> actual = getShortestPath(testMap, 4,10);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void nodesCanTake_noPrevious_singleOption_InteriorTest() {
        List<Node> expected = List.of(node3);
        List<Node> currentPath = List.of(node2);
        List<Integer> path1 = List.of(2,1);
        List<Integer> path6 = List.of(2,6);
        List<List<Integer>> pathsTaken = new ArrayList<>(List.of(path1,path6));

        List<Node> actual = nodesCanTake(currentPath, pathsTaken);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void nodesCanTake_noPrevious_multipleOptions_InteriorTest() {
        List<Node> expected = List.of(node3, node6);
        List<Node> currentPath = List.of(node2);
        List<Integer> path1 = List.of(2,1);
        List<List<Integer>> pathsTaken = new ArrayList<>(List.of(path1));

        List<Node> actual = nodesCanTake(currentPath, pathsTaken);
        Assert.assertTrue(expected.contains(actual.get(0)));
        Assert.assertTrue(expected.contains(actual.get(1)));
    }
    @Test
    public void nodesCanTake_withPrevious_multipleOptions_InteriorTest() {
        List<Node> expected = List.of(node3);
        List<Node> currentPath = List.of(node6, node2);
        List<Integer> path1 = List.of(2,1);
        List<List<Integer>> pathsTaken = new ArrayList<>(List.of(path1));

        List<Node> actual = nodesCanTake(currentPath, pathsTaken);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void nodesCanTake_noOptions_InteriorTest() {
        List<Node> expected = List.of();
        List<Node> currentPath = List.of(node6, node2);
        List<Integer> path1 = List.of(2,1);
        List<Integer> path3 = List.of(2,3);
        List<List<Integer>> pathsTaken = new ArrayList<>(List.of(path1,path3));

        List<Node> actual = nodesCanTake(currentPath, pathsTaken);
        Assert.assertEquals(expected,actual);
    }

    @Before
    public void setup() {
        Collections.addAll(testMap,node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,node11,node12);
        node1.setConnectedNodes(List.of(node2,node5));
        node2.setConnectedNodes(List.of(node1,node3,node6));
        node3.setConnectedNodes(List.of(node2,node4));
        node4.setConnectedNodes(List.of(node3,node8));
        node5.setConnectedNodes(List.of(node1,node9));
        node6.setConnectedNodes(List.of(node2,node7));
        node7.setConnectedNodes(List.of(node6, node8));       // with loop
        node8.setConnectedNodes(List.of(node4,node7,node12)); // with loop
//        node7.setConnectedNodes(List.of(node6));                // no loop
//        node8.setConnectedNodes(List.of(node4,node12));         // no loop
        node9.setConnectedNodes(List.of(node5,node10));
        node10.setConnectedNodes(List.of(node9,node11));
        node11.setConnectedNodes(List.of(node10,node12));
        node12.setConnectedNodes(List.of(node11,node8));
    }

    private Set<Node> testMap = new HashSet<>();
    private Node node1 = new Node(1);
    private Node node2 = new Node(2);
    private Node node3 = new Node(3);
    private Node node4 = new Node(4);
    private Node node5 = new Node(5);
    private Node node6 = new Node(6);
    private Node node7 = new Node(7);
    private Node node8 = new Node(8);
    private Node node9 = new Node(9);
    private Node node10 = new Node(10);
    private Node node11 = new Node(11);
    private Node node12 = new Node(12);

//    public static void main(String[] args) {
////        JUnitCore.main("Underground");
//    }
}

/*
Learnt:
    Construct a set from a list: = new HashSet<>(aList);
    Be careful when making to lists equal. If you don't want one changing the other, must make new ArrayList: List<TYPE> aList = new ArrayList<>(originalList);
    When some interior functions are very complex, worth writing little tests for them to see they are working.
    To find an object by min of a method on a field (e.g. : .stream().min(Comparator.comparingInt(fieldType::methodOnField))
    orElseThrow requires a lambda: .orElseThrow(() -> new RuntimeException(
    Add helpful logging as you go through, if stuck try and write out what you expect to happen and write logs that would match this
    import org.junit.*; for tests
 */
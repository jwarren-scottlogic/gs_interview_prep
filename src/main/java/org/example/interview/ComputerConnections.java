package org.example.interview;


/*There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi]represents a connection between computers ai and bi. Any
computer can reach any other computer directly or indirectly through
the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers
connected. If it is not possible, return -1.

Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
*/

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ComputerConnections {

    public int changeInConnections(int n, List<Connection> wires) {
        int mainComputer = getMainComputer(wires);
        List<Integer> computerUnconnected = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!areTwoComputerConnected(wires, mainComputer, i)) {
                computerUnconnected.add(i);
            }
        }

        List<Connection> unnecessaryWires = unnecessaryWires(wires);

        int neededWires = computerUnconnected.size();

        for (int i=0; i< computerUnconnected.size(); i++) {
            for (int j=i+1; j< computerUnconnected.size(); j++){
                boolean disconnectedComputersConnectedToEachother
                        = areTwoComputerConnected(wires, computerUnconnected.get(i), computerUnconnected.get(j));
                if (disconnectedComputersConnectedToEachother) neededWires--;
            }
        }

        if (unnecessaryWires.size() >= neededWires) return neededWires;
        return -1;
    }

    private int getMainComputer(List<Connection> wires) {
        // Find the computer that appears the most in the wires
        return wires.stream()
                // get all the computers mentioned
                .filter(w -> w.pointA != w.pointB)
                .map(w -> List.of(w.pointA, w.pointB))
                .flatMap(List::stream)
                //find the mode of the computers
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No main computer exists"))
                .getKey();
    }

    //which wires are unnecessary
// loop over wires, remove that wire, if two points are still connected then this wire is unnecessary
    private List<Connection> unnecessaryWires(List<Connection> wires) {
        List<Connection> unnecessaryWires = new ArrayList<>();
        List<Connection> wiresWithoutCurrentWire = new ArrayList<>(wires);
        for (Connection wire : wires) {
            wiresWithoutCurrentWire.remove(wire);

            if (areTwoComputerConnected(wiresWithoutCurrentWire, wire.pointA, wire.pointB)) unnecessaryWires.add(wire);
        }
        return unnecessaryWires;
    }

    private boolean areTwoComputerConnected(List<Connection> wires, int computer1, int computer2) {
        return areTwoComputerConnected(wires, computer1, computer2, new ArrayList<>());
    }

    private boolean areTwoComputerConnected(List<Connection> wires, int computer1, int computer2, List<Integer> computersAlreadyTried) {

        if (computer1 == computer2) return true;
        boolean connectedDirectly = wires.stream()
                .anyMatch(w -> (w.pointA == computer1 && w.pointB == computer2)
                        || (w.pointA == computer2 && w.pointB == computer1));

        if (connectedDirectly) return true;
        List<Integer> computersDirectlyConnectedToComputer1 = wires.stream()
                .filter(w -> (w.pointA == computer1) || w.pointB == computer1)
                .map(w -> (w.pointA == computer1) ? w.pointB : w.pointA)
                .filter(c -> !computersAlreadyTried.contains(c))
                .collect(Collectors.toList());

        computersAlreadyTried.add(computer1);
        for (Integer computer1b : computersDirectlyConnectedToComputer1) {
            boolean indirectlyConnected = areTwoComputerConnected(wires, computer1b, computer2, computersAlreadyTried);
            if (indirectlyConnected) return true;
            computersAlreadyTried.add(computer1b);
        }
        return false;
    }

    class Connection {
        int pointA;
        int pointB;

        Connection(int a, int b) {
            this.pointA = a;
            this.pointB = b;
        }
    }

    @Nested
    public class changeInConnectionsTest {

        @Test
        public void exampleTest1() {
            int n = 4;
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(0, 2), new Connection(1, 2));
            int actual = changeInConnections(n, connections);
            int expected = 1;
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void exampleTest2() {
            int n = 6;
            List<Connection> connections = List.of(
                    new Connection(0, 1),
                    new Connection(0, 2),
                    new Connection(0, 3),
                    new Connection(1, 2),
                    new Connection(1, 3));
            int actual = changeInConnections(n, connections);
            int expected = 2;
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void myTest() {
            int n = 6;
            List<Connection> connections = List.of(
                    new Connection(1, 2),
                    new Connection(0, 2),
                    new Connection(0, 3),
                    new Connection(1, 2),
                    new Connection(4, 5));
            int actual = changeInConnections(n, connections);
            int expected = 1;
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void myTest2() {
            int n = 6;
            List<Connection> connections = List.of(
                    new Connection(4, 5),
                    new Connection(0, 2),
                    new Connection(0, 3),
                    new Connection(1, 2),
                    new Connection(1, 0));
            int actual = changeInConnections(n, connections);
            int expected = 1;
            Assert.assertEquals(expected, actual);
        }
        @Test
        public void myTest3() {
            int n = 5;
            List<Connection> connections = List.of(
                    new Connection(0, 1),
                    new Connection(0, 2),
                    new Connection(3, 4),
                    new Connection(1, 2));
            int actual = changeInConnections(n, connections);
            int expected = 1;
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void exampleTestFailure() {
            int n = 6;
            List<Connection> connections = List.of(
                    new Connection(0, 1),
                    new Connection(0, 2),
                    new Connection(0, 3),
                    new Connection(1, 2));
            int actual = changeInConnections(n, connections);
            int expected = -1;
            Assert.assertEquals(expected, actual);
        }
    }

    @Nested
    public class twoComputersConnectedTest {
        @Test
        public void connectedEqualTest() {
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(0, 2), new Connection(1, 2));
            boolean actual = areTwoComputerConnected(connections, 0, 0);
            Assert.assertTrue(actual);
        }

        @Test
        public void connectedDirectlyTest() {
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(0, 2), new Connection(1, 2));
            boolean actual = areTwoComputerConnected(connections, 0, 1);
            Assert.assertTrue(actual);
        }

        @Test
        public void connectedindirectlyTest() {
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(0, 2));
            boolean actual = areTwoComputerConnected(connections, 1, 1);
            Assert.assertTrue(actual);
        }

        @Test
        public void notConnectedTest1() {
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(0, 2), new Connection(1, 2));
            boolean actual = areTwoComputerConnected(connections, 0, 3);
            Assert.assertFalse(actual);
        }

        @Test
        public void notConnectedTest2() {
            List<Connection> connections = List.of(new Connection(0, 1), new Connection(2, 3));
            boolean actual = areTwoComputerConnected(connections, 0, 3);
            Assert.assertFalse(actual);
        }
    }
}

/*
    This is a refined version with more tests of the answer I gave in my interview. It's highly inefficient so could do with reducing the number of nested loops
    My interviewer suggested at the end that I change all the input wirings that I can to point to the main computer. So [0,1],[0,2],[2,1] becomes [0,1],[0,2],[0,1]
            then obviously all the duplicates can be changed
 */

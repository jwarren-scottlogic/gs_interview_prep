package org.example.interview;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputerConnections2 {


    public int changeInConnections(int n, List<Connection> wires) {
        if (wires.size()<n-1) return -1;

        List<Connection> wiresToProcess = new ArrayList<>(wires);

        Set<Set<Integer>> connectedGroups = new HashSet<>();
        while (!wiresToProcess.isEmpty()) {
            Set<Integer> connectedGroup = new HashSet<>();
            Connection wire = wiresToProcess.get(0);
            connectedGroup.add(wire.pointA);
            connectedGroup.add(wire.pointB);
            wiresToProcess.remove(wire);

            // Get all computers in one group
            Set<Integer> newAdditions = new HashSet<>(connectedGroup);
            while (!newAdditions.isEmpty()) {
                Set<Integer> computersToAdd = wiresToProcess.stream()
                        .map(w -> computerConnectedToGroup(w, connectedGroup))
                        .flatMap(Optional::stream)
                        .collect(Collectors.toSet());
                connectedGroup.addAll(computersToAdd);
                newAdditions =  new HashSet<>(computersToAdd);
            }
            connectedGroups.add(connectedGroup);
            wiresToProcess = wiresToProcess.stream()
                    .filter(w -> !(connectedGroup.contains(w.pointA) || connectedGroup.contains(w.pointB)))
                    .collect(Collectors.toList());
        }

        int changesToConnectGroups = connectedGroups.size()-1;
        int disconnectedComputers = 0;
        for (int i=0; i<n; i++) {
            final int finalI = i;
            boolean computerIConnected = wires.stream().anyMatch(w -> (w.pointA == finalI) || (w.pointB == finalI));
            if (!computerIConnected) disconnectedComputers++;
        }
        return changesToConnectGroups+ disconnectedComputers;
    }


    private Optional<Integer> computerConnectedToGroup(Connection wire, Set<Integer> group) {
        if(group.contains(wire.pointA) && group.contains(wire.pointB)) return Optional.empty();
        if (group.contains(wire.pointA)) return Optional.of(wire.pointB);
        if (group.contains(wire.pointB)) return Optional.of(wire.pointA);
        return Optional.empty();
    }


    static class Connection {
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

}
/*
Learnt:
    Dealing with optionals in a list: instead of: stream().filter(Optional::isPresent).map(Optional::get), we can flatMap: stream().flatMap(Optional::stream).



 */
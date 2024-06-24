package org.example.secondRoundInterviews;



import org.junit.*;
import org.testng.internal.collections.Pair;
//import org.apache.commons.lang3.tuple.ImmutablePair; // for coderpad
//import org.apache.commons.lang3.tuple.Pair;


import java.util.*;
        import java.util.stream.Collectors;

        import static org.junit.Assert.*;

public class JoiningPathSegments {
        @Test
    public void testScores() {
        List<Pair<Integer, Integer>> inputSegments = List.of(
                new Pair<>(5, 6),
                new Pair<>(2, 3),
                new Pair<>(1, 5),
                new Pair<>(3, 0),
                new Pair<>(6, 2)
        );
        List<Pair<Integer, Integer>> expectedSegments = List.of(
                new Pair<>(1, 5), // has to be ImmutablePair<Integer,Integer>(1, 5), for coderpad (getLeft and getRight)
                new Pair<>(5, 6),
                new Pair<>(6, 2),
                new Pair<>(2, 3),
                new Pair<>(3, 0)
        );
        List<Pair<Integer, Integer>> actualSegments = joinPathSegments(inputSegments);
        assertEquals(expectedSegments, actualSegments);
    }

    public List<Pair<Integer, Integer>> joinPathSegments(List<Pair<Integer, Integer>> inputSegments) {
        Map<Integer, Pair<Integer, Integer>> segmentMap = inputSegments.stream().collect(Collectors.toMap(p -> p.first(), p->p));
        List<Pair<Integer, Integer>> output = new ArrayList<>();
        Pair<Integer, Integer> nextSegment = inputSegments.get(0);

        //orders all segments to right of first entry
        while (nextSegment != null) {
            Pair<Integer, Integer> segment = nextSegment;
            output.add(segment);
            segmentMap.remove(segment.first());
            nextSegment = segmentMap.get(segment.second());
        }
        if (segmentMap.isEmpty()) return output;

        //orders all segments to left of original first entry
        segmentMap = segmentMap.entrySet().stream().collect(Collectors.toMap(e->e.getValue().second(),e->e.getValue()));
        nextSegment = segmentMap.get(output.get(0).first());

        while (nextSegment != null && !segmentMap.isEmpty()) {
            Pair<Integer, Integer> segment = nextSegment;
            printSegment(segment);
            output.add(0, segment);
            segmentMap.remove(segment.second());
            nextSegment = segmentMap.get(segment.first());
        }

        if (!segmentMap.isEmpty()) throw new RuntimeException("Some values were could not be added");

        return output;
    }

    private void printSegmentMap(Map<Integer, Pair<Integer, Integer>> segmentMap) {
        System.out.print("segmentMap: ");
        for (var segmentEntry : segmentMap.entrySet()) {
            System.out.print(segmentEntry.getKey()+"|");printSegment(segmentEntry.getValue());
        }
    };

    private void printListOfSegments(List<Pair<Integer, Integer>> segments) {
        System.out.print("segment list: ");
        for (var segment : segments) {
            printSegment(segment);
        }
    };
    private void printSegment(Pair<Integer, Integer> segment) {
        System.out.println(segment.first()+":"+segment.second());
    }

//    public static void main(String[] args) {
//        JUnitCore.main("JoiningPathSegments");
//    }
}

/*
Learnt:
    If something can't be instantiated (Pairs on coderpad), might be because type is abstract
    Using maps are helpful in avoiding large big O
    stream to a map : stream().collect(Collectors.toMap(p -> p.first(), p -> p)); <- check if there is a better way
    Making little functions for logging complex types can be useful and neater (in coderpad with no debugger)
 */
package org.example.page6;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KidsInACircle {
//    Write a method taking (n, k) where you have k kids in a circle and a phrase of length n words (Eeny, meeny, miny, moe).
//    Count round your kids, remove the one you land on from the list, and continue. Output the kid that will be left.

    public String whichKidLeft(int n, int k) { // n = phrase length, // k = number of kids
        validation(n, k);

        List<String> remainingKids = makeKidArray(k);
        while (remainingKids.size() > 1) {
            int numberOfRemainingKids = remainingKids.size();
            int kidToRemove = (n % numberOfRemainingKids);
            if (kidToRemove == 0) kidToRemove += numberOfRemainingKids;
            remainingKids.remove(kidToRemove - 1);
        }
        return remainingKids.get(0);
    }

    private List<String> makeKidArray(int k) {
        List<String> kids = new ArrayList<>();
        for (int i = 1; i <= k; i++) kids.add("Kid " + i);
        return kids;
    }

    private void validation(int n, int k) {
        if (n < 1) throw new RuntimeException("Phrase length has to be at least 1");
        if (k < 1) throw new RuntimeException("There has to be at least 1 kid!");
    }

    @Test
    public void basicTest() {
        assertEquals("Kid 2", whichKidLeft(4, 4));
    }

    @Test
    public void singleKidTest() {
        assertEquals("Kid 1", whichKidLeft(4, 1));
    }

    @Test
    public void singleWordPhraseTest() {
        assertEquals("Kid 4", whichKidLeft(1, 4));
    }
}

/*
Learnt:
    Addidtion assigment is plus first: +=         ... (not =+)
    instead of:             int kidToRemove = (n % numberOfRemainingKids);
                            if (kidToRemove == 0) kidToRemove += numberOfRemainingKids;
                            remainingKids.remove(kidToRemove - 1);
              I could have used an index:
                            int kidIndexToRemove = (n-1 % numberOfRemainingKids);
                            remainingKids.remove(kidIndexToRemove);
 */
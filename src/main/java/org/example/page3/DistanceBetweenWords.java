package org.example.page3;

import org.junit.Test;
import java.util.Arrays;

import static java.util.Arrays.stream;
import static org.testng.AssertJUnit.assertEquals;

public class DistanceBetweenWords {

    /**
     * Write a method that determines the shortest distance between the middle of two words in a document.
     * <p>
     * E.g. in the document "on his birthday" the distance between "his" and "birthday" is 6.5
     *
     * @param wordA    the first word
     * @param wordB    the second word
     * @param document a document, containing many words
     * @return the distance between wordA and wordB in the document
     */
    private double findDistanceBetween(String wordA, String wordB, String document) {
        String[] words = document.split(" ");
        words = sanitize(words);
        double shortest = document.length() + 1;
        double positionA = 0;
        double positionB = 0;
        int index = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(wordA)) {
                positionA = index + ((double) wordA.length() / 2);
            }

            if (word.equalsIgnoreCase(wordB)) {
                positionB = index + ((double) wordB.length() / 2);
            }

            if (positionA > 0 && positionB > 0) {
                double dist = Math.abs(positionA - positionB);
                shortest = Math.min(shortest, dist);
            }

            boolean addSpace = !wordEndsWithPunctuation(word);
            index += word.length();
            if (addSpace) index += 1;
        }

        if (shortest == document.length() + 1) {
            return -1;
        }

        return shortest;
    }

    private String[] sanitize(String[] words) {
        return stream(words).map(word -> {
                    if (wordEndsWithPunctuation(word)) {
                        return new String[]{word.substring(0, word.length() - 1),
                                word.substring(word.length()-1)};
                    }
                    return new String[]{word};
                }).flatMap(Arrays::stream).toArray(String[]::new);
    }

    private boolean wordEndsWithPunctuation(String word) {
        return (word.endsWith(",")
                || word.endsWith(".")
                || word.endsWith("?")
                || word.endsWith("!"));
    }

    @Test
    public void givenExample_returnAnswer() {
        assertEquals(6.5, findDistanceBetween("his", "birthday", DOCUMENT), 0.1);
    }

    @Test
    public void givenEvenLengthWords_returnAnswer() {
        assertEquals(6.0, findDistanceBetween("Bucket", "ever", DOCUMENT), 0.1);
    }

    @Test
    public void givenOddAndEvenLengthWords_returnCorrectDistance() {
        assertEquals(8.5, findDistanceBetween("special", "occasion", DOCUMENT), 0.1);
    }

    @Test
    public void givenDifferentCase_returnCorrectDistance() {
        assertEquals(14.0, findDistanceBetween("factory", "imagine", DOCUMENT), 0.1);
    }

    @Test
    public void givenWordsWithPunctuationBetweenThem_returnCorrectDistance() {
        assertEquals(8.0, findDistanceBetween("nibble", "just", DOCUMENT), 0.1);
    }

    @Test
    public void givenWordsOutOfOrder_returnCorrectDistance() {
        assertEquals(5.0, findDistanceBetween("the", "great", DOCUMENT), 0.1);
    }

    private static final String DOCUMENT = "Only once a year, on his birthday, did Charlie Bucket ever get to taste a" +
            " bit of chocolate. The whole family saved up their money for that special occasion, and when the great " +
            "day arrived, Charlie was always presented with one small chocolate bar to eat all by himself. And each " +
            "time he received it, on those marvellous birthday mornings, he would place it carefully in a small " +
            "wooden box that he owned, and treasure it as though it were a bar of solid gold; and for the next few " +
            "days, he would allow himself only to look at it, but never to touch it. Then at last, when he could " +
            "stand it no longer, he would peel back a tiny bit of the paper wrapping at one corner to expose a tiny " +
            "bit of chocolate, and then he would take a tiny nibble – just enough to allow the lovely sweet taste to " +
            "spread out slowly over his tongue. The next day, he would take another tiny nibble, and so on, and so " +
            "on. And in this way, Charlie would make his sixpenny bar of birthday chocolate last him for more than a " +
            "month. " +
            "But I haven’t yet told you about the one awful thing that tortured little Charlie, the lover of " +
            "chocolate, more than anything else. This thing, for him, was far, far worse than seeing slabs of " +
            "chocolate in the shop windows or watching other children munching bars of creamy chocolate right in " +
            "front of him. It was the most terrible torturing thing you could imagine, and it was this: " +
            "In the town itself, actually within sight of the house in which Charlie lived, there was an ENORMOUS " +
            "CHOCOLATE FACTORY! " +
            "Just imagine that! " +
            "And it wasn’t simply an ordinary enormous chocolate factory, either. It was the largest and most famous " +
            "in the whole world! It was WONKA’S FACTORY, owned by a man called Mr Willy Wonka, the greatest inventor " +
            "and maker of chocolates that there has ever been. And what a tremendous, marvellous place it was! It had" +
            " huge iron gates leading into it, and a high wall surrounding it, and smoke belching from its chimneys, " +
            "and strange whizzing sounds coming from deep inside it. And outside the walls, for half a mile around in" +
            " every direction, the air was scented with the heavy rich smell of melting chocolate! " +
            "Twice a day, on his way to and from school, little Charlie Bucket had to walk right past the gates of " +
            "the factory. And every time he went by, he would begin to walk very, very slowly, and he would hold his " +
            "nose high in the air and take long deep sniffs of the gorgeous chocolatey smell all around him. " +
            "Oh, how he loved that smell! " +
            "And oh, how he wished he could go inside the factory and see what it was like! ";
}



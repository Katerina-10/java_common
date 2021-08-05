package collections;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card implements Comparable<Card> {
    public enum Suit {Spades, Hearts, Clubs, Dimond};
    public enum Face {Ace, Douce, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King};

    private final Suit suit;
    private final Face face;

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        if (suit != card.suit) return false;
        return face == card.face;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, face);
    }

    @Override
    public int compareTo(Card anotheCard) {
        Face[] values = Face.values();
        List<Face> faces = Arrays.asList(values);
        if (faces.indexOf(this.face) < faces.indexOf(anotheCard.face)) {
            return -1;
        } else if (faces.indexOf(this.face) > faces.indexOf(anotheCard.face)) {
            return 1;
        } else if (faces.indexOf(this.face) == faces.indexOf(anotheCard.face)) {
            return String.valueOf(suit).compareTo(String.valueOf(anotheCard.suit));
        }
        return 0;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }
}

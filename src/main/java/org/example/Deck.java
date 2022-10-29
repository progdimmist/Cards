package org.example;

import org.example.cards.Card;
import org.example.cards.Rank;
import org.example.cards.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> cards = new ArrayList<>();

    public Deck() {
        init();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Card getNextCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public static void addInDeck(Card card) {
        cards.add(card);
    }

    public void showCardsInDeck() {
        System.out.print("\nCards: ");
        for (Card i : cards) {
            System.out.print(i.getNameOfCard() + "; ");
        }
    }

    public int getSizeOfDeck() {
        return cards.size();
    }

}
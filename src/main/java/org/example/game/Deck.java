package org.example.game;

import org.example.cards.Card;
import org.example.cards.Rank;
import org.example.cards.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    static final List<Card> cards = new ArrayList<>();

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
}

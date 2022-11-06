package org.example.game;

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

    public Card getCard(int i) {
        return cards.get(i);
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

    public void showCardsInDeckPlayer(int firstIndexPlayer, List<Player> players) {
        System.out.print("\nDiscarded cards: ");

            for (int i = 0; i < cards.size(); i++) {
                if (i != cards.size() - 1) System.out.print((firstIndexPlayer + 1) + " player threw «" +cards.get(i).getNameOfCard()+ "»; ");
                else System.out.print((firstIndexPlayer + 1) + " player threw «" + cards.get(i).getNameOfCard() + "» ");


            firstIndexPlayer++;
            if (firstIndexPlayer == players.size()) {
                firstIndexPlayer = 0;
            }
        }
    }

    public int getSizeOfDeck() {
        return cards.size();
    }

}
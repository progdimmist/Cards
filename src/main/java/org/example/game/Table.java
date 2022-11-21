package org.example.game;

import org.example.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Table {

    static final List<Card> cards = new ArrayList<>();

    public Card getCard(int i) {
        return cards.get(i);
    }

    public int getSizeOfTable() {
        return cards.size();
    }

    public Card getNextCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public void showCardsInTablePlayer(int firstIndexPlayer, List<Player> players) {
        System.out.print("\nDiscarded cards: ");
        for (int i = 0; i < cards.size(); i++) {
            System.out.print((i == 0 ? "" : "; ") + (firstIndexPlayer + 1) + " player -> " + cards.get(i).getNameOfCard());
            firstIndexPlayer++;
            if (firstIndexPlayer == players.size()) {
                firstIndexPlayer = 0;
            }
        }
    }
}
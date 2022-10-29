package org.example;

import org.example.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private int nextId = 1;

    private int id;
    private ArrayList<Card> cards;
    private int value;

    public Player(Deck deck) {
        this.setId();
        cards = new ArrayList<Card>();
        for (int i = 0; i < 13; i++) {
            cards.add(deck.getNextCard());
        }
    }

    void setId() {
        id = nextId;
        nextId++;
    }

    int getId() {
        return id;
    }

    void deletePlayerCard(int index) {
        Deck.addInDeck(cards.get(index));
        cards.remove(index);
    }

    void addPlayerCardFromDeckWithIndexOne(Deck deck) {
        cards.add(deck.getNextCard());
    }

    void showCardsInHand() {
        System.out.print("\nCards of player #" + this.getId() + ": ");
        for (Card i : cards) {
            System.out.print(i.getNameOfCard() + "; ");
        }
    }

    public ArrayList<Integer> getThreeMaxCard() {
        ArrayList<Integer> maxCard = new ArrayList<>(Arrays.asList(0,0,0));
        for (int j = 0; j < 3; j++) {
            int index = 0;
            int value = 0;
            for (Card i : cards) {
                index++;
                if ((i.getRank().getValue()) > value && index <= 13) {
                    if ((j == 0) || (j == 1 && index != maxCard.get(0)) ||
                            (j == 2 && index != maxCard.get(0) && index != maxCard.get(1))) {
                        maxCard.set(j, index);
                        value = i.getRank().getValue();
                    }
                }
            }
        }
        maxCard.sort(Integer::compareTo);
        return maxCard;
    }

}

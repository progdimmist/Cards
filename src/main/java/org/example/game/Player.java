package org.example.game;

import org.example.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private static int nextId = 1;

    private int id;
    private final List<Card> cards;
    private final List<Card> takenCards;

    public boolean isTwoClubs = false;

    public Player(Deck deck) {
        this.setId();
        cards = new ArrayList<>();
        takenCards = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            cards.add(deck.getNextCard());
            if (cards.get(i).suit().getValue() == 3 && cards.get(i).rank().getValue() == 0) {
                isTwoClubs = true;
            }
        }
    }

    void setId() {
        id = nextId;
        nextId++;
        if (nextId == 5) {
            nextId = 1;
        }
    }

    int getId() {
        return id;
    }

    int getSuitThisIndex(int index) {
        return cards.get(index).suit().getValue();
    }

    public int indexTwoClubs() {
        int index = 0;
        for (int i = 0; i < 13; i++) {
            if (cards.get(i).suit().getValue() == 3 && cards.get(i).rank().getValue() == 0) {
                index = i;
            }
        }
        return index;
    }

    void deletePlayerCard(int index) {
        Deck.addInDeck(cards.get(index));
        cards.remove(index);
    }

    void addPlayerCardFromDeckWithIndexOne(Deck deck) {
        cards.add(deck.getNextCard());
    }

    void addTakenCard(Deck deck) {
        for (int i = 0; i < 4; i++) {
            takenCards.add(deck.getNextCard());
        }
    }

    void showCardsInHand() {
        System.out.print("\nCards of player #" + this.getId() + ": ");
        for (int i = 0; i < cards.size(); i++) {
            if (i != cards.size() - 1) System.out.print(cards.get(i).getNameOfCard() + "; ");
            else System.out.print(cards.get(i).getNameOfCard());
        }
    }

    void showTakenCardsInHand() {
        System.out.print("\nTaken cards of player #" + this.getId() + ": ");
        for (int i = 0; i < takenCards.size(); i++) {
            if (i != takenCards.size() - 1) System.out.print(takenCards.get(i).getNameOfCard() + "; ");
            else System.out.print(takenCards.get(i).getNameOfCard());
        }
    }

    int getPointsTakenCard() {
        int value = 0;
        for (Card takenCard : takenCards) {
            value += takenCard.getPointsCard();
        }
        return value;
    }

    ArrayList<Integer> getThreeMaxCard() {
        ArrayList<Integer> maxCard = new ArrayList<>(Arrays.asList(0, 0, 0));
        for (int j = 0; j < 3; j++) {
            int index = 0;
            int value = 0;
            for (Card i : cards) {
                index++;
                if ((i.rank().getValue()) > value && index <= 13) {
                    if ((j == 0) || (j == 1 && index != maxCard.get(0)) ||
                            (j == 2 && index != maxCard.get(0) && index != maxCard.get(1))) {
                        maxCard.set(j, index);
                        value = i.rank().getValue();
                    }
                }
            }
        }
        maxCard.sort(Integer::compareTo);
        return maxCard;
    }

    int getIndexMinCardThisSuitOrMaxCardAnotherSuit(int suit) {
        int minValue = 20;
        int minIndex = -1;
        for (int i = 0; i < cards.size(); i++) {
            if ((cards.get(i).rank().getValue() < minValue) && (cards.get(i).suit().getValue() == suit)) {
                minValue = cards.get(i).rank().getValue();
                minIndex = i;
            }
        }
        if (minIndex == -1) {
            minValue = -1;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).rank().getValue() > minValue) {
                    minValue = cards.get(i).rank().getValue();
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    int getIndexMinCard() {
        int minValue = 20;
        int minIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).rank().getValue() < minValue) {
                minValue = cards.get(i).rank().getValue();
                minIndex = i;
            }
        }
        return minIndex;
    }

}

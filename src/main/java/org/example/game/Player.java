package org.example.game;

import org.example.cards.Card;
import org.example.cards.Rank;
import org.example.cards.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private final List<Card> cards;
    private final List<Card> takenCards;


    public Player(Deck deck) {
        cards = new ArrayList<>();
        takenCards = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            cards.add(deck.getNextCard());
        }
    }


    int getSuitThisIndex(int index) {
        return cards.get(index).suit().getValue();
    }

    boolean isTwoClubs(){
        return cards.contains(new Card(Suit.CLUBS, Rank.TWO));
    }
    public int indexTwoClubs() {
        int index = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).suit().getUiString().equals("\u2663") && cards.get(i).rank().getUiValue().equals("2")) {
                index = i;
            }
        }
        return index;
    }

    void deletePlayerCard(int index) {
        addInDeck(cards.get(index));
        cards.remove(index);
    }

    public void addInDeck(Card card) {
        Table.cards.add(card);
    }

    void addPlayerCardFromDeckWithIndexOne(Table table) {
        cards.add(table.getNextCard());
    }

    void addTakenCard(Table table) {
        for (int i = 0; i < 4; i++) {
            takenCards.add(table.getNextCard());
        }
    }

    void showCardsInHand(int id) {
        System.out.print("\nCards of player #" + (id+1) + ": ");
        for (int i = 0; i < cards.size(); i++) {
            System.out.print((i == 0 ? "" : "; ") + cards.get(i).getNameOfCard());
        }
    }

    void showTakenCardsInHand(int id) {
        System.out.print("\nTaken cards of player #" + (id+1) + ": ");
        for (int i = 0; i < takenCards.size(); i++) {
            System.out.print((i == 0 ? "" : "; ") + takenCards.get(i).getNameOfCard());
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

    int getIndexCardForMove(int suit) {//min card this suit or max card other suit
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

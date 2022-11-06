package org.example.cards;

public record Card(Suit suit, Rank rank) {

    public String getNameOfCard() {
        return rank.getUiValue() + " of " + suit.getUiString();
    }

    public int getPointsCard() {
        if (suit.getValue() == 0) {
            return 1;
        } else if (suit.getValue() == 1 && rank.getValue() == 10) {
            return 13;
        } else return 0;
    }
}
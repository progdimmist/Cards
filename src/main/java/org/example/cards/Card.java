package org.example.cards;

public record Card(Suit suit, Rank rank) {

    public String getNameOfCard() {
        return rank.getUiValue() + suit.getUiString();
    }

    public int getPointsCard() {
        if (suit.getUiString().equals("\u2665") ) {
            return 1;
        } else if (suit.getUiString().equals("\u2660") && rank.getUiValue().equals("Q")) {
            return 13;
        }
        return 0;
    }

}
package org.example.cards;

public enum Suit {
    HEARTS("hearts"),
    SPADES("spades"),
    DIAMONDS("diamonds"),
    CLUBS("clubs");
    private final String uiString;

    Suit(String uiString) {
        this.uiString = uiString;
    }

    public String getUiString() {
        return uiString;
    }

}

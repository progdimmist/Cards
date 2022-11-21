package org.example.cards;

public enum Suit {
    HEARTS(0, "\u2665"),
    SPADES(1, "\u2660"),
    DIAMONDS(2, "\u2666"),
    CLUBS(3, "\u2663");
    private final String uiString;
    private final int value;

    Suit(int value, String uiString) {
        this.value = value;
        this.uiString = uiString;
    }

    public String getUiString() {
        return uiString;
    }

    public int getValue() {
        return value;
    }
}

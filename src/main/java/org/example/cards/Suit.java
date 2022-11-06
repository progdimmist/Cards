package org.example.cards;

public enum Suit {
    HEARTS(0, "hearts"),
    SPADES(1, "spades"),
    DIAMONDS(2, "diamonds"),
    CLUBS(3, "clubs");
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

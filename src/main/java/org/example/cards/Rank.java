package org.example.cards;

public enum Rank {
    TWO(0, "two"),
    THREE(1, "three"),
    FOUR(2, "four"),
    FIVE(3, "five"),
    SIX(4, "six"),
    SEVEN(5, "seven"),
    EIGHT(6, "eight"),
    NINE(7, "nine"),
    TEN(8, "ten"),
    JACK(9, "jack"),
    QUEEN(10, "queen"),
    KING(11, "king"),
    ACE(12, "ace");

    private final int value;
    private final String uiValue;

    Rank(int value, String uiValue) {
        this.value = value;
        this.uiValue = uiValue;
    }

    public int getValue() {
        return value;
    }

    public String getUiValue() {
        return uiValue;
    }
}

package org.example;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        // create deck of cards
        Deck deck = new Deck();

        // create 2 players
        Player player1 = new Player(deck);
        Player player2 = new Player(deck);
        Player player3 = new Player(deck);
        Player player4 = new Player(deck);


        allShowCardsInHand(player1, player2, player3, player4);

        passThreeCards(player1, player2, player1.getThreeMaxCard(), deck);
        passThreeCards(player2, player3, player2.getThreeMaxCard(), deck);
        passThreeCards(player3, player4, player3.getThreeMaxCard(), deck);
        passThreeCards(player4, player1, player4.getThreeMaxCard(), deck);

        System.out.println("\n");
        System.out.println("->->->Pass three cards around->->->");
        allShowCardsInHand(player1, player2, player3, player4);
    }

    private static void passThreeCards(Player player1, Player player2, ArrayList<Integer> passCard, Deck deck) {
        for (int i = 0; i < 3; i++) {
            player1.deletePlayerCard(passCard.get(i) - i - 1);
            player2.addPlayerCardFromDeckWithIndexOne(deck);
        }
    }

    private static void allShowCardsInHand(Player player1, Player player2, Player player3, Player player4) {
        player1.showCardsInHand();
        player2.showCardsInHand();
        player3.showCardsInHand();
        player4.showCardsInHand();
    }
}
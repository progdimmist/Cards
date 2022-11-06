package org.example.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public static void main(String[] args) {
        List<Integer> pointsPlayers = Arrays.asList(0, 0, 0, 0);
        for (int i = 1; i < 8; i++) {
            System.out.println("\n<=======> " + (i) + " ROUND <=======>");
            pointsPlayers = playRoundGame(pointsPlayers);
        }
        System.out.println("\nPoints players= " + pointsPlayers);
        System.out.println("\n"+(getWin(pointsPlayers)+1)+" player to win!!!");
    }

    private static List<Integer> playRoundGame(List<Integer> pointsPlayers) {
        Deck deck = new Deck();
        List<Player> players = new ArrayList<>(
                Arrays.asList(new Player(deck), new Player(deck), new Player(deck), new Player(deck)));
        allShowCardsInHand(players);
        for (int index1 = 0, index2 = 1; index1 < players.size(); index1++, index2++) {
            if (index2 == players.size()) index2 = 0;
            passThreeCards(players, index1, index2, players.get(index1).getThreeMaxCard(), deck);
        }
        System.out.println("\n\n->->->->-> Pass three cards around ->->->->->");
        allShowCardsInHand(players);
        System.out.print("\n");
        int beginnerPlayer = 0;
        int numberMove = 1;
        for (int i = 0; i < 13; i++) {
            System.out.println("\n\n->->->->-> " + numberMove + " move ->->->->->");
            if (numberMove == 1) {
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).isTwoClubs)
                        beginnerPlayer = j;
                }
            }
            beginnerPlayer = move(players, beginnerPlayer, deck, numberMove);
            numberMove++;
        }
        System.out.println("\n\n->->->->-> Taken cards all players ->->->->->");
        allShowTakenCardsInHand(players);
        System.out.println();
        return (getPoints(players, pointsPlayers));
    }

    private static void passThreeCards(List<Player> players, int index1, int index2, ArrayList<Integer> passCard, Deck deck) {
        for (int i = 0; i < 3; i++) {
            players.get(index1).deletePlayerCard(passCard.get(i) - i - 1);
            players.get(index2).addPlayerCardFromDeckWithIndexOne(deck);
        }
    }

    private static void allShowCardsInHand(List<Player> players) {
        for (Player player : players) {
            player.showCardsInHand();
        }
    }

    private static void allShowTakenCardsInHand(List<Player> players) {
        for (Player player : players) {
            player.showTakenCardsInHand();
        }
    }

    private static List<Integer> getPoints(List<Player> players, List<Integer> pointsPlayers) {
        for (int i = 0; i < players.size(); i++) {
            pointsPlayers.set(i, (pointsPlayers.get(i) + players.get(i).getPointsTakenCard()));
        }
        return pointsPlayers;
    }

    private static int getWin(List<Integer> pointsPlayer){
        int indexWin=0;
        int value=Integer.MAX_VALUE;
        for (int i = 0; i < pointsPlayer.size(); i++){
            if(pointsPlayer.get(i)<value){
                value=pointsPlayer.get(i);
                indexWin=i;
            }
        }
        return indexWin;
    }
    private static int move(List<Player> players, int beginnerPlayer, Deck deck, int numberMove) {
        int indexCard;
        int indexPlayer;
        int suit = 3;
        if (numberMove == 1) {

            indexCard = players.get(beginnerPlayer).indexTwoClubs();
            indexPlayer = beginnerPlayer;
            players.get(indexPlayer).deletePlayerCard(indexCard);

            for (int i = 0; i < players.size() - 1; i++) {
                indexPlayer++;
                if (indexPlayer == (players.size())) {
                    indexPlayer = 0;
                }
                indexCard = players.get(indexPlayer).getIndexMinCardThisSuitOrMaxCardAnotherSuit(suit);
                players.get(indexPlayer).deletePlayerCard(indexCard);
            }
        } else {
            indexCard = players.get(beginnerPlayer).getIndexMinCard();
            suit = players.get(beginnerPlayer).getSuitThisIndex(indexCard);
            players.get(beginnerPlayer).deletePlayerCard(indexCard);
            indexPlayer = beginnerPlayer;
            for (int i = 0; i < players.size() - 1; i++) {
                indexPlayer++;
                if (indexPlayer == (players.size())) {
                    indexPlayer = 0;
                }
                indexCard = players.get(indexPlayer).getIndexMinCardThisSuitOrMaxCardAnotherSuit(suit);
                players.get(indexPlayer).deletePlayerCard(indexCard);
            }
        }
        deck.showCardsInDeckPlayer(beginnerPlayer, players);
        int indexPlayerHoldingCards = indexPlayerHoldingCards(deck, players, beginnerPlayer, suit);
        System.out.print("\n" + (indexPlayerHoldingCards + 1) + " player takes these cards\n");
        allShowCardsInHand(players);
        players.get(indexPlayerHoldingCards).addTakenCard(deck);
        return indexPlayerHoldingCards;
    }

    private static int indexPlayerHoldingCards(Deck deck, List<Player> players, int indexFirstPlayer, int suitDiscardedCart) {
        int indexPlayerTakeCards = indexFirstPlayer;
        for (int i = 0; i < deck.getSizeOfDeck(); i++) {
            if (deck.getCard(i).suit().getValue() == suitDiscardedCart &&
                    deck.getCard(i).rank().getValue() > deck.getCard(indexPlayerTakeCards).rank().getValue()) {
                indexPlayerTakeCards = i;
            }
        }
        indexFirstPlayer += indexPlayerTakeCards;
        if (indexFirstPlayer >= players.size()) {
            indexFirstPlayer -= players.size();
        }
        return indexFirstPlayer;
    }
}
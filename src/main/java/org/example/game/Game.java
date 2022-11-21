package org.example.game;

import org.example.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public void start() {
        Deck deck;
        Table table=new Table();
        List<Player> players;
        List<Integer> pointsPlayers = Arrays.asList(0, 0, 0, 0);
        for (int i = 1; i < 8; i++) {
            deck = new Deck();
            players = new ArrayList<>(
                    Arrays.asList(new Player(deck), new Player(deck), new Player(deck), new Player(deck)));

            System.out.println("\n<=======> " + (i) + " ROUND <=======>");
            pointsPlayers = playRoundGame(pointsPlayers, table, players);
        }
        System.out.println("\nPoints players= " + pointsPlayers);
        System.out.println("\n" + (getWin(pointsPlayers) + 1) + " player to win!!!\uD83E\uDD73");
    }

    public List<Integer> playRoundGame(List<Integer> pointsPlayers, Table table, List<Player> players) {
        allShowCardsInHand(players);
        passThreeCards(players, table);
        System.out.println("\n\n->->->->-> Pass three cards around ->->->->->");
        allShowCardsInHand(players);
        System.out.print("\n");
        int beginnerPlayer = 0;
        int numberMove = 1;
        for (int i = 0; i < 13; i++) {
            System.out.println("\n\n->->->->-> " + numberMove + " move ->->->->->");
            if (numberMove == 1) {
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).isTwoClubs())
                        beginnerPlayer = j;
                }
            }
            beginnerPlayer = move(players, beginnerPlayer, table, numberMove);
            numberMove++;
        }
        System.out.println("\n\n->->->->-> Taken cards all players ->->->->->");
        allShowTakenCards(players);
        System.out.println();
        return (getPoints(players, pointsPlayers));
    }

    private void passThreeCards(List<Player> players, Table table) {
        for (int i = 0; i < players.size(); i++) {
            List<Integer> passCard = players.get(i).getThreeMaxCard();
            for (int j = 0; j < 3; j++) {
                players.get(i).deletePlayerCard(passCard.get(j) - j - 1);
                if (i != players.size() - 1)
                    players.get(i + 1).addPlayerCardFromDeckWithIndexOne(table);
                else players.get(0).addPlayerCardFromDeckWithIndexOne(table);
            }
        }
    }

    private void allShowCardsInHand(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).showCardsInHand(i);
        }
    }

    private void allShowTakenCards(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).showTakenCardsInHand(i);
        }
    }

    private List<Integer> getPoints(List<Player> players, List<Integer> pointsPlayers) {
        for (int i = 0; i < players.size(); i++) {
            pointsPlayers.set(i, (pointsPlayers.get(i) + players.get(i).getPointsTakenCard()));
        }
        return pointsPlayers;
    }

    public int getWin(List<Integer> pointsPlayer) {
        int indexWin = 0;
        int value = Integer.MAX_VALUE;
        for (int i = 0; i < pointsPlayer.size(); i++) {
            if (pointsPlayer.get(i) < value) {
                value = pointsPlayer.get(i);
                indexWin = i;
            }
        }
        return indexWin;
    }

    private int move(List<Player> players, int beginnerPlayer, Table table, int numberMove) {
        int indexCard;
        int indexPlayer;
        int suit = 3;
        if (numberMove == 1) {
            indexCard = players.get(beginnerPlayer).indexTwoClubs();
            indexPlayer = beginnerPlayer;
            players.get(indexPlayer).deletePlayerCard(indexCard);
        } else {
            indexCard = players.get(beginnerPlayer).getIndexMinCard();
            suit = players.get(beginnerPlayer).getSuitThisIndex(indexCard);
            players.get(beginnerPlayer).deletePlayerCard(indexCard);
            indexPlayer = beginnerPlayer;
        }
        for (int i = 0; i < players.size() - 1; i++) {
            indexPlayer++;
            if (indexPlayer == (players.size())) {
                indexPlayer = 0;
            }
            indexCard = players.get(indexPlayer).getIndexCardForMove(suit);
            players.get(indexPlayer).deletePlayerCard(indexCard);
        }
        table.showCardsInTablePlayer(beginnerPlayer, players);
        int indexPicker = indexPicker(table, players, beginnerPlayer, suit);
        System.out.print("\n" + (indexPicker + 1) + " player takes these cards\n");
        allShowCardsInHand(players);
        players.get(indexPicker).addTakenCard(table);
        return indexPicker;
    }

    private int indexPicker(Table table, List<Player> players, int beginnerPlayer, int suit) {
        Map<Card, Integer> map = new HashMap<>();
        int takenPlayer = beginnerPlayer;
        for (int i = 0; i < players.size(); i++) {
            map.put(table.getCard(i), beginnerPlayer);
            beginnerPlayer++;
            if (beginnerPlayer == players.size())
                beginnerPlayer = 0;
        }
        int indexLastPlayer = 0;
        for (int i = 0; i < table.getSizeOfTable(); i++) {
            if (table.getCard(i).suit().getValue() == suit &&
                    table.getCard(i).rank().getValue() > table.getCard(indexLastPlayer).rank().getValue()) {
                takenPlayer = map.get(table.getCard(i));
                indexLastPlayer = i;
            }
        }
        return takenPlayer;
    }
}
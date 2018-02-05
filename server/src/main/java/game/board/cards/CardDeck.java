package game.board.cards;

import requests.gamemoves.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {

    private ArrayList<Card> deck = new ArrayList<>();
    private CardType[] ornamentCards = {CardType.PYRAMID, CardType.TEMPLE, CardType.BURIALCHAMBER, CardType.OBELISK};
    private CardType[] locationCards = {CardType.ENTRANCE, CardType.SARCOPHAGUS, CardType.PAVEDPATH};
    private CardType[] toolCards = {CardType.LEVER, CardType.SAIL, CardType.HAMMER, CardType.CHISEL};

    public CardDeck() {
        createCards();
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    private void createCards() {
        Arrays.stream(ornamentCards).forEach(type -> {
            deck.add(new OrnamentCard(type));
            deck.add(new OrnamentCard(type));
        });

        for (CardType cardType : locationCards) {
            deck.add(new LocationCard(cardType));
            deck.add(new LocationCard(cardType));
        }

        Arrays.stream(toolCards).forEach(type -> {
            deck.add(new ToolCard(type));
            deck.add(new ToolCard(type));
            if (type == CardType.CHISEL || type == CardType.SAIL) {
                deck.add(new ToolCard(type));
            }
        });
    }
}

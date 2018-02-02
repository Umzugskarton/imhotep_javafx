package game.board.cards;

import requests.gamemoves.CardType.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class CardDeck {

  private ArrayList<Card> deck = new ArrayList<>();
  private Type[] ornamentCards = {Type.PYRAMID, Type.TEMPLE, Type.BURIALCHAMBER, Type.OBELISK};
  private Type[] locationCards = {Type.ENTRANCE, Type.SARCOPHAGUS, Type.PAVEDPATH};
  private Type[] toolCards = {Type.LEVER, Type.SAIL, Type.HAMMER, Type.CHISEL};

  public CardDeck() {
    createCards();
  }

  public ArrayList<Card> getDeck() {
    return this.deck;
  }

  private void createCards() {
    Arrays.stream(ornamentCards).forEach(type -> {
      deck.add(new OrnamentCard(type));
      deck.add(new OrnamentCard(type));
    });

    for (Type type : locationCards) {
      deck.add(new LocationCard(type));
      deck.add(new LocationCard(type));
    }

    Arrays.stream(toolCards).forEach(type -> {
      deck.add(new ToolCard(type));
      deck.add(new ToolCard(type));
      if (type == Type.CHISEL || type == Type.SAIL) {
        deck.add(new ToolCard(type));
      }
    });
  }

}

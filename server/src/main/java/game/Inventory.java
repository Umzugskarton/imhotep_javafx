package game;

import game.board.Cards.Card;
import game.board.Cards.OrnamentCard;
import game.board.Cards.StatueCard;
import game.board.Cards.ToolCard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 16.01.2018.
 */
public class Inventory {
  ArrayList<OrnamentCard> ornamentCards = new ArrayList<>();
  ArrayList<ToolCard> toolCards = new ArrayList<>();
  ArrayList<StatueCard> statueCards = new ArrayList<>();

  HashMap<String, ArrayList> map = new HashMap<>();

  public Inventory() {
    map.put("OrnamentCard", ornamentCards);
    map.put("StatueCard", statueCards);
    map.put("ToolCard", toolCards);
  }
// Todo: Karten richtig hinzufügen
  public void addCard(Card card){
    String type = card.getClass().toString();
     map.get(type).add(card);
  }
}

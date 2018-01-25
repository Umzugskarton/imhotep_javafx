package game;

import game.board.Cards.Card;
import game.board.Cards.OrnamentCard;
import game.board.Cards.StatueCard;
import game.board.Cards.ToolCard;

import java.util.ArrayList;

public class Inventory {

  private ArrayList<OrnamentCard> ornamentCards = new ArrayList<>();
  private ArrayList<ToolCard> toolCards = new ArrayList<>();
  private ArrayList<StatueCard> statueCards = new ArrayList<>();


  public Inventory() {

  }

  public ArrayList<OrnamentCard> getOrnamentCards() {
    return ornamentCards;
  }

  public ArrayList<StatueCard> getStatueCards() {
    return statueCards;
  }

  public ArrayList<ToolCard> getToolCards() {
    return toolCards;
  }

  public boolean ownsCard(Card card) {

    if (card instanceof ToolCard) {
      for (ToolCard tool : toolCards) {
        if (tool.getName().equals(card.getName())) {
          return true;
        }
      }
      return false;
    } else {
      ArrayList temp = new ArrayList();
      if (card instanceof OrnamentCard) {
        temp = ornamentCards;
      } else if (card instanceof StatueCard) {
        temp = statueCards;
      }
      for (Object object : temp) {
        Card cardInstance = (Card) object;
        if (cardInstance == card) {
          return true;
        }
      }
      return false;
    }
  }

  public void addCard(Card card) {
    if (card instanceof OrnamentCard) {
      ornamentCards.add((OrnamentCard) card);
    } else if (card instanceof ToolCard) {
      toolCards.add((ToolCard) card);
    } else if (card instanceof StatueCard) {
      statueCards.add((StatueCard) card);
    }
  }
}
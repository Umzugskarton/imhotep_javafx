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
        if (tool.getType() == card.getType()) {
          return true;
        }
      }
      return false;
    } else {
      ArrayList<? extends Card> temp = new ArrayList<>();
      if (card instanceof OrnamentCard) {
        temp = ornamentCards;
      } else if (card instanceof StatueCard) {
        temp = statueCards;
      }
      for (Card c : temp) {
        if (c.getType() == card.getType()) {
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

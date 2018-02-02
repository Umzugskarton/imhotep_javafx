package game;

import game.board.cards.Card;
import game.board.cards.OrnamentCard;
import game.board.cards.StatueCard;
import game.board.cards.ToolCard;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

  private ArrayList<OrnamentCard> ornamentCards = new ArrayList<>();
  private ArrayList<ToolCard> toolCards = new ArrayList<>();
  private ArrayList<StatueCard> statueCards = new ArrayList<>();

  public List<OrnamentCard> getOrnamentCards() {
    return ornamentCards;
  }

  public List<StatueCard> getStatueCards() {
    return statueCards;
  }

  public List<ToolCard> getToolCards() {
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

package game;

import data.user.User;
import game.board.SupplySled;
import game.board.cards.Card;
import game.board.cards.OrnamentCard;
import game.board.cards.StatueCard;
import game.board.cards.ToolCard;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Spieler. Enthält seine Punktanzahl, seine aktuellen Karten und seine Steine.
 */
public class Player {

  private int id;
  private int points = 0;
  private User user;
  private Inventory inventory = new Inventory();
  private SupplySled supplySled = new SupplySled();

  /**
   * Erstellt einen neuen Spieler.
   *
   * @param user der eingeloggte User, dem der Spieler zugeordnet ist
   * @param id die ID des Spielers im Spiel; "Spielernummer"
   */
  public Player(User user, int id) {
    this.user = user;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public void addCard(Card card) {
    this.inventory.addCard(card);
  }

  public boolean ownsCard(Card card) {
    return this.inventory.ownsCard(card);
  }

  public int getPoints() {
    return points;
  }

  public SupplySled getSupplySled() {
    return supplySled;
  }

  private class Inventory {

    private ArrayList<OrnamentCard> ornamentCards = new ArrayList<>();
    private ArrayList<ToolCard> toolCards = new ArrayList<>();
    private ArrayList<StatueCard> statueCards = new ArrayList<>();

    List<OrnamentCard> getOrnamentCards() {
      return ornamentCards;
    }

    List<StatueCard> getStatueCards() {
      return statueCards;
    }

    List<ToolCard> getToolCards() {
      return toolCards;
    }

    boolean ownsCard(Card card) {
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

    void addCard(Card card) {
      if (card instanceof OrnamentCard) {
        ornamentCards.add((OrnamentCard) card);
      } else if (card instanceof ToolCard) {
        toolCards.add((ToolCard) card);
      } else if (card instanceof StatueCard) {
        statueCards.add((StatueCard) card);
      }
    }
  }
}
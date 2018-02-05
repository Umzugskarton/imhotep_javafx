package game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import data.user.User;
import game.Player;
import game.board.cards.OrnamentCard;
import game.board.cards.StatueCard;
import game.board.cards.ToolCard;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import requests.gamemoves.CardType;

class PlayerTest {

  @Mock
  User user;

  @Test
  void ownsCardTest() {
    Player player = new Player(user, 0);

    CardType cardO = CardType.TEMPLE;
    CardType cardT = CardType.LEVER;

    OrnamentCard card1 = new OrnamentCard(cardO);
    ToolCard card2 = new ToolCard(cardT);
    StatueCard card3 = new StatueCard();

    player.addCard(card1);
    player.addCard(card2);
    player.addCard(card3);

    boolean result1 = player.ownsCard(card1);
    boolean result2 = player.ownsCard(card2);
    boolean result3 = player.ownsCard(card3);
    boolean result4 = player.ownsCard(new StatueCard());
    boolean result5 = player.ownsCard(new OrnamentCard(cardO));

    assertEquals(true, result1);
    assertEquals(true, result2);
    assertEquals(true, result3);
    assertEquals(true, result4);
    assertEquals(true, result5);
  }

  @Test
  void addStonesTest() {
    Player player = new Player(null, 0);
    player.addStones();
    int stones = player.getStones();

    //Überprüfen ob zum Start des Spiels 3 Steine hinzugefügt wird
    assertEquals(3, stones);

    //Überprüfen ob die Kapazität nicht überschritten wird
    player.addStones(3);
    stones = player.getStones();
    assertEquals(5, stones);
  }

  @Test
  void removeStonesTest() {
    Player player = new Player(null, 0);
    assertEquals(false, player.removeStone());
  }
}

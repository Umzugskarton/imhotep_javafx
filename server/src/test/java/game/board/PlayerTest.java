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

    assertEquals(true, result1);
    assertEquals(true, result2);
    assertEquals(true, result3);
  }
}

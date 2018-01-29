package game.board.cards;

import static org.junit.jupiter.api.Assertions.*;

import game.board.Cards.StatueCard;
import org.junit.jupiter.api.Test;

class StatueCardTest {

  @Test
  void calc() {
    StatueCard sc = new StatueCard();
    int points = sc.calc(1);
    assertEquals(1, points);
    points = sc.calc(2);
    assertEquals(3, points);
    points = sc.calc(3);
    assertEquals(6, points);
    points = sc.calc(4);
    assertEquals(10, points);
    points = sc.calc(5);
    assertEquals(15, points);
    points = sc.calc(6);
    assertEquals(17, points);
    points = sc.calc(7);
    assertEquals(19, points);
    points = sc.calc(8);
    assertEquals(21, points);
    points = sc.calc(21);
    assertEquals(47, points);
  }
}
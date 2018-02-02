package game.board;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplySledTest {

  @Test
  public void addStonesTest() {
    SupplySled supplySled = new SupplySled();
    supplySled.addStones();
    int stones = supplySled.getStones();

    //Überprüfen ob zum Start des Spiels 3 Steine hinzugefügt wird
    assertEquals(3, stones);

    //Überprüfen ob die Kapazität nicht überschritten wird
    supplySled.addStones(3);
    stones = supplySled.getStones();
    assertEquals(5, stones);
  }

  @Test
  public void removeStonesTest() {
    SupplySled supplySled = new SupplySled();
    assertEquals(false, supplySled.removeStone());
  }
}

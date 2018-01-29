package game.board;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Slothan on 29.01.2018.
 */
public class SupplySledTest {

    @Test
    public void addStonesTest() {
        SupplySled supplySled = new SupplySled();
        int stones = supplySled.getStones();
        supplySled.addStones();

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

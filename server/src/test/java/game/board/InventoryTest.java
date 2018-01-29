package game.board;

import game.Inventory;
import game.board.Cards.OrnamentCard;
import game.board.Cards.StatueCard;
import game.board.Cards.ToolCard;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Slothan on 28.01.2018.
 */
class InventoryTest {

    @Test
    void ownsCardTest() {
        Inventory inventory = new Inventory();

        String cardO = "temple";
        String cardT = "lever";


        OrnamentCard card1 = new OrnamentCard(cardO);
        ToolCard card2 = new ToolCard(cardT);
        StatueCard card3 = new StatueCard();

        //Expected
        boolean test1 = true;
        boolean test2 = true;
        boolean test3 = true;

        //Ergebnis
        boolean result1 = inventory.ownsCard(card1);
        boolean result2 = inventory.ownsCard(card2);
        boolean result3 = inventory.ownsCard(card3);

        assertEquals(test1, result1);
        assertEquals(test2, result2);
        assertEquals(test3, result3);


    }
}

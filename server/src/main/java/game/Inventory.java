package game;

import game.board.Cards.Card;
import game.board.Cards.OrnamentCard;
import game.board.Cards.StatueCard;
import game.board.Cards.ToolCard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 16.01.2018.
 */
public class Inventory {
  ArrayList<OrnamentCard> ornamentCards = new ArrayList<>();
  ArrayList<ToolCard> toolCards = new ArrayList<>();
  ArrayList<StatueCard> statueCards = new ArrayList<>();



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

  public boolean ownsCard(Card card){
    ArrayList temp = new ArrayList();
   if (card instanceof OrnamentCard)
     temp = ornamentCards;
   else if (card instanceof ToolCard)
     temp = toolCards;
   else if (card instanceof StatueCard)
     temp = statueCards;

   for (Object object : temp){
     Card cardInstance = (Card) object;
     if (cardInstance == card)
         return true;
   }
   return false;
  }

  public void addCard(Card card){
    if (card instanceof OrnamentCard)
     ornamentCards.add((OrnamentCard) card);
    else if (card instanceof ToolCard)
      toolCards.add((ToolCard) card);
    else if (card instanceof StatueCard)
    statueCards.add((StatueCard) card);
  }
}

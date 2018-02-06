package events.app.game;

public class AllChosenCardsEvent extends GameEvent  {
  int[] chosenCards;

  public AllChosenCardsEvent(int[] chosenCards, int lobbyId){
    this.chosenCards = new int[chosenCards.length];
    System.arraycopy(chosenCards, 0 , this.chosenCards, 0 , chosenCards.length);
    this.lobbyId = lobbyId;

  }

  public int[] getChosenCards() {
    return chosenCards;
  }
}

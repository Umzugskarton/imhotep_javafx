package events.app.game;

public class ChooseCardEvent extends GameEvent {

  private int playerId;
  private int[] chosenCards;

  public ChooseCardEvent(int playerId, int[] chosenCards, int lobbyId) {
    this.chosenCards = new int[chosenCards.length];
    this.playerId = playerId;
    System.arraycopy(chosenCards, 0 , this.chosenCards, 0 , chosenCards.length);
    this.lobbyId = lobbyId;
  }

  public int getPlayerId() {
    return playerId;
  }

  public int[] getChoosenCardsId() {
    return chosenCards;
  }
}

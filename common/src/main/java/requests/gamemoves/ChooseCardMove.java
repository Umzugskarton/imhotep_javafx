package requests.gamemoves;


import requests.RequestType;

public class ChooseCardMove implements Move{
  private int lobbyId;
  private int cardId;

  public ChooseCardMove(int lobbyId, int cardId){
    this.lobbyId = lobbyId;
    this.cardId = cardId;
  }

  @Override
  public int getLobbyId() {
    return lobbyId;
  }

  @Override
  public RequestType getType() {
    return RequestType.CHOOSE_CARD;
  }
}

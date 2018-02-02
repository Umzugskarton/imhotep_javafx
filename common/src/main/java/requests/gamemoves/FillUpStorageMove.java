package requests.gamemoves;

import requests.RequestType;

public class FillUpStorageMove implements Move {

  private int lobbyId;

  public FillUpStorageMove(int lobbyId) {
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  @Override
  public RequestType getType() {
    return RequestType.RESUPPLY_STORAGE;
  }
}

package requests.gamemoves;

import requests.RequestType;

public class LoadUpShipMove implements Move{
  private RequestType type = RequestType.LOAD_UP_SHIP;
  private int shipId;
  private int position;
  private int lobbyId;

  public LoadUpShipMove(int shipId, int position, int lobbyId){
    this.shipId = shipId;
    this.position = position;
    this.lobbyId=lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
  }

  public int getPosition() {
    return position;
  }

  public int getShipId() {
    return shipId;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  @Override
  public RequestType getType() {
    return type;
  }
}

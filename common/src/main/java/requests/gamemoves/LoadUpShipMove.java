package requests.gamemoves;

public class LoadUpShipMove implements Move{
  private String move = "LoadUpShip";
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

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  @Override
  public String getType() {
    return move;
  }

}
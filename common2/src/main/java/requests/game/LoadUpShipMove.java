package requests.game;

public class LoadUpShipMove extends GameRequest {
  private String move = "loadUpBoat";
  private int shipId;
  private int position;

  public LoadUpShipMove(){

  }

  public LoadUpShipMove(int shipId, int position){
    this.shipId = shipId;
    this.position = position;
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
}

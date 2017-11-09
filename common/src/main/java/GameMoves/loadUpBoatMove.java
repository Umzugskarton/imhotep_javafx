package GameMoves;

import java.util.Date;

public class loadUpBoatMove implements Move{
  private String move = "loadUpBoat";
  private int shipId;
  private int position;

  public loadUpBoatMove(){

  }

  public loadUpBoatMove(int shipId, int position){
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

  @Override
  public String getType() {
    return move;
  }

  @Override
  public Date getDate() {
    return null;
  }
}

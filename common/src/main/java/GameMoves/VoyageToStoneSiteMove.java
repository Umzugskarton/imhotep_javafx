package GameMoves;


import java.util.Date;

public class VoyageToStoneSiteMove implements Move{
  private String move = "VoyageToStoneSite";
  private int shipId;
  private String stonesite;

  public VoyageToStoneSiteMove(){}

  public VoyageToStoneSiteMove(int shipId, String stonesite){
    this.shipId = shipId;
    this.stonesite =stonesite;
  }

  public int getShipId() {
    return shipId;
  }

  public String getStonesite() {
    return stonesite;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  public void setStonesite(String stonesite) {
    this.stonesite = stonesite;
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

package GameMoves;


import java.util.Date;

public class voyageToStoneSiteMove implements Move{
  private String move = "voyageToSton";
  private int shipId;
  private int stonesite;

  public voyageToStoneSiteMove(){}

  public voyageToStoneSiteMove(int shipId, int stonesite){
    this.shipId = shipId;
    this.stonesite =stonesite;
  }

  public int getShipId() {
    return shipId;
  }

  public int getStonesite() {
    return stonesite;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  public void setStonesite(int stonesite) {
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

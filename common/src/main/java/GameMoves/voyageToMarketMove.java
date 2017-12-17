package GameMoves;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class voyageToMarketMove implements Move{
  private int shipId;
  private String stonesite;

  public voyageToMarketMove(){}

  public voyageToMarketMove(int shipId, String stonesite){
    this.shipId = shipId;
    this.stonesite =stonesite;
  }


  public int getShipId() {
    return shipId;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  public void setStonesite(String stonesite) {
    this.stonesite = stonesite;
  }

  @Override
  public String getType() {
    return "voyageToStoneSite";
  }

  @Override
  public Date getDate() {
    return null;
  }
}

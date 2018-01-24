package GameMoves;

import java.util.Date;

/**
 * Created on 16.12.2017.
 */
public class VoyageToMarketMove implements Move{
  private int shipId;
  private String stonesite;
  private int lobbyId;

  public VoyageToMarketMove(){}

  public VoyageToMarketMove(int shipId, String stonesite, int lobbyId){
    this.shipId = shipId;
    this.stonesite =stonesite;
    this.lobbyId = lobbyId;
  }

  public int getLobbyId() {
    return lobbyId;
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

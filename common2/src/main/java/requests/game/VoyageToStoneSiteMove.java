package requests.game;


public class VoyageToStoneSiteMove extends GameRequest {
  private String move = "voyageToSton";
  private int shipId;
  private int stonesite;

  public VoyageToStoneSiteMove(){}

  public VoyageToStoneSiteMove(int shipId, int stonesite){
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
}

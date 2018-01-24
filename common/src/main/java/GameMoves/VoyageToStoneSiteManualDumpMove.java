package GameMoves;


public class VoyageToStoneSiteManualDumpMove implements Move {
  private int shipId;
  private String stonesite;
  private int[] dumpOrder;

  public VoyageToStoneSiteManualDumpMove (){}

  public VoyageToStoneSiteManualDumpMove(int shipId, String stonesite, int[] dumpOrder){
    this.shipId = shipId;
    this.stonesite =stonesite;
    this.dumpOrder=dumpOrder;
  }


  public int[] getDumpOrder() {
    return dumpOrder;
  }

  public int getShipId() {
    return shipId;
  }

  @Override
  public String getType() {
    return "voyageToStoneSiteManualDump";
  }
}

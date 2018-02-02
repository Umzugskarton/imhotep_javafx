package requests.gamemoves;


public class VoyageToStoneSiteManualDumpMove implements Move  {
  private int shipId;
  private String stonesite;
  private int[] dumpOrder;
  private int lobbyId;

  public VoyageToStoneSiteManualDumpMove (){}

  public VoyageToStoneSiteManualDumpMove(int shipId, String stonesite, int[] dumpOrder, int lobbyId){
    this.lobbyId=lobbyId;
    this.shipId = shipId;
    this.stonesite =stonesite;
    this.dumpOrder=dumpOrder;
  }

  @Override
  public int getLobbyId() {
    return lobbyId;
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

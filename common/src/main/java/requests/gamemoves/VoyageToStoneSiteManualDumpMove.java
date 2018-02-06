package requests.gamemoves;


import requests.RequestType;

public class VoyageToStoneSiteManualDumpMove implements Move {

  private RequestType move = RequestType.VOYAGE_TO_STONE_SITE_MANUAL_DUMP;
  private int[] dumpOrder;
  private int lobbyId;

  public VoyageToStoneSiteManualDumpMove( int[] dumpOrder,
      int lobbyId) {
    this.lobbyId = lobbyId;
    this.dumpOrder = dumpOrder;
  }

  @Override
  public int getLobbyId() {
    return lobbyId;
  }

  public int[] getDumpOrder() {
    return dumpOrder;
  }

  @Override
  public RequestType getType() {
    return this.move;
  }
}

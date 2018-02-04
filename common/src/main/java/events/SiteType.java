package events;

public enum SiteType {
  MARKET,
  BURIAL_CHAMBER,
  TEMPLE,
  OBELISKS,
  PYRAMID;

  private String fileString;

  static {
    MARKET.fileString = "Market";
    BURIAL_CHAMBER.fileString = "BurialChamber";
    TEMPLE.fileString = "Temple";
    OBELISKS.fileString = "Obelisks";
    PYRAMID.fileString = "Pyramids";
  }

  public String getFileString() {
    return fileString;
  }
}

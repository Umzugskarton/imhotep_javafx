package events;

public enum SiteType {
  MARKET,
  BURIALCHAMBER,
  TEMPLE,
  OBELISKS,
  PYRAMID;

  private String fileString;

  static {
    MARKET.fileString = "Market";
    BURIALCHAMBER.fileString = "BurialChamber";
    TEMPLE.fileString = "Temple";
    OBELISKS.fileString = "Obelisks";
    PYRAMID.fileString = "Pyramids";
  }

  public String getFileString() {
    return fileString;
  }
}

package GameMoves;

import java.util.HashMap;

public class MoveFactory {
  private HashMap<String, Move> Dict = new HashMap<>();

  public MoveFactory() {
    Dict.put("LoadUpShip", new LoadUpShipMove());
    Dict.put("FillUpStorage", new FillUpStorageMove());
    Dict.put("VoyageToStoneSite", new VoyageToStoneSiteMove());
    Dict.put("ToolCard", new ToolCardMove());

  }

  public Move getMove(String move) {
    return Dict.get(move);
  }

}

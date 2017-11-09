package GameMoves;

import java.util.HashMap;

public class MoveFactory {
  private HashMap<String, Move> Dict = new HashMap<>();

  public MoveFactory() {
    Dict.put("loadUpShip", new loadUpBoatMove());
    Dict.put("fillUpStorage", new fillUpStorageMove());
    Dict.put("voyageToStoneSite", new voyageToStoneSiteMove());
  }

  public Move getMove(String move) {
    return Dict.get(move);
  }

}

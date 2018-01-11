package game.GameProcedures;


import GameMoves.Move;
import game.Game;

import java.util.HashMap;

public class ProcedureFactory {
  private HashMap<String, Procedure> Dict = new HashMap<>();

  public ProcedureFactory(int playerId, Game game) {
    Dict.put("FillUpStorage", new FillUpStorage(game, playerId));
    Dict.put("LoadUpShip", new LoadUpShip(game, playerId));
    Dict.put("VoyageToStoneSite", new VoyageToStoneSite(game, playerId));
  }

  public Procedure getProcedure(String m, Move move) {
    Procedure p = Dict.get(m);
    p.put(move);
    return p;
  }
}

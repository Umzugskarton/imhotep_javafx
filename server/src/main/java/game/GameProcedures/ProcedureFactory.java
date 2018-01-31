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
    Dict.put("LeadToolCard", new LeadToolCard(game, playerId));
  }

  public Procedure getProcedure(Move move) {
    Procedure p = Dict.get(move.getType());
    p.put(move);
    return p;
  }
}

package game.GameProcedures;


import GameMoves.Move;
import game.Game;

import java.util.HashMap;

public class ProcedureFactory {
  private HashMap<String, Procedure> Dict = new HashMap<>();

  public ProcedureFactory( int playerId, Game game, Move move){
    Dict.put("fillUpStorage", new fillUpStorage(move, game,  playerId));
    Dict.put("loadUpBoat", new loadUpBoat(move, game,  playerId));
    Dict.put("voyageToStoneSite", new voyageToStoneSite(move, game,  playerId));
  }

  public Procedure getProcedure(String m){
    Procedure p = getProcedure(m);
    return p;
  }
}

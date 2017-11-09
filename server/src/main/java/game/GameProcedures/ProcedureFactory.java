package game.GameProcedures;


import GameMoves.Move;
import game.Game;

import java.util.HashMap;

public class ProcedureFactory {
  private HashMap<String, Procedure> Dict = new HashMap<>();

  public ProcedureFactory( int playerId, Game game){
    Dict.put("fillUpStorage", new fillUpStorage( game,  playerId));
    Dict.put("loadUpBoat", new loadUpBoat( game,  playerId));
    Dict.put("voyageToStoneSite", new voyageToStoneSite( game,  playerId));
  }

  public Procedure getProcedure(String m, Move move){
    Procedure p = getProcedure(m);
    return p;
  }
}

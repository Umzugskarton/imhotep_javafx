package game.GameProcedures;

import GameMoves.Move;
import SRVevents.Event;

public interface Procedure {
  Event exec();
  void put(Move move);
}

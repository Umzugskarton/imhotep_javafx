package game.GameProcedures;

import requests.GameMoves.Move;
import events.Event;

public interface Procedure {

  Event exec();

  void put(Move move);
}

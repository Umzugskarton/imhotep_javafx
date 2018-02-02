package game.gameprocedures;

import requests.gamemoves.Move;
import events.Event;

public interface Procedure {

  Event exec();

  void put(Move move);
}

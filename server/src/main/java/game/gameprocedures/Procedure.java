package game.gameprocedures;

import events.Event;
import requests.gamemoves.Move;

public interface Procedure {

    Event exec();

    void put(Move move);
}

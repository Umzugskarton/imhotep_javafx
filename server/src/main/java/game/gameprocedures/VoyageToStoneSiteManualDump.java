package game.gameprocedures;

import events.Event;
import events.app.game.VoyageToStoneSiteManualDumpEvent;
import game.Game;
import game.board.Ship;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;

public class VoyageToStoneSiteManualDump extends Voyage {

    private VoyageToStoneSiteManualDumpMove move;
    private Game game;
    private int playerId;

    VoyageToStoneSiteManualDump(Game game, int playerId) {
        this.game = game;
        this.playerId = playerId;
    }

    public void put(Move move) {
        this.move = (VoyageToStoneSiteManualDumpMove) move;
    }

    public Event exec() {
        Ship ship = game.getShipByID(move.getShipId());
        ship.sortStones(move.getDumpOrder());
        return new VoyageToStoneSiteManualDumpEvent(game.getGameID());
    }

}

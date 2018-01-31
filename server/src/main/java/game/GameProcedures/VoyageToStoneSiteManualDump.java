package game.GameProcedures;

import GameEvents.VoyageToStoneSiteManualDumpEvent;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteManualDumpMove;
import SRVevents.Event;
import game.Game;
import game.board.Ship;

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
    return new VoyageToStoneSiteManualDumpEvent();
  }

}

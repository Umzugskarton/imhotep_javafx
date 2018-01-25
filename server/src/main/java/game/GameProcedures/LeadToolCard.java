package game.GameProcedures;

import GameEvents.CardNotInPossessionError;
import GameEvents.ToolCardEvent;
import GameMoves.Move;
import GameMoves.ToolCardMove;
import SRVevents.Event;
import game.Game;
import game.GameProcedures.ToolCardProtocols.*;
import game.board.Cards.ToolCard;

import java.util.HashMap;

public class LeadToolCard implements Procedure{
  private ToolCardMove move;
  private int playerId;
  private Game game;
  HashMap<String, IProtocol> protocolHashMap = new HashMap<>();


  LeadToolCard(Game game, int playerId) {
    this.playerId = playerId;
    this.game=game;
    protocolHashMap.put("hammer", new HammerProtocol(game , playerId));
    protocolHashMap.put("chisel", new ChiselProtocol(game , playerId));
    protocolHashMap.put("sail", new SailProtocol(game, playerId));
    protocolHashMap.put("lever", new LeverProtocol(game, playerId));
  }

  public void put(Move move) {
    this.move = (ToolCardMove) move;
  }

  public Event exec() {
    ToolCard dummy = new ToolCard(move.getName());
    if (game.getPlayer(playerId).getInventory().ownsCard(dummy)) {
      IProtocol protocol = protocolHashMap.get(move.getName());
      protocol.exec();
      return new ToolCardEvent(move.getName(), playerId, false);
    }
    else {
      return new CardNotInPossessionError(move.getName());
    }
  }
}
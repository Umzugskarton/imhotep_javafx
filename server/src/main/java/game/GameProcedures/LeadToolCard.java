package game.GameProcedures;

import GameMoves.CardType.Type;
import GameEvents.CardNotInPossessionError;
import GameEvents.ToolCardEvent;
import GameMoves.Move;
import GameMoves.ToolCardMove;
import SRVevents.Event;
import game.Game;
import game.GameProcedures.ToolCardProtocols.ChiselProtocol;
import game.GameProcedures.ToolCardProtocols.HammerProtocol;
import game.GameProcedures.ToolCardProtocols.IProtocol;
import game.GameProcedures.ToolCardProtocols.LeverProtocol;
import game.GameProcedures.ToolCardProtocols.SailProtocol;
import game.board.Cards.ToolCard;

import java.util.HashMap;

public class LeadToolCard implements Procedure{
  private ToolCardMove move;
  private int playerId;
  private Game game;
  private HashMap<Type, IProtocol> protocolHashMap = new HashMap<>();


  LeadToolCard(Game game, int playerId) {
    this.playerId = playerId;
    this.game=game;
    protocolHashMap.put(Type.HAMMER, new HammerProtocol(game , playerId));
    protocolHashMap.put(Type.CHISEL, new ChiselProtocol(game , playerId));
    protocolHashMap.put(Type.SAIL, new SailProtocol(game, playerId));
    protocolHashMap.put(Type.LEVER, new LeverProtocol(game, playerId));
  }

  public void put(Move move) {
    this.move = (ToolCardMove) move;
  }

  public Event exec() {
    ToolCard dummy = new ToolCard(move.getToolType());
    if (game.getPlayer(playerId).getInventory().ownsCard(dummy)) {
      IProtocol protocol = protocolHashMap.get(move.getToolType());
      protocol.exec();
      return new ToolCardEvent(move.getToolType(), playerId, false);
    }
    else {
      return new CardNotInPossessionError(move.getToolType());
    }
  }
}

package game.GameProcedures;

import java.util.EnumMap;
import requests.gamemoves.CardType;
import events.app.game.CardNotInPossessionError;
import events.app.game.ToolCardEvent;
import requests.gamemoves.Move;
import requests.gamemoves.ToolCardMove;
import events.Event;
import game.Game;
import game.GameProcedures.ToolCardProtocols.ChiselProtocol;
import game.GameProcedures.ToolCardProtocols.HammerProtocol;
import game.GameProcedures.ToolCardProtocols.IProtocol;
import game.GameProcedures.ToolCardProtocols.LeverProtocol;
import game.GameProcedures.ToolCardProtocols.SailProtocol;
import game.board.cards.ToolCard;

public class LeadToolCard implements Procedure {

  private ToolCardMove move;
  private int playerId;
  private Game game;
  private EnumMap<CardType, IProtocol> protocolMap = new EnumMap<>(CardType.class);


  LeadToolCard(Game game, int playerId) {
    this.playerId = playerId;
    this.game = game;
    protocolMap.put(CardType.HAMMER, new HammerProtocol(game, playerId));
    protocolMap.put(CardType.CHISEL, new ChiselProtocol(game, playerId));
    protocolMap.put(CardType.SAIL, new SailProtocol(game, playerId));
    protocolMap.put(CardType.LEVER, new LeverProtocol(game, playerId));
  }

  public void put(Move move) {
    this.move = (ToolCardMove) move;
  }

  public Event exec() {
    ToolCard dummy = new ToolCard(move.getToolType());
    if (game.getPlayer(playerId).getInventory().ownsCard(dummy)) {
      IProtocol protocol = protocolMap.get(move.getToolType());
      protocol.exec();
      return new ToolCardEvent(move.getToolType(), playerId, false);
    } else {
      return new CardNotInPossessionError(move.getToolType());
    }
  }
}

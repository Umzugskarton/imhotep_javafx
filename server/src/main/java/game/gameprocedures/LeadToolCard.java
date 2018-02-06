package game.gameprocedures;

import events.Event;
import events.app.game.CardNotInPossessionError;
import events.app.game.InventoryUpdateEvent;
import events.app.game.ToolCardEvent;
import game.Game;
import game.Player;
import game.board.cards.ToolCard;
import game.gameprocedures.toolcardprotocols.*;
import requests.gamemoves.CardType;
import requests.gamemoves.Move;
import requests.gamemoves.ToolCardMove;

import java.util.ArrayList;
import java.util.EnumMap;

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
    Player player = game.getPlayer(playerId);
    ToolCard dummy = new ToolCard(move.getToolType());
    if (player.ownsCard(dummy)) {
      IProtocol protocol = protocolMap.get(move.getToolType());
      protocol.exec();
      player.removeCard(dummy);
      ArrayList<ArrayList<CardType>> cardtypes = new ArrayList<>();
      for (Player p : game.getPlayers()){
          cardtypes.add(p.getCardTypes());
      }
      game.sendAll(new InventoryUpdateEvent(cardtypes, game.getGameID()));
      return new ToolCardEvent(move.getToolType(), playerId, false, game.getGameID());
    } else {
      return new CardNotInPossessionError(move.getToolType(), game.getGameID());
    }
  }
}

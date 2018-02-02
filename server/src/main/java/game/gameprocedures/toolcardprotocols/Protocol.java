package game.gameprocedures.toolcardprotocols;

import game.Game;
import requests.gamemoves.Move;

public abstract class Protocol implements IProtocol {

  protected Game game;
  protected int playerId;

  Protocol(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  Move acquireMove() {
    game.getExecutor().waitForMove();
    if (game.getExecutor().getMove() != null) {
      return game.getExecutor().getMove();
    }
    return null;
  }
}

package game.GameProcedures.ToolCardProtocols;

import requests.gamemoves.Move;
import game.Game;

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

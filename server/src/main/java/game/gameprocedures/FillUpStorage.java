package game.gameprocedures;

import events.app.game.FillUpStorageEvent;
import game.Game;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.Move;

public class FillUpStorage implements Procedure {

  private FillUpStorageMove move;
  private Game game;
  private int playerId;

  FillUpStorage(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (FillUpStorageMove) move;
  }

  public FillUpStorageEvent exec() {
    this.game.getPlayer(playerId).addStones();
    return new FillUpStorageEvent(playerId, game.getPlayer(playerId).getStones(), game.getGameID());
  }
}

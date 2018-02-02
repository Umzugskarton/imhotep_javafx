package game.GameProcedures;

import events.app.game.FillUpStorageEvent;
import requests.gamemoves.Move;
import requests.gamemoves.FillUpStorageMove;
import game.Game;

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
    this.game.getPlayer(playerId).getSupplySled().addStones();
    return new FillUpStorageEvent(playerId, game.getPlayer(playerId).getSupplySled().getStones());
  }
}

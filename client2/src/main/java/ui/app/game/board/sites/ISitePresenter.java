package ui.app.game.board.sites;

import GameEvents.ShipDockedEvent;

public interface ISitePresenter {
  void setStones(ShipDockedEvent e);
}

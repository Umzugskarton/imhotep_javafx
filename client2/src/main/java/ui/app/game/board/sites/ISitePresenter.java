package ui.app.game.board.sites;


import events.app.game.ShipDockedEvent;

public interface ISitePresenter {
  void setStones(ShipDockedEvent e);
}

package ui.app.game.board.sites.market;

import javafx.scene.layout.Pane;
import mvp.view.IView;
import ui.app.game.board.sites.market.cards.CardView;

import java.util.ArrayList;

public interface IMarketView extends IView {
  ArrayList<CardView> getCardViews();
  ArrayList<Pane> getCards();
  void initChooseCardView();
}

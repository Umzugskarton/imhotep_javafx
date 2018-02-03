package ui.app.game.board.sites.market;

import javafx.scene.layout.Pane;
import mvp.view.IView;

import java.util.ArrayList;

public interface IMarketView extends IView {
  ArrayList<CardView> getCardViews();
  ArrayList<Pane> getCards();
}

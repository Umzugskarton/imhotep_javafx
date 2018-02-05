package ui.app.game.board.sites.market.cards;

import java.util.List;
import javafx.scene.layout.GridPane;
import ui.dialog.IDialogView;

public interface IChooseCardView extends IDialogView {

  List<CardView> getCardViews();

  GridPane getCardGrid();

  void setCards();
}

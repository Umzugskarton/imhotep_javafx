package ui.app.game.board.sites.market.cards;
import javafx.scene.layout.GridPane;
import ui.dialog.IDialogView;
import java.util.List;

public interface IChooseCardView extends IDialogView {
  List<CardView> getCardViews();
  GridPane getCardGrid();
  void setCards();
}

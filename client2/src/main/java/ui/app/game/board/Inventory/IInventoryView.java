package ui.app.game.board.Inventory;

import javafx.scene.layout.GridPane;
import ui.app.game.board.sites.market.cards.CardView;
import ui.dialog.IDialogView;

public interface IInventoryView extends IDialogView {
    GridPane getCardGridPane();

    void addCard(CardView cardView);
}

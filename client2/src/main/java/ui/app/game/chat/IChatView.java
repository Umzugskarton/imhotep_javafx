package ui.app.game.chat;

import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import mvp.view.INavigateableSubView;
import mvp.view.IView;

public interface IChatView extends IView {

  TextFlow getChatText();

  TextField getMessageInput();

  void clearForm();
}
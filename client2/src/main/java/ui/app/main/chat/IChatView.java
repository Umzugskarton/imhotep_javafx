package ui.app.main.chat;

import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import mvp.view.INavigateableSubView;

public interface IChatView extends INavigateableSubView {

  TextFlow getChatText();

  TextField getMessageInput();

  void clearForm();
}
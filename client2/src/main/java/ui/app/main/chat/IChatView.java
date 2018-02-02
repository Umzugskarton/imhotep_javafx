package ui.app.main.chat;

import javafx.scene.paint.Color;
import mvp.view.IView;

public interface IChatView extends IView {

  void addChatMessage(String user, String msg);

  void addInfoMessage(String msg, Color color);

  void addWhisper(String user, String msg, boolean isClientReceiver);

  void clearForm();
}
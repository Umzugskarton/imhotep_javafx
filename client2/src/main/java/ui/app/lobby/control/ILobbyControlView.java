package ui.app.lobby.control;

import mvp.view.IView;

public interface ILobbyControlView extends IView {

    void updateStatusLabel(String m);

    void clearStatusLabel();

    void showStartGameButton(boolean show);

    void updateUserSizeLabel(String m);

}

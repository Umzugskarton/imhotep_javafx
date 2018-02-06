package ui.app.profil.settings;

import mvp.view.IView;

public interface IProfilSettingView extends IView {

    void updateStatusLabel(String m);

    void clearStatusLabel();

    void showStartGameButton(boolean show);

    void updateUserSizeLabel(String m);

}

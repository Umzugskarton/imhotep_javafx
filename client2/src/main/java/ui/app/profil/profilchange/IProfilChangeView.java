package ui.app.profil.profilchange;

import mvp.view.IView;

public interface IProfilChangeView extends IView {

    void updateStatusLabel(String m);

    void clearForm();
}

package login.view;

import javafx.scene.Scene;
import login.presenter.LoginPresenter;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public interface LoginView {

  void buildLogin();

  Scene getLoginScene();

  void setLoginPresenter(LoginPresenter loginPresenter);

  void updateStatusLabel(String result);
}

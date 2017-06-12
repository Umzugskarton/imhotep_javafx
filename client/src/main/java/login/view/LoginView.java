package login.view;

import javafx.scene.Scene;
import login.presenter.LoginPresenter;

public interface LoginView {

  void buildLogin();

  Scene getLoginScene();

  void setLoginPresenter(LoginPresenter loginPresenter);

  void updateStatusLabel(String result);
}

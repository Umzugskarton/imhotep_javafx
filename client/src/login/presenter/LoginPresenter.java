package login.presenter;

import login.model.LoginModelImpl;
import login.view.LoginView;
import main.SceneController;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public class LoginPresenter {

    private LoginView view;
    private SceneController sc;

    public LoginPresenter(LoginView view, LoginModelImpl loginModel, SceneController sc) {
        this.view = view;
        this.sc = sc;
        view.setLoginPresenter(this);
    }

    public void login(String pass) {
        String result = "Incorrect password";
        view.updateStatusLabel(result);
    }
/*
    public void toRegisterScene(){
      sc.toRegistrationScene();
    }
    */

    public void login(String username, String password){
     //   userService.login(username, password);
    }

    public LoginView getLoginView(){
      return this.view;
    }

    public SceneController getSceneController(){
      return this.sc;
    }
}

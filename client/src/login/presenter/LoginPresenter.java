package login.presenter;


import login.view.LoginView;
import main.SceneController;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public class LoginPresenter {

    private LoginView view;
    private SceneController sc;

    public LoginPresenter(LoginView view, SceneController sc) {
        this.view = view;
        this.sc = sc;
        view.setLoginPresenter(this);
    }

    public void toRegisterScene(){
      sc.toRegistrationScene();
    }

    public void login(String username, String password){

    }

    public LoginView getLoginView(){
      return this.view;
    }

    public void setResult(String message) {
        this.view.updateStatusLabel(message);
    }
}

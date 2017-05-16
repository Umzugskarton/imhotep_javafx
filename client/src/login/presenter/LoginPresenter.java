package login.presenter;


import json.ClientCommands;
import login.view.LoginView;
import main.SceneController;
import org.json.simple.JSONObject;

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
      JSONObject loginCommand = ClientCommands.loginCommand(username, password);
      this.sc.getClientSocket().send(loginCommand);
    }

    public LoginView getLoginView(){
      return this.view;
    }

    public void setResult(String message) {
        this.view.updateStatusLabel(message);
    }
}

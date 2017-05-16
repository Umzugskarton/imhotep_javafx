package registration.presenter;

import registration.view.*;
import registration.model.*;
import main.SceneController;


public class RegistrationPresenter{
  private RegistrationView view;
  private RegistrationModel model;
  private SceneController sc;

  public RegistrationPresenter(RegistrationViewImpl view, RegistrationModelImpl model, SceneController sc) {
    this.view = view;
    this.model = model;
    this.sc = sc;
    view.setRegistrationPresenter(this);
  }

  public void register(String pass) {
    String result = "Incorrect password";

    if (model.getPassword().equals(pass)) {
      result = "Correct password";
    }
    view.updateStatusLabel(result);
  }

    public void toLoginScene(){
      sc.toLoginScene();
    }


  public void register(String username, String password, String passwordRepeat, String userEmail){
    //   userService.register(username, password, passwordRepeat, userEmail);
  }

  public RegistrationView getRegistrationView(){
    return this.view;
  }

  public SceneController getSceneController(){
    return this.sc;
  }
}

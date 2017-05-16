package registration.presenter;

import main.SceneController;
import registration.view.RegistrationView;


public class RegistrationPresenter {
  private RegistrationView view;
  private SceneController sc;

  public RegistrationPresenter(RegistrationView view, SceneController sc) {
    this.sc = sc;
    this.view = view;
    view.setRegistrationPresenter(this);
  }

  public void register(String username, String password, String email) {
    System.out.println(username);
    System.out.println(password);
    System.out.println(email);
    //Hier das JSON Objekt erzeugen
    view.updateStatusLabel(""); //Ergebnis des Registers hier einfügen
  }

  public void toLoginScene(){
    sc.toLoginScene();
  }

  public RegistrationView getRegistrationView(){
    return this.view;
  }

}

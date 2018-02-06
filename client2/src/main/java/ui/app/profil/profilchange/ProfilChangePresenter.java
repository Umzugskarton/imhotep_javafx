package ui.app.profil.profilchange;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.app.profil.ChangeProfilDataEvent;
import mvp.presenter.Presenter;
import requests.profil.ChangeCredentialRequest;

import static misc.language.TextBundle.getString;

public class ProfilChangePresenter extends Presenter<IProfilChangeView> {

  private final Connection connection;
  private final User user;

  public ProfilChangePresenter(IProfilChangeView view, EventBus eventBus, Connection connection, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.eventBus.register(this);
  }

  public void sendChangeProfilEMail(String newEmail){
    if(!newEmail.isEmpty()){
      this.connection.send(new ChangeCredentialRequest(newEmail,1));
    } else {
      getView().updateStatusLabel("E-Mail darf nicht leer sein");
    }
  }

  public void sendChangeProfilPasswort(String oldPasswort, String newPasswort){
    System.out.println("Presenter handlechangePasswortButtonAction");
    if(validatePasswort(oldPasswort,newPasswort)){
      this.connection.send(new ChangeCredentialRequest(newPasswort,2));
    }
  }

  @Subscribe
  public void onLobbyInfoEvent(ChangeProfilDataEvent e){
    getView().updateStatusLabel(e.getMsg());
    getView().clearForm();
  }

  private boolean validatePasswort(String password1, String password2) {
    System.out.println("Validate");
    System.out.println(password1.length());
    String msg = "";
    if (!password1.isEmpty() && !password2.isEmpty() && password1.equals(password2) && password1.length() >= 8) {
      return true;
    } else {
      if (password1.isEmpty()) {
        msg += "Bitte ein Passwort eingeben. \n";
      }
      if (password2.isEmpty()) {
        msg += "Bitte das Passwort wiederholen. \n";
      }
      if (!password1.equals(password2)) {
        msg += "Die Passwörter stimmen nicht überein. \n";
      }
      if (password1.length() < 8) {
        msg += "Das Passwort muss mindestens 8 Zeichen besitzen. \n";
      }
    }
    getView().clearForm();
    getView().updateStatusLabel(msg);
    return false;
  }
}
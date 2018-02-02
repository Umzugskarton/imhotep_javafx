package create.presenter;

import requests.CreateRequest;
import create.view.CreateView;
import create.view.CreateViewImpl;
import main.SceneController;

public class CreatePresenter {

  private CreateView view;
  private SceneController sceneController;

  public CreatePresenter(SceneController sc) {
    this.sceneController = sc;

    this.view = new CreateViewImpl(this);
  }

  public void createLobby(String name, int size, String pass) {
    if(name.equals("")) {
      this.view.updateStatusLabel("Bitte geben Sie einen Namen für die Lobby ein!");
    } else if(pass.length() >= 16) {
      this.view.updateStatusLabel("Passwort zu lang. (Maximal 16 Zeichen)");
    } else {
      CreateRequest j = new CreateRequest(name, size, pass);
      this.sceneController.getClientSocket().send(j);
    }
  }



  public CreateView getCreateView() {
    return this.view;
  }

}

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

  public void createLobby(String name, int size, String password) {
    if(name.isEmpty()) {
      this.view.updateStatusLabel("Bitte geben Sie einen Namen für die Lobby ein!");
    } else {
      CreateRequest j = new CreateRequest(name, size, password);
      this.sceneController.getClientSocket().send(j);
    }
  }



  public CreateView getCreateView() {
    return this.view;
  }

}

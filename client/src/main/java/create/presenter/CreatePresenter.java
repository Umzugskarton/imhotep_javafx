package create.presenter;

import CLTrequests.createRequest;
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
    createRequest j = new createRequest(name, size, pass);
    this.sceneController.getClientSocket().send(j);
  }

  public CreateView getCreateView() {
    return this.view;
  }

}

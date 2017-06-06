package mainmenu.presenter;

import json.ClientCommands;
import main.SceneController;
import mainmenu.view.MainmenuView;
import org.json.simple.JSONObject;


/**
 * Created by mirco, kristin on 28.05.2017.
 */
public class MainmenuPresenter {

  private MainmenuView view;
  private SceneController sceneController;

    public MainmenuPresenter(MainmenuView view, SceneController sc) {
      this.view = view;
      this.sceneController = sc;
      view.setMainmenuPresenter(this);
      this.sceneController.getClientSocket().send(ClientCommands.userlistCommand());
    }

  public void processUserlist(String message) {
      this.view.updateUserlist(message);
  }
  public MainmenuView getMainmenuView() {
    return this.view;
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }
}

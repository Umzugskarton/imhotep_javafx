package mainmenu.presenter;

import main.SceneController;
import mainmenu.view.MainmenuView;

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
    }

  public MainmenuView getMainmenuView() {
    return this.view;
  }

  public void toMainmenuScene() { sceneController.toMainmenuScene();}
}

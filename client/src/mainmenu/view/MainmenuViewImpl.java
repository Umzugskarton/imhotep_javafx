package mainmenu.view;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mainmenu.presenter.MainmenuPresenter;

/**
 * Created by mirco, kristin on 28.05.2017.
 */
public class MainmenuViewImpl implements MainmenuView {

  private Scene mainmenuScene;
  private MainmenuPresenter mainmenuPresenter;
  private Label userList;

  public MainmenuViewImpl() { buildMainmenu(); }

  public void buildMainmenu() {
    GridPane grid = new GridPane();
    mainmenuScene = new Scene(grid);
    userList = new Label();
    grid.add(userList, 3, 5);
  }

  @Override
  public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;

  }

  public Scene getMainmenuScene() {
    return this.mainmenuScene;
  }

  public void updateUserlist(String result) {
    System.out.println(result);
    this.userList.setText(result);
  }

}


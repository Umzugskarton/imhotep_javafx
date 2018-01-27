package ui.app.game.board;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardView implements IBoardView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane mainViewRoot;

  @FXML
  private Pane havenPane;

  @FXML
  private GridPane storageGridPane;

  @FXML
  private Label roundLabel;

  @FXML
  private Label currentPlayerLabel;

  @FXML
  private ProgressBar turnTimerProgress;

  private final INavigateableView parentView;
  private final BoardPresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;

  // Own Parent
  private Parent myParent;

  public BoardView(INavigateableView parentView, EventBus eventBus, Connection connection, User user){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.user = user;
    this.mainPresenter = new BoardPresenter(this, eventBus, connection, user);
    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/game/BoardView.fxml", this, eventBus);
  }

  @Override
  public INavigateableView getParentView() {
    return null;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public String getTitle() {
    return "Board";
  }

  @Override
  public Parent getRootParent() {
    return null;
  }
}

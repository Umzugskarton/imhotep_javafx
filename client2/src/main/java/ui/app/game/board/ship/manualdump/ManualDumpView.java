package ui.app.game.board.ship.manualdump;


import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

public class ManualDumpView implements IManualDumpView{
  @FXML
  private GridPane chooseBox;

  private final IView parentView;
  private final ManualDumpPresenter mainPresenter;
  private final EventBus eventBus;

  private Connection connection;
  private int[] cargo;

  @FXML
  private Pane manualdump;

  private CommonLobby lobby;
  // Own Parent
  private Parent myParent;

  public ManualDumpView(IView parentView , EventBus eventBus, Connection connection, CommonLobby lobby) {
      this.parentView = parentView;
      this.lobby =lobby;
      mainPresenter = new ManualDumpPresenter(this , eventBus, connection, lobby);
      this.eventBus = eventBus;
      this.connection =connection;
      initOwnView();
  }

  @Override
  public String getTitle() {
    return "ManualDump";
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public void initOwnView() {
    if (myParent == null)
      myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/ManualDumpView.fxml", this, eventBus);
  }

  @FXML
  private void onStone0(){
    System.out.println("Pushed Stone 0");
    setPlace(0);
  }

  @FXML
  private void onStone1(){
    setPlace(1);
  }

  @FXML
  private void onStone2(){
    setPlace(2);
  }

  @FXML
  private void onStone3(){
    setPlace(3);
  }

  public void setCargo(int[] cargo) {
    this.cargo = cargo;
    for (int i = 0; i < cargo.length ;i++) {
      if (cargo[i] != -1) {
        Rectangle rectangle = (Rectangle) manualdump.getChildren().get(i);
        rectangle.setVisible(true);
        rectangle.setFill(Color.web(lobby.getUserbyLobbyId(cargo[i]).getColor()));
      }
    }
    mainPresenter.setNewCargoSize(cargo.length);
  }

  public int[] getCargo() {
    return cargo;
  }

  private void setPlace(int id){
    manualdump.getChildren().get(id).setVisible(false);
    chooseBox.getChildren().get(3-mainPresenter.getPlace()).setStyle("-fx-background-color: " + lobby.getUserbyLobbyId(cargo[id]).getColor());
    mainPresenter.setPlace(id);
  }

  @Override
  public void closeDialog() {

  }
}

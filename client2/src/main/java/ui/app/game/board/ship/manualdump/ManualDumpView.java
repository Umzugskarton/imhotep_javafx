package ui.app.game.board.ship.manualdump;


import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mvp.view.IView;

public class ManualDumpView implements IManualDumpView{
  @FXML
  private GridPane cardGridPane;

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
      this.cargo = cargo;
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
    setPlace(0);
    manualdump.getChildren().get(0).setVisible(false);
  }

  @FXML
  private void onStone1(){
    setPlace(1);
    manualdump.getChildren().get(1).setVisible(false);

  }

  @FXML
  private void onStone2(){
    setPlace(2);
    manualdump.getChildren().get(2).setVisible(false);

  }

  @FXML
  private void onStone3(){
    setPlace(3);
    manualdump.getChildren().get(3).setVisible(false);

  }

  public void setCargo(int[] cargo) {
    this.cargo = cargo;
    for (int i = 0; i < cargo.length ;i++) {
      manualdump.getChildren().get(i).setVisible(true);
    }
  }

  public int[] getCargo() {
    return cargo;
  }

  private void setPlace(int id){
    mainPresenter.setPlace(id);
  }

  @Override
  public void closeDialog() {

  }
}

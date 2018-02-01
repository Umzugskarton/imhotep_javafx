package ui.app.game.board;

import events.app.game.TurnEvent;
import events.app.game.GameInfoEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.game.board.ship.ShipView;
import ui.app.game.board.storage.StorageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

  @FXML
  private Pane berth0;

  @FXML
  private Pane berth1;

  @FXML
  private Pane berth2;

  @FXML
  private Pane berth3;


  private final INavigateableView parentView;
  private final BoardPresenter mainPresenter;
  private final EventBus eventBus;
  private final ArrayList<StorageView> storageViews = new ArrayList<>();
  private final ArrayList<ShipView> shipViews = new ArrayList<>();

  private final User user;
  private CommonLobby lobby;
  private final Connection connection;

  // Own Parent
  private Parent myParent;

  public BoardView(INavigateableView parentView, EventBus eventBus, Connection connection, User user, CommonLobby lobby){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.lobby = lobby;
    this.connection = connection;
    this.user = user;
    this.mainPresenter = new BoardPresenter(this, eventBus, connection, user, lobby);



    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @FXML
  void initialize(){
    for (int i = 0 ; i < lobby.getUsers().size(); i++){
      StorageView storageView = new StorageView(this, eventBus, connection, lobby.getUsers().get(i),
              lobby.getUsers().get(i).getUser().getId() == this.user.getId(), i);
      // TODO evtl. abändern Ich bin nicht glücklich wie bestimmt wird welcher Storage Presenter der deine ist und die LobbyId muss übergeben werden
      storageViews.add(storageView);
      storageGridPane.add(storageView.getRootParent(),  0, i);
    }
  }


  public void setShips(ArrayList<int[]> ships){
    for (int i = 0; i < ships.size(); i++){
      ShipView shipView =new ShipView(this, eventBus, connection, lobby , ships.get(i), i);
      shipViews.add(shipView);
      getBerths().get(i % 5).getChildren().add(shipView.getRootParent()); // sollte eh nie über 4 sein
    }
  }

  public ArrayList<StorageView> getStorageViews() {
    return storageViews;
  }

  public ArrayList<ShipView> getShipViews() {
    return shipViews;
  }

  public ArrayList<Pane> getBerths(){
    ArrayList<Pane> a = new ArrayList<>();
    Collections.addAll(a, berth0, berth1, berth2,berth3);
    return a;
  }


  public ProgressBar getTurnTimerProgress() {
    return turnTimerProgress;
  }

  @Subscribe
  private void setCurrentPlayer(TurnEvent event){
    currentPlayerLabel.setText(event.getUsername());

    // Aktuellen Spielernamen fettgedruckt anzeigen wenn der Client der aktuelle Spieler ist
    if (event.isMyTurn()) {
      currentPlayerLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
    } else {
      currentPlayerLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
    }
  }

  @Subscribe
  private void setRoundLabel(GameInfoEvent event){
    roundLabel.setText(String.valueOf(event.getRound()));
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/BoardView.fxml", this, eventBus);
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
    return myParent;
  }
}

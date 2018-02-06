package ui.app.game;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import events.app.game.WinEvent;
import helper.fxml.GenerateFXMLView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import mvp.view.INavigateableView;
import requests.lobby.LeaveLobbyRequest;
import ui.dialog.IDialogView;

import java.util.Arrays;


public class WinView implements IDialogView {

  @FXML
  private TableView pointTable;

  @FXML
  private Label announceLabel;

  private final INavigateableView parentView;
  private final EventBus eventBus;

  // Own Parent
  private Parent myParent;

  private WinEvent myWinEvent;

  private final Connection connection;
  private CommonLobby lobby;

  public WinView(INavigateableView parentView, EventBus eventBus, Connection connection, CommonLobby lobby, WinEvent winEvent) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    myWinEvent = winEvent;
    this.lobby = lobby;
    this.connection = connection;
    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if (myParent == null)
      myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/WinView.fxml", this, eventBus);
  }

  @FXML
  private void initialize(){
    ObservableList<String[]> data = FXCollections.observableArrayList();
    data.addAll(Arrays.asList(myWinEvent.getResults()));
    data.remove(0);//remove titles from data
    TableView<String[]> table = new TableView<>();
    for (int i = 0; i < myWinEvent.getResults()[0].length; i++) {
      TableColumn tc = new TableColumn(myWinEvent.getResults()[0][i]);
      final int colNo = i;
      tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
          return new SimpleStringProperty((p.getValue()[colNo]));
        }
      });
      table.getColumns().add(tc);
    }
      pointTable = table;

      announceLabel.setText(myWinEvent.getWinner());
      //announceLabel.setTextFill(Color.web(lobby.getUserByName(myWinEvent.getWinner()).getColor()));

  }

  @FXML
  private void leave(){
    this.connection.send(new LeaveLobbyRequest(lobby.getLobbyId()));
  }

  @Override
  public void closeDialog() {

  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public String getTitle() {
    return null;
  }
}

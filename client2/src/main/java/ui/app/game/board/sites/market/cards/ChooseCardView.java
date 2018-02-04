package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import mvp.view.IView;
import ui.dialog.IDialogView;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChooseCardView implements IChooseCardView{

  @FXML
  private GridPane cardGrid;

  private final ChooseCardPresenter mainPresenter;
  private final IView parentview;
  private final EventBus eventBus;
  private final Connection connection;
  private ArrayList<CardView> cardViews;

  private Parent myParent;

  public ChooseCardView(IView view , EventBus eventBus, Connection connection , ArrayList<CardView> cardViews, CommonLobby lobby){
    this.parentview = view;
    this.eventBus = eventBus;
    this.cardViews = cardViews;
    this.connection = connection;
    mainPresenter = new ChooseCardPresenter(this, eventBus, connection, lobby.getLobbyId());
    initOwnView();
  }

  @FXML
  private void initialize(){
    int card = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        cardGrid.add(cardViews.get(card).getRootParent() , i , j);
        card++;
      }
    }
  }

  @FXML
  public void chooseCard(MouseEvent event){
    Node s = (Node) event.getSource();
    Integer col = GridPane.getColumnIndex(s);
    Integer row = GridPane.getRowIndex(s);
    String binary = row.toString() + col.toString();
    int card = 0;
    for (int i = 0; i< binary.length(); i++){
      if (binary.charAt(i) == '1')
        card+=Math.pow(2,binary.length()-1-i);
    }
    mainPresenter.sendChooseCard(card);
  }

  public List<CardView> getCardViews() {
    return cardViews;
  }

  public GridPane getCardGrid() {
    return cardGrid;
  }


  @Override
  public void initOwnView() {
    if (myParent == null)
      myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/ChooseCardView.fxml", this , eventBus);
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public String getTitle() {
    return this.getClass().getSimpleName();
  }

}

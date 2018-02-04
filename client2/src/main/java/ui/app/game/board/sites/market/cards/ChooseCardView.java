package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import mvp.view.INavigateableView;
import ui.dialog.IDialogView;

import java.awt.event.MouseEvent;

public class ChooseCardView implements IChooseCardView{

  private final ChooseCardPresenter mainPresenter;
  private final INavigateableView parentview;
  private final EventBus eventBus;
  private final Connection connection;

  private Parent myParent;

  public ChooseCardView(INavigateableView view , EventBus eventBus, Connection connection , CommonLobby lobby){
    this.parentview = view;
    this.eventBus = eventBus;
    this.connection = connection;
    mainPresenter = new ChooseCardPresenter(this, eventBus, connection, lobby.getLobbyId() );
    initOwnView();
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


  @Override
  public void initOwnView() {
    if (myParent == null)
      myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/sites/ChooseCardView", this , eventBus);
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

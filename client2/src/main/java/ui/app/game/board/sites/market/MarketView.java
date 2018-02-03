package ui.app.game.board.sites.market;


import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import mvp.view.IView;
import ui.dialog.IDialogView;

import java.util.ArrayList;

public class MarketView implements IMarketView {

  @FXML
  private Pane cardPane;


  private final IView parentView;
  private final MarketPresenter mainPresenter;
  private final EventBus eventBus;
  private final ArrayList<CardView> cardViews = new ArrayList<>();

  private final Connection connection;

  // Own Parent
  private Parent myParent;

  public MarketView(IView parentView, EventBus eventBus, Connection connection){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.connection = connection;
    this.mainPresenter = new MarketPresenter(this, eventBus, connection);
    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @FXML
  private void initialize(){
    for (int i = 0 ; i < 4 ; i++)
      cardViews.add(new CardView(this,eventBus));
  }

  @FXML
  private void showCard0(){
    showOnDialogPane(cardViews.get(0));
  }
  @FXML
  private void showCard1(){
    showOnDialogPane(cardViews.get(1));
  }
  @FXML
  private void showCard2(){
    showOnDialogPane(cardViews.get(2));
  }
  @FXML
  private void showCard3(){
    showOnDialogPane(cardViews.get(3));
  }

  private void showOnDialogPane(IDialogView view){
    eventBus.post(view);
  }

  @Override
  public ArrayList<CardView> getCardViews() {
    return cardViews;
  }

  @Override
  public ArrayList<Pane> getCards() {
    ArrayList<Pane> cardPanes = new ArrayList<>();
    for (Node node : cardPane.getChildren()){
      cardPanes.add((Pane) node);
    }
    return cardPanes;
  }

  @Override
  public void initOwnView() {
    if (myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/sites/MarketView.fxml", this, eventBus);
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

}


package ui.app.game.board.sites.market;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import mvp.view.IView;
import requests.gamemoves.CardType;
import ui.dialog.IDialogView;
import static languageSupport.TextBundle.getString;

public class CardView implements IDialogView{

  @FXML
  private Pane cardBack;

  @FXML
  private Label cardname;

  @FXML
  private TextArea cardDescription;
  @FXML
  private Label cardClass;

  private final IView parentView;
  private final EventBus eventBus;

  private CardType.Type type;

  // Own Parent
  private Parent myParent;

  public CardView(IView parentView, EventBus eventBus){
    this.parentView = parentView;
    this.eventBus = eventBus;
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/CardView.fxml", this, eventBus);
  }

  void setCardType(CardType.Type type){
    this.type = type;
    cardBack.setId(getType(type));
    cardClass.setText(getTypeClass());
    cardname.setText(type.toString().toLowerCase());
    cardDescription.setText(getString(type.toString()));
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  String getType(CardType.Type type){
    switch (type){
      case SAIL:
      case LEVER:
      case CHISEL:
      case HAMMER: return "blue";
      case TEMPLE:
      case OBELISK:
      case PYRAMID:
      case BURIALCHAMBER: return "green";
      case ENTRANCE:
      case PAVEDPATH:
      case SARCOPHAGUS: return "red";
      case STATUE: return "yellow";
      default: return "";
    }
  }

  private String getTypeClass(){
    switch (type){
      case SAIL:
      case LEVER:
      case CHISEL:
      case HAMMER: return "Blaue Marktkarte";
      case TEMPLE:
      case OBELISK:
      case PYRAMID:
      case BURIALCHAMBER: return "Verzierung";
      case ENTRANCE:
      case PAVEDPATH:
      case SARCOPHAGUS: return "Steinbruch";
      case STATUE: return "Statue";
      default: return "";
    }
  }

  @Override
  public String getTitle() {
    return type.toString();
  }

  @FXML
  public void showCard(){
    System.out.println("Cardstack gedrückt");
  }
}


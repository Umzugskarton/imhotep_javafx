package ui.app.game.board.sites.market.cards;

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
import requests.gamemoves.ChooseCardMove;
import ui.app.game.HideDialogEvent;
import ui.dialog.IDialogView;

import static misc.language.TextBundle.getString;

public class CardView implements IDialogView {

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
    private boolean available;
    private boolean clickable;
    private Connection connection;
    private int cardId;
    private int lobbyId;

    private CardType type;

    // Own Parent
    private Parent myParent;

    public CardView(IView parentView, EventBus eventBus, Connection connection, int cardId, int lobbyid) {
        this.parentView = parentView;
        this.eventBus = eventBus;
        this.lobbyId = lobbyid;
        this.connection = connection;
        this.cardId = cardId;
        available = true;
        clickable = false;
        initOwnView();
    }

    public CardView(IView parentView, EventBus eventBus, Connection connection, int cardId) {
        this.parentView = parentView;
        this.eventBus = eventBus;
        this.connection = connection;
        this.cardId = cardId;
        available = true;
        clickable = false;
        initOwnView();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/CardView.fxml", this, eventBus);
    }

    public void setCardType(CardType type) {
        this.type = type;
        cardBack.setId(getStyleType(type));
        cardClass.setText(getTypeClass());
        cardname.setText(type.toString().toLowerCase());
        cardDescription.setText(getString("carddescription." + type.toString()));
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }


    @Override
    public Parent getRootParent() {
        return myParent;
    }

    public CardType getType() {
        return type;
    }

    public String getStyleType(CardType type) {
        switch (type) {
            case SAIL:
            case LEVER:
            case CHISEL:
            case HAMMER:
                return "blue";
            case TEMPLE:
            case OBELISK:
            case PYRAMID:
            case BURIALCHAMBER:
                return "green";
            case ENTRANCE:
            case PAVEDPATH:
            case SARCOPHAGUS:
                return "red";
            case STATUE:
                return "yellow";
            default:
                return "";
        }
    }

    private String getTypeClass() {
        switch (type) {
            case SAIL:
            case LEVER:
            case CHISEL:
            case HAMMER:
                return "Blaue Marktkarte";
            case TEMPLE:
            case OBELISK:
            case PYRAMID:
            case BURIALCHAMBER:
                return "Verzierung";
            case ENTRANCE:
            case PAVEDPATH:
            case SARCOPHAGUS:
                return "Steinbruch";
            case STATUE:
                return "Statue";
            default:
                return "";
        }
    }

    @FXML
    private void chooseCard() {
        if (clickable)
            connection.send(new ChooseCardMove(lobbyId, cardId));
        eventBus.post(new HideDialogEvent());
    }

    @Override
    public String getTitle() {
        return type.toString();
    }

    @FXML
    public void showCard() {
        System.out.println("Cardstack gedrückt");
    }
}


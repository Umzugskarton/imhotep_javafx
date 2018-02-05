package ui.app.game.board.sites.defaultsites;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import events.SiteType;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;
import ui.app.game.board.sites.ISitePresenter;
import ui.app.game.board.sites.ISiteView;
import ui.app.game.board.sites.obelisks.ObelisksPresenter;

import java.util.ArrayList;


public class DefaultSiteView implements ISiteView {

    @FXML
    private Pane stonePane;

    private final IView parentView;
    private final ISitePresenter mainPresenter;
    private final EventBus eventBus;
    private SiteType type;
    // Own Parent
    private Parent myParent;

    public DefaultSiteView(IView parentView, EventBus eventBus, Connection connection,
                           CommonLobby lobby, SiteType type) {
        this.type = type;
        this.parentView = parentView;
        this.eventBus = eventBus;
        if (type.equals(SiteType.OBELISKS)) {
            mainPresenter = new ObelisksPresenter(this, eventBus, connection, lobby);
        } else {
            mainPresenter = new DefaultSitePresenter(this, eventBus, connection, lobby, type);
        }
        initOwnView();
    }

    @Override
    public void initOwnView() {
        if (this.myParent == null) {
            String path = "/ui/fxml/app/game/sites/" + toTitleCase(type.toString().toLowerCase()) + "View.fxml";
            this.myParent = GenerateFXMLView.getINSTANCE()
                    .loadView(path, this, eventBus);
        }
    }

    @Override
    public ArrayList<Group> getStones() {
        ArrayList<Group> stones = new ArrayList<>();
        for (Node node : stonePane.getChildren()) {
            stones.add((Group) node);
        }
        return stones;
    }

    @Override
    public Rectangle getColorStones(int i) {
        return (Rectangle) getStones().get(i).getChildren().get(0);
    }

    @Override
    public Parent getRootParent() {
        return myParent;
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

}

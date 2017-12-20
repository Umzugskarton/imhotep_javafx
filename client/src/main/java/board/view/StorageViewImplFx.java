package board.view;


import board.presenter.StoragePresenter;
import commonLobby.LobbyUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class StorageViewImplFx {

    private StoragePresenter presenter;

    @FXML
    private Polygon polygon;

    @FXML
    private Rectangle flag;

    @FXML
    private AnchorPane root;

    @FXML
    private Label pointsLabel;

    @FXML
    private Circle pointsCircle;

    @FXML
    private Group stone0;

    @FXML
    private Group stone1;

    @FXML
    private Group stone2;

    @FXML
    private Group stone3;

    @FXML
    private Group stone4;

    public StorageViewImplFx() {

    }

    public void setUserColor(String color) {
        flag.setFill(Color.web(color));
        pointsCircle.setFill(Color.web(color));
        polygon.setFill(Color.web(color));
        for (Rectangle rec : getColorStones()) {
            rec.setFill(Color.web(color));
        }
    }

    public ArrayList<Rectangle> getColorStones() {
        ArrayList<Rectangle> a = new ArrayList<>();
        for (Group g : getStones()) {
            int i = 0;
            for (Node x : g.getChildren()) {
                if (i == 0) {
                    a.add((Rectangle) x);
                }
                i++;
            }
        }
        return a;
    }

    public ArrayList<Group> getStones() {
        ArrayList<Group> a = new ArrayList<>();
        Collections.addAll(a, stone0, stone1, stone2, stone3, stone4);
        return a;
    }

    public Label getPointsLabel() {
        return this.pointsLabel;
    }

    AnchorPane getRoot() {
        return root;
    }
}

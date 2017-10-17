package lobby.view;

import general.Delta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import commonLobby.LobbyUser;
import lobby.presenter.LobbyPresenter;
import main.SceneController;

public class LobbyViewImpl implements LobbyView{
    private Scene LobbyScene;
    private LobbyPresenter lobbyPresenter;
    private Label userList;
    private TableView<LobbyUser> table =  new TableView();
    private BorderPane main;
    private TextField messageField;

    public LobbyViewImpl() {
        buildLobby();
    }

    void buildLobby() {
        this.main = new BorderPane();
        main.setId("menuroot");
        GridPane grid = new GridPane();
        userList = new Label();
        grid.add(userList, 3, 5);

        HBox nav = new HBox();
        nav.setId("nav");
        nav.setSpacing(10);
        nav.setAlignment(Pos.CENTER_RIGHT);
        nav.setPadding(new Insets(15, 15, 15, 12));
        main.setTop(nav);
        main.setBottom(grid);

        Rectangle rect = new Rectangle(720, 480);
        rect.setArcHeight(30.0);
        rect.setArcWidth(30.0);
        main.setClip(rect);
        LobbyScene = new Scene(main);
        LobbyScene.setFill(Color.TRANSPARENT);

        messageField = new TextField();
        grid.add(messageField, 0 ,7);

        Button close = new Button("x");
        close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        Button min = new Button("_");
        min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SceneController test = lobbyPresenter.getSceneController();
            }
        });

        min.setMinWidth(20);
        close.setMinWidth(20);

        final Delta dragDelta = new Delta();
        nav.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x =
                        lobbyPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
                dragDelta.y =
                        lobbyPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
            }
        });
        nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lobbyPresenter.getSceneController().getStage()
                        .setX(mouseEvent.getScreenX() + dragDelta.x);
                lobbyPresenter.getSceneController().getStage()
                        .setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });

        nav.getChildren().addAll(min, close);

        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("username"));
        TableColumn lastNameCol = new TableColumn("Color");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("DUMMY"));

        Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory
                = new Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
            @Override
            public TableCell call(final TableColumn<LobbyUser, String> param) {
                final TableCell<LobbyUser, String> cell = new TableCell<LobbyUser, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            LobbyUser lobbyUser = getTableView().getItems().get(getIndex());
                            HBox hbox = new HBox();
                            Rectangle color = new Rectangle();
                            color.setHeight(15);
                            color.setWidth(15);
                            hbox.setSpacing(5);
                            color.setFill(Color.web(lobbyUser.getColor()));
                            color.setOnMouseClicked(event -> {
                                System.out.println(lobbyUser.getColor());
                            });
                            hbox.getChildren().add(color);
                            setGraphic(hbox);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        lastNameCol.setCellFactory(cellFactory);


        TableColumn joinCol = new TableColumn("Bereit");
        joinCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory2
                = new Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
            @Override
            public TableCell call(final TableColumn<LobbyUser, String> param) {
                final TableCell<LobbyUser, String> cell = new TableCell<LobbyUser, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            LobbyUser lobbyUser = getTableView().getItems().get(getIndex());
                            HBox hbox = new HBox();
                            ImageView img = new ImageView();
                            img.setFitHeight(15);
                            img.setFitWidth(10);
                            hbox.setSpacing(5);
                            img.setImage(new Image("ank.png"));
                            if (lobbyUser.isReady()){
                                hbox.getChildren().add(img);
                                setGraphic(hbox);
                            } else{
                                setGraphic(null);
                            }
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        joinCol.setCellFactory(cellFactory2);


        table.getColumns().addAll(firstNameCol, lastNameCol, joinCol);
        table.setMaxHeight(180);
        table.setPrefHeight(180);
        main.setLeft(table);
    }

    public void initLobbyInfo() {
       table.setItems(this.lobbyPresenter.getCLTLobby().getObservableUsers());
    }


    public void openModal(String msg){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getStylesheets().add("style.css");
        dialogVbox.getStyleClass().add("dialog");
        Label info= new Label(msg);
        Button confirm = new Button("OK");
        confirm.addEventHandler(ActionEvent.ACTION , event -> dialog.close());
        dialogVbox.getChildren().addAll(new Text(" "),info, confirm);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void setLobbyPresenter(LobbyPresenter lobbyPresenter) {
        this.lobbyPresenter = lobbyPresenter;
    }

    public Scene getLobbyScene() {
        return this.LobbyScene;
    }
}

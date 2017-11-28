package lobby.view;

import general.Delta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import commonLobby.LobbyUser;
import lobby.presenter.LobbyPresenter;
import main.SceneController;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import static general.TextBundle.getString;

public class LobbyViewImpl implements LobbyView {

  private Scene LobbyScene;
  private LobbyPresenter lobbyPresenter;
  private Label userList;
  private TableView<LobbyUser> table = new TableView();
  private BorderPane main;
  private TextField messageInput;
  private String username;
  private GridPane grid = new GridPane();
  private TextFlow chatText;

  public LobbyViewImpl() {
    buildLobby();
  }

  void buildLobby() {
    this.main = new BorderPane();
    main.setId("menuroot");

    userList = new Label();
    grid.add(userList, 3, 5);
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(15, 15, 15, 12));

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
    this.chatText = new TextFlow();
    this.chatText.setPadding(new Insets(5));
    this.chatText.setId("#msg");
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-border-radius: 5px;-fx-background-radius: 5px;-fx-background: white;");
    scrollPane.setContent(chatText);
    scrollPane.setMaxHeight(150);
    scrollPane.setPrefHeight(150);

    this.messageInput = new TextField();
    this.messageInput.setPromptText(getString("enterMessage"));

    Button sendButton = new Button(getString("send"));
    sendButton.addEventHandler(ActionEvent.ACTION, event -> {
      System.out.println(messageInput.getText());
      lobbyPresenter.sendChatMsg(messageInput.getText());
      messageInput.clear();
      messageInput.requestFocus();
    });

    this.messageInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
          if (!messageInput.getText().isEmpty()) {
            sendButton.fire();
          }
        }
      }
    });
    ColumnConstraints column = new ColumnConstraints();
    column.setFillWidth(true);
    column.setHgrow(Priority.ALWAYS);
    grid.getColumnConstraints().add(column);

    column = new ColumnConstraints();
    column.setFillWidth(false);
    column.setHgrow(Priority.NEVER);
    grid.getColumnConstraints().add(column);

    RowConstraints row = new RowConstraints();
    row.setFillHeight(true);
    row.setVgrow(Priority.NEVER);
    grid.getRowConstraints().add(row);

    row = new RowConstraints();
    row.setFillHeight(false);
    row.setVgrow(Priority.NEVER);
    grid.getRowConstraints().add(row);


    grid.add(scrollPane, 0, 4, 2, 1);
    grid.add(messageInput, 0, 6);
    grid.add(sendButton, 1, 6);

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

              //Shit-Button-Lösung. Valve, pls fix
              // Anmerkung: Ja shit Lösung weil er im Callback für die Colorzeile ist


              color.setFill(Color.web(lobbyUser.getColor()));
              color.setOnMouseClicked(event -> {
                //Benutzer kann nur seine eigene Farbe ändern
                if (getLobbyPresenter().getUsername().equals(lobbyUser.getUsername())) {
                  getLobbyPresenter().sendChangeColorRequest();
                  System.out.println(lobbyUser.getColor());
                } else {
                  System.out.println(getLobbyPresenter().getUsername());
                  System.out.println(lobbyUser.getUsername());
                }

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
              if (lobbyUser.isReady()) {
                this.setStyle("-fx-background-color: green");
              } else {
                this.setStyle("-fx-background-color: red");
              }
              setText(null);
            }
          }
        };
        return cell;
      }
    };
    joinCol.setCellFactory(cellFactory2);


    Button setReady = new Button("Bereit");
    grid.add(setReady, 4, 1);
    setReady.addEventHandler(ActionEvent.ACTION, e ->
            getLobbyPresenter().sendSetReadyRequest());

    table.getColumns().addAll(firstNameCol, lastNameCol, joinCol);
    table.setMaxHeight(120);
    table.setPrefHeight(120);
    table.setMaxWidth(180);
    grid.add(table, 0, 0);
  }

  public void initLobbyInfo() {

    if (lobbyPresenter.checkHost()) {
      Button startGame = new Button("Start Game");
      grid.add(startGame, 5, 1);
      startGame.addEventHandler(ActionEvent.ACTION, e -> {
        if (getLobbyPresenter().checkAllReady()) {
          getLobbyPresenter().startGame();
          System.out.print("Go!");
        } else {
          System.out.print("Es sind nicht alle bereit!");
        }
      });
    }

    table.setItems(this.lobbyPresenter.getCLTLobby().getObservableUsers());
  }

  public void updateTable() {
    for (int i = 0; i < table.getItems().size(); i++) {
      table.getItems().clear();
    }
    table.setItems(this.lobbyPresenter.getCLTLobby().getObservableUsers());
  }

  public void openModal(String msg) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogVbox = new VBox(20);
    dialogVbox.getStylesheets().add("style.css");
    dialogVbox.getStyleClass().add("dialog");
    Label info = new Label(msg);
    Button confirm = new Button("OK");
    confirm.addEventHandler(ActionEvent.ACTION, event -> dialog.close());
    dialogVbox.getChildren().addAll(new Text(" "), info, confirm);
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  public TextFlow getChatText() {
    return this.chatText;
  }

  public void setLobbyPresenter(LobbyPresenter lobbyPresenter) {
    this.lobbyPresenter = lobbyPresenter;
  }

  public LobbyPresenter getLobbyPresenter() {
    return lobbyPresenter;
  }

  public Scene getLobbyScene() {
    return this.LobbyScene;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public TableView<LobbyUser> getTable() {
    return this.table;
  }
}
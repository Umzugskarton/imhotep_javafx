package ui.app;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.main.lobby.LobbyJoinSuccessfulEvent;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvp.view.IView;
import mvp.view.ShowViewEvent;
import ui.app.game.GameView;
import ui.app.lobby.LobbyView;
import ui.app.main.MainView;
import ui.popup.PopupView;
import ui.popup.createLobby.CreateLobbyView;
import ui.popup.createLobby.ShowCreateLobbyPopupEvent;
import ui.start.ShowStartViewEvent;
import ui.start.login.LoginView;

import java.net.URL;
import java.util.ResourceBundle;

public class AppView implements IAppView  {

    @FXML
    private ResourceBundle resource;

    @FXML
    private URL location;

    @FXML
    AnchorPane appViewRoot;

    @FXML
    Pane appViewTopPane;

    @FXML
    MenuButton appViewMenuButton;

    @FXML
    TabPane appViewMainTabPane;

    @FXML
    Tab appViewMainTab;

    @FXML
    Pane appViewBottomPane;

    private final AppPresenter presenter;
    private final EventBus eventBus;
    private final User user;

    // Own Parent
    private Parent myParent;

    //TabSubViews
    private MainView mainView;
    private LobbyView lobbyView;
    private GameView gameView;

    //PopupView
    private PopupView popupView;

    public AppView(EventBus eventBus, Connection connection, User user){
        this.eventBus = eventBus;
        this.user = user;
        this.presenter = new AppPresenter(this, eventBus, connection);
        bind();
        initOwnView();
    }

    private void bind() {
        eventBus.register(this);
    }

    @Override
    public void initOwnView(){
        if(this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/AppView.fxml", this, eventBus);
    }

    @FXML
    private void initialize(){
        this.mainView = new MainView(this, eventBus, this.presenter.getConnection(), this.user);
        Pane mainViewPane = (Pane) this.appViewMainTab.getContent();
        mainViewPane.getChildren().add(this.mainView.getRootParent());
    }

    public boolean addTab(LobbyView lobbyView){
        Tab tab = new Tab();
        tab.setText(lobbyView.getTitle());
        tab.setContent(lobbyView.getRootParent());
        tab.setId("lobbyTab");
        return this.appViewMainTabPane.getTabs().add(tab);
    }

    public boolean addTab(GameView gameView){
        Tab tab = new Tab();
        tab.setText(gameView.getTitle());
        tab.setContent(gameView.getRootParent());
        tab.setId("gameTab");
        return this.appViewMainTabPane.getTabs().add(tab);
    }

    public void openPopup(IView view) {
        if (this.popupView == null)
            this.popupView = new PopupView(view);

            //this.popupView.setView(view);

        popupView.show();
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return new ShowStartViewEvent();
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @Subscribe
    public void onLobbyJoinSuccessfulEvent(LobbyJoinSuccessfulEvent e){
        addTab(new LobbyView(this, this.eventBus, this.presenter.getConnection(), this.user));
    }


    public void onShowCreateLobbyPopupEvent(ShowCreateLobbyPopupEvent e) {
        openPopup(new CreateLobbyView(this.eventBus, this.presenter.getConnection()));
    }
}


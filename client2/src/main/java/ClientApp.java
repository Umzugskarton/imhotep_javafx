import GameEvents.TurnEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.game.StartGameEvent;
import events.main.CreateEvent;
import events.main.login.LoginSuccessfulEvent;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.app.AppView;
import ui.popup.PopupView;
import ui.popup.createLobby.CreateLobbyView;
import ui.popup.createLobby.ShowCreateLobbyPopupEvent;
import ui.start.StartView;

public class ClientApp extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final EventBus eventBus = new EventBus();

    private Stage primaryStage;
    private Group group = new Group();
    private Scene scene = new Scene(group);

    private StartView startView;
    private AppView appView;

    DebugApp debugApp = new DebugApp(this.eventBus);

    private Connection connection = new Connection(this.eventBus);

    private User authenticatedUser;

    @Override
    public void init() {
        eventBus.register(this);
        eventBus.post(new TurnEvent());
        logger.info("EventBus registriert");
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //this.primaryStage.initStyle(StageStyle.UTILITY);
        this.primaryStage.sizeToScene();

        this.startView = new StartView(eventBus, connection);
        setContent(this.startView.getRootParent());

        primaryStage.setScene(scene);
        primaryStage.show();

        debugApp.getStage().show();
    }

    @Override
    public void stop(){
        this.debugApp.getStage().close();
    }

    private void setContent(Parent parent){
        group.getChildren().clear();
        group.getChildren().add(parent);
    }

    private void setResizable(Boolean resizable){
        primaryStage.setResizable(resizable);
    }

    private void setWindowSize(int width, int height){
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }

    @Subscribe
    public void onLoginSuccessfulEvent(LoginSuccessfulEvent e){
        this.authenticatedUser = e.getUser();

        if(this.appView == null)
            this.appView = new AppView(eventBus, connection, authenticatedUser);

        setContent(this.appView.getRootParent());
        this.primaryStage.sizeToScene();
    }

    @Subscribe
    public void onPopup(ShowCreateLobbyPopupEvent e){
        CreateLobbyView createLobbyView = new CreateLobbyView(this.eventBus, this.connection);
        PopupView popupView = new PopupView(createLobbyView);
        this.group.getChildren().add(popupView.getRootParent());
    }
}

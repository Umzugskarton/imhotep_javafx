import GameEvents.TurnEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.main.login.LoginSuccessfulEvent;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.app.AppView;
import ui.start.StartView;

public class ClientApp extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final EventBus eventBus = new EventBus();

    private Stage primaryStage;
    private Scene scene = new Scene(new Group());

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

        setResizable(false);
        setWindowSize(1024, 576);

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
        scene.setRoot(parent);
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
    }
}

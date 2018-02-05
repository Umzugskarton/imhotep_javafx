import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import connection.ConnectionDebug;
import data.user.User;
import events.start.login.LoginSuccessfulEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.app.AppView;
import ui.layout.StageLayout;
import ui.start.StartView;

public class ClientAppDebug extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final EventBus eventBus = new EventBus();

    private Stage primaryStage;
    private Group group = new Group();
    private Scene scene = new Scene(group);

    private StartView startView;
    private AppView appView;

    //Layout
    private StageLayout stageLayout;

    DebugApp debugApp = new DebugApp(this.eventBus);

    private Connection connection = new ConnectionDebug(this.eventBus);

    private User authenticatedUser;

    @Override
    public void init() {
        eventBus.register(this);
        logger.info("EventBus registriert");
    }

    @Override
    public void start(Stage primaryStage) {
        this.stageLayout = new StageLayout(primaryStage, this.scene, this.eventBus);

        this.primaryStage = primaryStage;

        this.startView = new StartView(eventBus, connection, stageLayout);

        setContent(this.startView.getRootParent());
        this.stageLayout.setWindowSize(720, 480);

        primaryStage.setScene(scene);
        primaryStage.show();

        //Zu Debug-Zwecken
        debugApp.getStage().show();
    }

    @Override
    public void stop() {

    }

    private void setContent(Parent parent) {
        group.getChildren().clear();
        group.getChildren().add(parent);
    }


    @Subscribe
    public void onLoginSuccessfulEvent(LoginSuccessfulEvent e) {
        this.authenticatedUser = e.getUser();

        if (this.appView == null)
            this.appView = new AppView(eventBus, connection, authenticatedUser, stageLayout);

        setContent(this.appView.getRootParent());

        this.stageLayout.setWindowSize(1300, 900);
    }
}

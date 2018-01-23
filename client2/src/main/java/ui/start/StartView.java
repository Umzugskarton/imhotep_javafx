package ui.start;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import mvp.view.ShowViewEvent;
import ui.start.login.LoginView;
import ui.start.login.ShowLoginViewEvent;
import ui.start.register.RegistrationView;
import ui.start.register.ShowRegisterViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StartView implements IStartView {

    @FXML
    private ResourceBundle resource;

    @FXML
    private URL location;

    @FXML
    private Pane startViewRoot;

    @FXML
    private Pane subPane;

    private final StartPresenter presenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    //Subview
    private LoginView loginView;
    private RegistrationView registrationView;


    public StartView(EventBus eventBus, Connection connection){
        this.eventBus = eventBus;
        this.presenter = new StartPresenter(this, eventBus, connection);
        bind();
        initOwnView();
    }

    private void bind() {
        eventBus.register(this);
    }

    @Override
    public void initOwnView(){
        if(this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/start/StartView.fxml", this, eventBus);
    }

    @FXML
    private void initialize(){
        this.loginView = new LoginView(this, eventBus, this.presenter.getConnection());
        setSubParent(loginView.getRootParent());
    }

    public void setSubParent(Parent subParent){
        this.subPane.getChildren().clear();
        this.subPane.getChildren().add(subParent);
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
    public void onShowLoginViewRequired(ShowLoginViewEvent e) {
        if (this.loginView == null)
            this.loginView = new LoginView(this, eventBus, this.presenter.getConnection());

        setSubParent(this.loginView.getRootParent());
    }

    @Subscribe
    public void onShowRegisterViewRequired(ShowRegisterViewEvent e) {
        if (this.registrationView == null) {
            this.registrationView = new RegistrationView(this, eventBus, this.presenter.getConnection());
        }
        setSubParent(this.registrationView.getRootParent());

        this.registrationView.setNameField(e.getUsername());
        this.registrationView.setPasswordField(e.getPasswort());
    }
}

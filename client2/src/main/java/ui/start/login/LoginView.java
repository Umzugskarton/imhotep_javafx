package ui.start.login;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.start.register.ShowRegisterViewEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginView implements ILoginView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane rootParent;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    Button signInButton;

    @FXML
    private Text actiontarget;

    private Parent myParent = rootParent;

    private final INavigateableView parentView;
    private final LoginPresenter loginPresenter;
    private final EventBus eventBus;

    public LoginView(INavigateableView parentView, EventBus eventBus, Connection clientSocket){
        this.parentView = parentView;
        this.loginPresenter = new LoginPresenter(this, eventBus, clientSocket);
        this.eventBus = eventBus;
        initOwnView();
    }

    @Override
    public void initOwnView() {
        if(this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/start/login/LoginView.fxml", this, eventBus);
    }

    @FXML
    private void handleToRegisterButtonAction(ActionEvent event) {
        eventBus.post(new ShowRegisterViewEvent(this.userField.getText(), this.passwordField.getText()));
        clearForm();
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        String username = userField.getText();
        String password = passwordField.getText();

        loginPresenter.sendLoginRequest(username, password);
    }

    @FXML
    private void handlePressedKeyAction(KeyEvent event) {
        if(KeyCode.ENTER == event.getCode() ){
            signInButton.fire();
        }
    }

    //@Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @Override
    public void showLoginFailed(String message) {
        actiontarget.setText(message);
    }

    @Override
    public void clearForm() {
        userField.setText("");
        passwordField.setText("");
    }

    @Override
    public INavigateableView getParentView() {
        return this.parentView;
    }

    @Override
    public String getTitle() {
        return "Login";
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return new ShowLoginViewEvent();
    }
}
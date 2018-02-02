package ui.start.register;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.dialog.IDialogView;
import ui.start.login.ShowLoginViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationView implements IRegistrationView {

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
    private PasswordField passwordRepeatField;

    @FXML
    private TextField emailField;

    @FXML
    private Text actiontarget;

    private Parent myParent = rootParent;

    private final INavigateableView parentView;

    private final RegistrationPresenter registrationPresenter;
    private final EventBus eventBus;

    public RegistrationView(INavigateableView parentView, EventBus eventBus, Connection connection){
        this.parentView = parentView;
        this.registrationPresenter = new RegistrationPresenter(this, eventBus, connection);
        this.eventBus = eventBus;
        initOwnView();
    }

    @Override
    public void initOwnView() {
        if(this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/start/register/RegistrationView.fxml", this, eventBus);
    }

    @FXML
    void initialize() {
    }

    @FXML
    void handleToLoginButtonAction(ActionEvent event) {
        eventBus.post(new ShowLoginViewEvent());

    }

    @FXML
    void handleRegisterButtonAction(ActionEvent event) {
        registrationPresenter.sendRegisterRequest(
                userField.getText(), passwordField.getText(), passwordRepeatField.getText(), emailField.getText());
    }

    public void updateStatusLabel(String result) {
        actiontarget.setText(result);
    }

    @Override
    public INavigateableView getParentView() {
        return this.parentView;
    }

    @Override
    public String getTitle() {
        return "Register";
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return new ShowRegisterViewEvent();
    }

    @Override
    public void clearForm() {
        userField.setText("");
        passwordField.setText("");
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    public void setNameField(String nameField) {
        this.userField.setText(nameField);
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.setText(passwordField);
    }


    @Override
    public void hideDialog() {

    }

    @Override
    public void showDialog(IDialogView view) {

    }
}

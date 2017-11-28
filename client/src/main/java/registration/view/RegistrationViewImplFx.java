package registration.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import registration.presenter.RegistrationPresenter;

public class RegistrationViewImplFx {

    private RegistrationPresenter registrationPresenter;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private TextField emailField;

    @FXML
    private Label statusLabel;

    @FXML
    void login_handle(ActionEvent event) {
        registrationPresenter.toLoginScene();
    }

    @FXML
    void register_handle(ActionEvent event) {
        String password1 = passwordField.getText();
        String password2 = passwordField1.getText();
        String username = nameField.getText();
        String email = emailField.getText();
        System.out.println(registrationPresenter);
        registrationPresenter.sendRegisterRequest(username, password1, password2, email);
    }

    public void setRegistrationPresenter(RegistrationPresenter registrationPresenter) {
        this.registrationPresenter = registrationPresenter;
    }

    public void updateStatusLabel(String result) {
        statusLabel.setText(result);
    }
}

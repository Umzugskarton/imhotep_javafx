package profile.view;

import profile.presenter.ProfilePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;

/**
 * Created by Slothan/Dennis Lindt on 25.06.2017.
 */
public class ProfileViewImpl extends GridPane implements ProfileView {
    private ProfilePresenter profilePresenter;
    private Label usernameLabel;
    private Label emailLabel;
    private TextField emailInput;

    public ProfileViewImpl(ProfilePresenter profilePresenter) {
        this.profilePresenter = profilePresenter;

        buildProfile();
    }

    public void buildProfile() {
        GridPane grid = this;
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));

        this.usernameLabel = new Label();
        this.emailLabel = new Label();
        this.emailInput = new TextField();

        Button changeColorButton = new Button("E-Mail ändern");
        changeColorButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                emailInput.setVisible(true);
            }
        });
        grid.add(usernameLabel, 1, 1);
        grid.add(emailLabel, 1, 2);
        grid.add(changeColorButton, 2, 2);
        grid.add(emailInput, 1,3);

        emailInput.setVisible(false);
    }

    public Label getUsernameLabel() {
        return this.usernameLabel;
    }
    public Label getEmailLabel() {return this.emailLabel; }
    public TextField getEmailInputField() { return this.emailInput; }

}

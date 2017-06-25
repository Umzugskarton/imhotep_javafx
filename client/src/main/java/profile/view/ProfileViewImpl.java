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
    private Label username;

    public ProfileViewImpl(ProfilePresenter profilePresenter) {
        this.profilePresenter = profilePresenter;

        buildProfile();
    }

    public void buildProfile() {
        GridPane grid = this;
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));

        Label username = new Label (getUsername());

        Button changeColorButton = new Button("Farbe ändern");
        changeColorButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        grid.add(username, 0, 0);
        grid.add(changeColorButton, 1, 1);




    }

    public String getUsername() {
        return "test";
    }


}

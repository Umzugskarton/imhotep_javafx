package profile.presenter;

import main.SceneController;
import profile.view.ProfileView;
import profile.view.ProfileViewImpl;

/**
 * Created by Slothan/Dennis Lindt on 25.06.2017.
 */
public class ProfilePresenter {
    private ProfileView view;
    private SceneController sceneController;

    public ProfilePresenter(SceneController sc) {
        this.sceneController = sc;
        this.view = new ProfileViewImpl(this);
    }

    void changeColor() {

    }

    public ProfileView getProfileView() { return this.view; }

    public void updateUsernameLabel(String username) {
       // Username holen
        this.view.getUsernameLabel().setText("Username: " + username);
    }

    public void updateEmailLabel(String email) {
        this.view.getEmailLabel().setText("Email: " + email);
    }

}

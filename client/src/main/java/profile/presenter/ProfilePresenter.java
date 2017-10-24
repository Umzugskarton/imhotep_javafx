package profile.presenter;

import CLTrequests.changeCredentialRequest;
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

    public void processChangeResponse(boolean validate, String msg) {
        if(validate) {
            this.view.updateStatusLabel(msg);
        } else {
            this.view.updateStatusLabel("Etwas lief schief.");
        }
    }

    public ProfileView getProfileView() { return this.view; }

    public void updateUsernameLabel(String username) {
       // Username holen
        this.view.getUsernameLabel().setText("Username: " + username);
    }
    public void updateEmailLabel(String email) {
        this.view.getEmailLabel().setText("Email: " + email);
    }

    public void sendChangeRequest(String email){
        if(this.validate(email)){
            this.view.updateStatusLabel("");
            changeCredentialRequest changeCredentialsCommand = new changeCredentialRequest(email);
            this.sceneController.getClientSocket().send(changeCredentialsCommand);
        }
    }

    private boolean validate(String email) {
        String msg = "";
        if(!email.isEmpty()) {
            return true;
        } else {
            msg += "Bitte eine E-Mail eingeben. \n";
        }
        this.view.updateStatusLabel(msg);
        return false;
    }

}

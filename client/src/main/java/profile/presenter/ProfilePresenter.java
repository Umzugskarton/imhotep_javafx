package profile.presenter;

import main.SceneController;
import profile.view.ProfileView;
import profile.view.ProfileViewImpl;
import requests.profil.ChangeCredentialRequest;

public class ProfilePresenter {

  private ProfileView view;
  private SceneController sceneController;

  public ProfilePresenter(SceneController sc) {
    this.sceneController = sc;
    this.view = new ProfileViewImpl(this);
  }

  public void processChangeResponse(boolean validate, String msg) {
    if (validate) {
      this.view.updateStatusLabel(msg);
    } else {
      this.view.updateStatusLabel("Etwas lief schief.");
    }
  }

  public ProfileView getProfileView() {
    return this.view;
  }

  public void updateUsernameLabel(String username) {
    this.view.getUsernameLabel().setText("Username: " + username);
  }

  public void updateEmailLabel(String email) {
    this.view.getEmailLabel().setText("Email: " + email);
  }

  public void sendChangeRequest(String credential, Integer type) {
    if (type == 1) {
      if (this.validate(credential)) {
        this.view.updateStatusLabel("");
        ChangeCredentialRequest changeCredentialsCommand = new ChangeCredentialRequest(credential,
            type);
        this.sceneController.getClientSocket().send(changeCredentialsCommand);
      }
    }
    if (type == 2 || type == 3) {
      this.view.updateStatusLabel("");
      ChangeCredentialRequest changeCredentialsCommand = new ChangeCredentialRequest(credential,
          type);
      this.sceneController.getClientSocket().send(changeCredentialsCommand);
    }
  }

  private boolean validate(String email) {
    if (!email.isEmpty()) {
      return true;
    } else {
      this.view.updateStatusLabel("Bitte eine E-Mail eingeben. \n");
      return false;
    }
  }

}

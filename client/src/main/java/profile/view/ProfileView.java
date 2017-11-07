package profile.view;

import javafx.scene.control.Label;

public interface ProfileView {

  void buildProfile();

  Label getUsernameLabel();

  Label getEmailLabel();

  void updateStatusLabel(String result);

}

package profile.view;

import javafx.scene.control.Label;

/**
 * Created by Slothan/Dennis Lindt on 25.06.2017.
 */
public interface ProfileView {
    void buildProfile();

    Label getUsernameLabel();
    Label getEmailLabel();

    void updateStatusLabel(String result);

}

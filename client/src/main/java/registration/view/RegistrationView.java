package registration.view;

import javafx.scene.Scene;
import registration.presenter.RegistrationPresenter;

public interface RegistrationView {

  void buildRegistration();

  Scene getRegistrationScene();

  void setRegistrationPresenter(RegistrationPresenter registrationPresenter);

  void updateStatusLabel(String result);
}

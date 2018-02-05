package ui.start.register;

import mvp.view.INavigateableSubView;

public interface IRegistrationView extends INavigateableSubView {

  void updateStatusLabel(String message);

  void clearForm();

  void clearPasswordField();
}

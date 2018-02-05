package ui.start.login;

import mvp.view.INavigateableSubView;

public interface ILoginView extends INavigateableSubView {

  void showLoginFailed(String message);

  void clearForm();
}

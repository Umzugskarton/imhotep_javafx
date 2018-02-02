package mvp.view;

public interface INavigateableView extends IView, IDialogableView {
  String getTitle();

  ShowViewEvent getEventToShowThisView();
}

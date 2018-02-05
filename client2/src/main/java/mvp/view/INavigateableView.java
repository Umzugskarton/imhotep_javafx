package mvp.view;

import ui.dialog.misc.IDialogableView;

public interface INavigateableView extends IView, IDialogableView {

  String getTitle();

  ShowViewEvent getEventToShowThisView();
}

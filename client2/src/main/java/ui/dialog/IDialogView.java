package ui.dialog;

import mvp.view.INavigateableView;
import mvp.view.IView;

public interface IDialogView extends IView, INavigateableView {
    @Override
    String getTitle();
}

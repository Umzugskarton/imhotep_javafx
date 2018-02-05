package ui.dialog.misc;

import ui.dialog.IDialogView;

public interface IDialogableView {
    void hideDialog();

    void showDialog(IDialogView view);
}

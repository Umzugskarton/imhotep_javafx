package ui.dialog;

import ui.dialog.misc.ViewIdentifier;

public class ShowDialogEvent {

  private ViewIdentifier viewIdentifier;

  public ShowDialogEvent(ViewIdentifier viewIdentifier) {
    this.viewIdentifier = viewIdentifier;
  }

  public ViewIdentifier getViewIdentifier() {
    return this.viewIdentifier;
  }
}

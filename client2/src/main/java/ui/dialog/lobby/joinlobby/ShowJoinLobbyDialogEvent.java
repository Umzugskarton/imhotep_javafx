package ui.dialog.lobby.joinlobby;

import ui.app.main.lobbylist.LobbyTableData;
import ui.dialog.ShowDialogEvent;
import ui.dialog.misc.ViewIdentifier;

public class ShowJoinLobbyDialogEvent extends ShowDialogEvent {

  LobbyTableData lobbydata;

  public ShowJoinLobbyDialogEvent(LobbyTableData lobbydata, ViewIdentifier viewIdentifier) {
    super(viewIdentifier);
    this.lobbydata = lobbydata;
  }

  public LobbyTableData getLobbyData() {
    return lobbydata;
  }
}

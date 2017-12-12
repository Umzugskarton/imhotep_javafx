package board.presenter;


import board.view.StorageViewImplFx;
import commonLobby.LobbyUser;

public class StoragePresenter {
  private StorageViewImplFx view;
  private LobbyUser user;

  public StoragePresenter(LobbyUser user, StorageViewImplFx view){
    this.view=view;
    this.user = user;
    this.view.setUserColor(user.getColor());
  }


}

package board.presenter;


import GameEvents.gameInfoEvent;
import board.view.BoardViewImplFx;
import commonLobby.CLTLobby;
import commonLobby.LobbyUser;
import main.SceneController;

public class BoardPresenter {
  private BoardViewImplFx view;
  private SceneController sc;
  private CLTLobby lobby;

  public BoardPresenter(BoardViewImplFx view, SceneController sc, CLTLobby legacy){
    this.lobby = legacy;
    this.view = view;
    this.sc = sc;
    int render = 0;
    int test= 0;
// zum testen
    String[] x = {"ank.png", "logout.png"};
      for (LobbyUser user : lobby.getUsers()) {
        view.addHouse(x[test]);
        System.out.println(user.getUsername());
        test++;
        test= test % 2;
      }
      sc.toggleFullscreen();
  }

  public BoardViewImplFx getView() {
    return view;
  }

  public SceneController getSc() {
    return sc;
  }

  public void updateBoard(gameInfoEvent e){

  }

}

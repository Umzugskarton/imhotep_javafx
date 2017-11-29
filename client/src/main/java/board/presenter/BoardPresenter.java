package board.presenter;


import GameEvents.gameInfoEvent;
import board.view.BoardViewImplFx;
import main.SceneController;

public class BoardPresenter {
  private BoardViewImplFx view;
  private SceneController sc;

  public BoardPresenter(BoardViewImplFx view, SceneController sc){
    this.view = view;
    this.sc = sc;
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

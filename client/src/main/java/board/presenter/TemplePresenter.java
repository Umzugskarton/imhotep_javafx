package board.presenter;

import board.view.PyramidViemImplFx;
import board.view.TempleViewImplFx;
import commonLobby.CLTLobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Created on 20.12.2017.
 */
public class TemplePresenter implements StoneSitePresenter {
  private TempleViewImplFx templeController;
  private CLTLobby lobby;

  public TemplePresenter(CLTLobby lobby, TempleViewImplFx templeController){
    this.lobby = lobby;
    this.templeController = templeController;
  }

  public void setStones(ArrayList<Integer> stones){
    ArrayList<Group> stoneGroups = templeController.getStones();
    for (int i = 0; i < stones.size() ; i++){
      stoneGroups.get(i).setVisible(true);
      Rectangle r = templeController.getColorStones(i);
      r.setFill(Color.web(lobby.getUserbyLobbyId(stones.get(i)).getColor()));
    }
  }
}

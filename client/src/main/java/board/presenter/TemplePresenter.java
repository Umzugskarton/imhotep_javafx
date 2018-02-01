package board.presenter;

import board.view.TempleViewImplFx;
import data.lobby.CommonLobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class TemplePresenter implements StoneSitePresenter {

  private TempleViewImplFx templeController;
  private CommonLobby lobby;

  public TemplePresenter(CommonLobby lobby, TempleViewImplFx templeController) {
    this.lobby = lobby;
    this.templeController = templeController;
  }

  public void setStones(ArrayList<Integer> stones) {
    ArrayList<Group> stoneGroups = templeController.getStones();
    for (int i = 0; i < stones.size(); i++) {
      stoneGroups.get(i).setVisible(true);
      Rectangle r = templeController.getColorStones(i);
      r.setFill(Color.web(lobby.getUserbyLobbyId(stones.get(i)).getColor()));
    }
  }
}

package board.presenter;

import board.view.BurialChamberViewImplFx;
import data.lobby.CommonLobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BurialChamberPresenter implements StoneSitePresenter {

  private BurialChamberViewImplFx burialChamberController;
  private CommonLobby lobby;

  public BurialChamberPresenter(CommonLobby lobby, BurialChamberViewImplFx burialChamberController) {
    this.lobby = lobby;
    this.burialChamberController = burialChamberController;
  }

  public void setStones(ArrayList<Integer> stones) {
    ArrayList<Group> stoneGroups = burialChamberController.getStones();
    for (int i = 0; i < stones.size(); i++) {
      stoneGroups.get(i).setVisible(true);
      Rectangle r = burialChamberController.getColorStones(i);
      r.setFill(Color.web(lobby.getUserbyLobbyId(stones.get(i)).getColor()));
    }
  }
}

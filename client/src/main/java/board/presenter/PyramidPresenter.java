package board.presenter;

import board.view.PyramidViemImplFx;
import data.lobby.CommonLobby;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PyramidPresenter implements StoneSitePresenter {

  private PyramidViemImplFx pyramidController;
  private CommonLobby lobby;

  public PyramidPresenter(CommonLobby lobby, PyramidViemImplFx pyramidController) {
    this.lobby = lobby;
    this.pyramidController = pyramidController;
  }

  @Override
  public void setStones(ArrayList<Integer> stones) {
    ArrayList<Group> stoneGroups = pyramidController.getStones();
    for (int i = 0; i < stones.size(); i++) {
      stoneGroups.get(i).setVisible(true);
      Rectangle r = pyramidController.getColorStones(i);
      r.setFill(Color.web(lobby.getUserbyLobbyId(stones.get(i)).getColor()));
    }
  }
}

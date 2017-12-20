package board.presenter;

import board.view.PyramidViemImplFx;
import commonLobby.CLTLobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Created on 20.12.2017.
 */
public class PyramidPresenter implements StoneSitePresenter{
  private PyramidViemImplFx pyramidController;
  private CLTLobby lobby;

  public PyramidPresenter(CLTLobby lobby, PyramidViemImplFx pyramidController){
    this.lobby = lobby;
    this.pyramidController = pyramidController;
  }

  public void setStones(ArrayList<Integer> stones){
    ArrayList<Group> stoneGroups = pyramidController.getStones();
    for (int i = 0; i < stones.size() ; i++){
      stoneGroups.get(i).setVisible(true);
      Rectangle r = pyramidController.getColorStones(i);
      r.setFill(Color.web(lobby.getUserbyLobbyId(stones.get(i)).getColor()));
    }
  }
}

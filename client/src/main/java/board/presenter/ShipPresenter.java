package board.presenter;

import board.view.ShipViewImplFx;
import commonLobby.CLTLobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


/**
 * Created on 17.12.2017.
 */
public class ShipPresenter {
  private ShipViewImplFx view;
  private CLTLobby lobby;
  private int[] placement;

  public ShipPresenter(CLTLobby lobby, ShipViewImplFx view, int[] placement) {
    this.view = view;
    this.lobby = lobby;
    this.placement = placement;
    view.getSprite().setId("ship"+placement.length);
    ArrayList<Group> stones = view.getStones();
    for (Group g : stones){
      g.setLayoutX(g.getLayoutX()-(4-placement.length)*2);
      g.setLayoutY(g.getLayoutY()-(4-placement.length)*10);
    }

    int i = 0;
    for (int owner : placement){
      if (owner != -1){
        Group p = view.getStones().get(i);
        p.setVisible(true);
        Rectangle r = view.getColorStones().get(i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(owner).getColor()));
      }
      i++;
    }
  }

}

package board.presenter;

import board.view.StorageViewImplFx;
import data.lobby.LobbyUser;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StoragePresenter {

  private StorageViewImplFx view;
  private LobbyUser user;
  private int stoneCount;

  public StoragePresenter(LobbyUser user, StorageViewImplFx view) {
    this.view = view;
    this.user = user;
    this.view.setUserColor(user.getColor());
  }

  public void setStoneCount(int stones) {
    stoneCount = stones;
    for (int i = 0; i < view.getStones().size(); i++) {
      view.getStones().get(i).setVisible(false);
    }
    for (int i = 0; i < stones; i++) {
      view.getStones().get(i).setVisible(true);
    }
  }

  public void highlightPointsLabel(boolean highlight) {
    if (highlight) {
      this.view.getPointsLabel().setUnderline(true);
      this.view.getPointsLabel().setFont(Font.font("Calibri", FontWeight.BOLD, 14));
    } else {
      this.view.getPointsLabel().setUnderline(false);
      this.view.getPointsLabel().setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
    }
  }

  public void setPoints(int points) {
    this.view.getPointsLabel().setText(points + "");
  }

  public int getStoneCount() {
    return stoneCount;
  }
}

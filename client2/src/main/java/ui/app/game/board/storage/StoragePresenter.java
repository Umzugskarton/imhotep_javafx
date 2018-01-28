package ui.app.game.board.storage;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.LobbyUser;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.presenter.Presenter;
import java.util.ArrayList;

public class StoragePresenter extends Presenter<IStorageView> {
  private LobbyUser user;
  private int stoneCount;
  private Connection connection;


  public StoragePresenter(IStorageView view, EventBus eventBus, Connection connection, LobbyUser user) {
    super(view , eventBus);
    this.user = user;
    this.connection = connection;
    this.view.setUserColor(user.getColor());
    bind();
  }

  public void bind(){
    eventBus.register(this);
  }

  public void setStoneCount(int stones){
    stoneCount = stones;
    ArrayList<Group> stoneGroup = view.getStones();
    for (int i = 0; i < stoneGroup.size() ; i++){
      stoneGroup.get(i).setVisible(false);
    }
    for (int i = 0; i < stones ; i++){
      stoneGroup.get(i).setVisible(true);
    }
  }

  public void highlightPointsLabel(boolean highlight) {
    if(highlight) {
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

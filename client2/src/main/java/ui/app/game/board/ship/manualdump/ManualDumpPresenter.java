package ui.app.game.board.ship.manualdump;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import java.util.ArrayList;
import java.util.Arrays;
import mvp.presenter.Presenter;
import requests.gamemoves.CardType;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;
import ui.app.game.HideDialogEvent;

public class ManualDumpPresenter extends Presenter<IManualDumpView> {

  private final Connection connection;;
  private ArrayList<CardType> myCardTypes;
  private CommonLobby lobby;
  private int[] newOrder;
  private int place;

  public ManualDumpPresenter(IManualDumpView view, EventBus eventBus, Connection connection,  CommonLobby lobby) {
    this.connection = connection;
    this.eventBus = eventBus;
    this.view = view;
    this.lobby = lobby;
    place = 0;
    bind();
  }

  public void bind(){
    eventBus.register(this);
  }

  void setPlace(int id){
    newOrder[place] = id;
    place++;
    if (place == getLoadedCargo()) {
      printArray(newOrder);
      connection.send(new VoyageToStoneSiteManualDumpMove(newOrder, lobby.getLobbyId()));
      eventBus.post(new HideDialogEvent());
    }
  }

  private void printArray(int[] a ){
    StringBuilder x = new StringBuilder();
    x.append("Array = { ");
    for (int i = 0; i < a.length ; i++) {
      x.append(a[i] + " ,");
    }
    x.append("}");
    System.out.println(x.toString());

  }

  public int getPlace() {
    return place;
  }

  void setNewCargoSize(int size){
    newOrder = new int[size];
    Arrays.fill(newOrder, -1);
  }

  private int getLoadedCargo(){
    int loaded = 0;
    for (int i = 0; i <view.getCargo().length ; i++) {
      if (view.getCargo()[i] != -1){
        loaded++;
      }
    }
    return loaded;
  }
}

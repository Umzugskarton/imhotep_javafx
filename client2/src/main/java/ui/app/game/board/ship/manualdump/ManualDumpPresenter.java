package ui.app.game.board.ship.manualdump;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import mvp.presenter.Presenter;
import requests.gamemoves.CardType;
import requests.gamemoves.VoyageToStoneSiteManualDumpMove;

import java.util.ArrayList;

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

  public void setPlace(int id){
    newOrder[place] = id;
    place++;
    if (place +1 == view.getCargo().length)
      connection.send(new VoyageToStoneSiteManualDumpMove(newOrder, lobby.getLobbyId()));
  }
}

package main;

import com.google.common.eventbus.Subscribe;
import data.lobby.CommonLobby;
import events.app.chat.WhisperChatEvent;
import events.app.game.*;
import events.app.main.UserListEvent;
import events.app.lobby.*;
import events.app.profil.ChangeProfilDataEvent;
import events.app.chat.ChatMessageEvent;
import events.app.chat.ChatInfoEvent;
import events.start.login.LoginEvent;
import events.start.login.LoginFailedEvent;
import events.start.login.LoginSuccessfulEvent;
import events.start.registration.RegistrationEvent;
import javafx.application.Platform;

public class EventListener {

    private SceneController sceneController;

    public EventListener(SceneController sc) {
        this.sceneController = sc;
    }

    @Subscribe
    public void loginEventListener(LoginSuccessfulEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getLoginPresenter().processLoginResponse(true, "");
                        this.sceneController.getMainmenuPresenter().getProfilePresenter()
                                .updateUsernameLabel(e.getUser().getUsername());
                        this.sceneController.getMainmenuPresenter().getProfilePresenter()
                                .updateEmailLabel(e.getUser().getEmail());
                        this.sceneController.getMainmenuPresenter().setUsername(e.getUser().getUsername());
                }
        );
    }

    @Subscribe
    public void loginEventListener(LoginFailedEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getLoginPresenter().processLoginResponse(false, e.getReason());
                }
        );
    }

    @Subscribe
    public void registerEventListener(RegistrationEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getRegistrationPresenter()
                            .processRegisterResponse(e.getMsg());
                }
        );
    }

    @Subscribe
    public void changeCredentialEventListener(ChangeProfilDataEvent e) {
        Platform.runLater(
                () -> {
                    if (e.getType() == 1) {
                        this.sceneController.getMainmenuPresenter().getProfilePresenter()
                                .updateEmailLabel(e.getCredential());
                    }
                    this.sceneController.getMainmenuPresenter().getProfilePresenter()
                            .processChangeResponse(e.getSuccess(), e.getMsg());
                    //this.sceneController.getMainmenuPresenter()
                    //this.sceneController.toRegistrationScene();
                }
        );
    }


    @Subscribe
    public void setReadyEventListener(SetReadyToPlayEvent e) {
        Platform.runLater(
                () -> {
                  for (boolean b :e.getReady()){
                    System.out.println("SPECIAL DEBUG MODE : PING ready  e : " +b);
                  }
                    CommonLobby lobby = sceneController.getLobbyPresenter().getCLTLobby();
                    lobby.setReady(e.getReady());
                    sceneController.getLobbyPresenter().updateLobby(lobby);
                }
        );
    }

    @Subscribe
    public void userListEventListener(UserListEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().updateUserlist(e.getUserList());
                    }
                }
        );
    }

    @Subscribe
    public void chatEventListener(ChatMessageEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        if (e.getLobbyId() == null) {
                            this.sceneController.getMainmenuPresenter().getChatPresenter()
                                    .addChatMessage(e.getUser(), e.getMsg());
                        } else {
                            //Hier kann später auch zwischen den Lobbypresentern unterschieden werden wenn mehrere Lobbys möglich sind
                            if (this.sceneController.getLobbyPresenter() != null) {
                                this.sceneController.getLobbyPresenter()
                                        .addChatMessage(e.getUser(), e.getMsg());
                            }
                        }
                    }
                }
        );
    }

    @Subscribe
    public void chatInfoEventListener(ChatInfoEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().getChatPresenter()
                                .addInfoMessage(e.getMsg());
                    }
                }
        );
    }

    @Subscribe
    public void joinEventListener(JoinLobbyEvent e) {
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void leaveLobbyEventListener(LeaveLobbyEvent e) {
        Platform.runLater(
                () -> {
                    if(e.getSuccess()) {
                        this.sceneController.toMainmenuScene();
                        //this.sceneController.getLobbyPresenter().resetUserAfterLeaving();
                    }
                }
        );
    }

    @Subscribe
    public void lobbylistEventListener(LobbyListEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().getGamesPresenter()
                                .updateLobbylist(e.getLobbies());
                    }
                }
        );
    }

    @Subscribe
    public void createEventListener(CreateLobbyEvent e) {
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void lobbyInfoEventListener(LobbyInfoEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter().getGamesPresenter() != null) {

                      this.sceneController.toLobbyScene();

                        CommonLobby temp = e.getLobby();
                        this.sceneController.getLobbyPresenter().setCLTLobby(temp);
                    }
                }
        );
    }

    @Subscribe
    public void changeColorEventListener(ChangeLobbyUserColorEvent e) {
        Platform.runLater(
                () -> {
                    CommonLobby temp = sceneController.getLobbyPresenter().getCLTLobby();
                    temp.getUserbyLobbyId(e.getId()).setColor(e.getColor());
                    sceneController.getLobbyPresenter().updateLobby(temp);
                });
    }

    @Subscribe
    public void whisperEventListener(WhisperChatEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().getChatPresenter()
                                .addWhisper(e.getFrom(), e.getMsg(), true);
                    }
                }
        );
    }

    @Subscribe
    public void gameInfoEventListener(GameInfoEvent e) {
        Platform.runLater(
                () -> {
                    if (sceneController.getBoardPresenter() == null) {
                        sceneController.toBoardScene();
                    }
                    sceneController.getBoardPresenter().updateBoard(e);
                }
        );
    }

    @Subscribe
    public void turnEventListener(TurnEvent e) {
        Platform.runLater(
                () -> {
                    if(sceneController.getBoardPresenter() != null) {
                        sceneController.getBoardPresenter().newTurn(e);
                    }
                }
        );
    }

    @Subscribe
    public void fillUpStorageEventListener(FillUpStorageEvent e) {
        Platform.runLater(
                () -> {
                    if(sceneController.getBoardPresenter() != null) {
                        sceneController.getBoardPresenter().receiveFillUpStorageEvent(e);
                    }
                }
        );
    }

    @Subscribe
    public void shipLoadedEventListener(ShipLoadedEvent e){
      Platform.runLater(
              () -> {
                if(sceneController.getBoardPresenter() != null) {
                  sceneController.getBoardPresenter().receiveShipLoadedEvent(e);
                }
              }
      );
    }

    @Subscribe
    public void alreadyAllocatedErrorListener(AlreadyAllocatedError e){
        Platform.runLater(
                () -> {
                    if(sceneController.getBoardPresenter() != null) {
                        System.out.println("alreadyallocatederror");
                    }
                }
        );
    }

    @Subscribe
    public void shipDockedListener(ShipDockedEvent e){
        Platform.runLater(
                () -> {
                    if(sceneController.getBoardPresenter() != null) {
                        sceneController.getBoardPresenter().shipDocked(e);
                    }
                }
        );
    }

    @Subscribe
    public void updatePointsListener(UpdatePointsEvent e){
        Platform.runLater(
                () -> {
                    if(sceneController.getBoardPresenter() != null) {
                        sceneController.getBoardPresenter().updatePoints(e);
                    }
                }
        );
    }
}
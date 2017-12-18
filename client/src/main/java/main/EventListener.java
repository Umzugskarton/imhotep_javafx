package main;

import GameEvents.*;
import SRVevents.*;
import com.google.common.eventbus.Subscribe;
import commonLobby.CLTLobby;
import javafx.application.Platform;

public class EventListener {

    private SceneController sceneController;

    public EventListener(SceneController sc) {
        this.sceneController = sc;
    }

    @Subscribe
    public void loginEventListener(loginEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getLoginPresenter().processLoginResponse(e.getSuccess(), e.getMsg());
                    if (e.getSuccess()) {
                        this.sceneController.getMainmenuPresenter().getProfilePresenter()
                                .updateUsernameLabel(e.getUsername());
                        this.sceneController.getMainmenuPresenter().getProfilePresenter()
                                .updateEmailLabel(e.getEmail());
                        this.sceneController.getMainmenuPresenter().setUsername(e.getUsername());
                    }
                }
        );
    }

    @Subscribe
    public void registerEventListener(registerEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getRegistrationPresenter()
                            .processRegisterResponse(e.getMsg());
                }
        );
    }

    @Subscribe
    public void changeCredentialEventListener(changeCredentialEvent e) {
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
    public void setReadyEventListener(setReadyEvent e) {
        Platform.runLater(
                () -> {
                    CLTLobby lobby = sceneController.getLobbyPresenter().getCLTLobby();
                    lobby.setReady(e.getReady());
                    sceneController.getLobbyPresenter().updateLobby(lobby);
                }
        );
    }

    @Subscribe
    public void userListEventListener(userListEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().updateUserlist(e.getUserList());
                    }
                }
        );
    }

    @Subscribe
    public void chatEventListener(chatEvent e) {
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
    public void chatInfoEventListener(chatInfoEvent e) {
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
    public void joinEventListener(joinEvent e) {
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void leaveLobbyEventListener(leaveLobbyEvent e) {
        Platform.runLater(
                () -> {

                        this.sceneController.toLoginScene();
                        System.out.print("A");


                }
        );
    }

    @Subscribe
    public void lobbylistEventListener(lobbylistEvent e) {
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
    public void createEventListener(createEvent e) {
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void lobbyInfoEventListener(lobbyInfoEvent e) {
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter().getGamesPresenter() != null) {
                        if (this.sceneController.getLobbyPresenter() == null) {
                            this.sceneController.toLobbyScene();
                        }
                        CLTLobby temp = e.getLobby();
                        this.sceneController.getLobbyPresenter().setCLTLobby(temp);
                    }
                }
        );
    }

    @Subscribe
    public void changeColorEventListener(changeColorEvent e) {
        Platform.runLater(
                () -> {
                    CLTLobby temp = sceneController.getLobbyPresenter().getCLTLobby();
                    temp.getUserbyLobbyId(e.getId()).setColor(e.getColor());
                    sceneController.getLobbyPresenter().updateLobby(temp);
                });
    }

    @Subscribe
    public void whisperEventListener(whisperEvent e) {
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
}
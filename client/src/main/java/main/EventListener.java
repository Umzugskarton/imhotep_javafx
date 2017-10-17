package main;

import javafx.application.Platform;
import com.google.common.eventbus.Subscribe;
import SRVevents.*;
import commonLobby.*;
import org.json.simple.JSONArray;


public class EventListener {
    private SceneController sceneController;

    public EventListener(SceneController sc){
        this.sceneController= sc;
    }

    @Subscribe
    public void loginEventListener(loginEvent e) {
        Platform.runLater(
                () -> {
                    this.sceneController.getLoginPresenter().processLoginResponse(e.getSuccess(), e.getMsg());
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
                        this.sceneController.getMainmenuPresenter().getChatPresenter()
                                .addChatMessage(e.getUser(), e.getMsg());
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
    public void joinEventListener(joinEvent e){
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void lobbylistEventListener(lobbylistEvent e){
        Platform.runLater(
                () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                        this.sceneController.getMainmenuPresenter().getGamesPresenter().updateLobbylist(e.getLobbies());
                    }
                }
        );
    }

    @Subscribe
    public void createEventListener(createEvent e){
        Platform.runLater(
                () -> {
                    if (e.getSuccess()) {
                        this.sceneController.toLobbyScene();
                    }
                }
        );
    }

    @Subscribe
    public void lobbyInfoEventListener(lobbyInfoEvent e){
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
}

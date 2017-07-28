package create.presenter;

import create.view.CreateView;
import create.view.CreateViewImpl;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;

/**
 * Created by fabianrieger on 28.07.17.
 */
public class CreatePresenter {

    private CreateView view;
    private SceneController sceneController;

    public CreatePresenter(SceneController sc) {
        this.sceneController = sc;

        this.view = new CreateViewImpl(this);
    }

    public void createLobby(String name, int size, String pass){
        JSONObject j = ClientCommands.createCommand(name, size, pass);
        this.sceneController.getClientSocket().send(j);
    }

    public CreateView getCreateView(){
        return this.view;
    }

}

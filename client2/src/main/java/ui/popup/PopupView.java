package ui.popup;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvp.view.IView;
import mvp.view.ShowViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupView implements IView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane popupViewRoot;

    private Parent myParent;

    //Subview
    IView view;

    private EventBus eventBus;

    private Stage stage;

    public PopupView(IView view) {
        this.view = view;
        this.stage = new Stage();

        initOwnView();
    }

    public PopupView(String msg, EventBus eventBus, Connection connection) {
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {
        System.out.println("PopupView " + this.view.getRootParent());
        this.popupViewRoot.getChildren().add(this.view.getRootParent());
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/popup/popupView2.fxml", this, eventBus);
    }

    @FXML
    void handleListViewClick(MouseEvent click){

    }

    public void show(){
        this.stage.setScene(new Scene(this.getRootParent()));
        stage.show();
    }

    public void setView(IView view){
        this.view = view;
        this.popupViewRoot.getChildren().clear();
        this.popupViewRoot.getChildren().add(this.view.getRootParent());
    }

    public ShowViewEvent getEventToShowThisView() {
        return null;
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }
}
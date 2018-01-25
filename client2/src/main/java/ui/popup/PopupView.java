package ui.popup;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import mvp.view.IView;
import mvp.view.ShowViewEvent;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupView implements IView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane popupViewRoot;

    @FXML
    private ImageView popupViewImageView;

    private Parent myParent;

    private final EventBus eventBus;

    public PopupView(String msg, EventBus eventBus, Connection connection) {
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/popup/popupView.fxml", this, eventBus);
    }

    @FXML
    void handleListViewClick(MouseEvent click){

    }

    public ShowViewEvent getEventToShowThisView() {
        return null;
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }
}

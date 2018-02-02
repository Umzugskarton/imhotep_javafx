package ui.dialog;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import events.Event;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvp.view.INeedDialogView;
import mvp.view.IView;
import mvp.view.ShowViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogView implements IDialogView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private BorderPane dialogViewRoot;

  @FXML
  private Button closeButton;

  @FXML
  private Pane dialogView;


  private Parent myParent;

  private String titel;

  //ParentView
  INeedDialogView parentView;

  //Subview
  IDialogView view;

  private EventBus eventBus;

  private Stage stage;

  public DialogView(INeedDialogView view, EventBus eventBus) {
    this.parentView = view;
    this.eventBus = eventBus;
    initOwnView();
  }

  /*
  public DialogView(String msg, EventBus eventBus, Connection connection) {
    this.eventBus = eventBus;
    initOwnView();
  }

  */

  @FXML
  void initialize() {

  }


  @Override
  public void initOwnView() {
    if (this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/dialog/dialogView.fxml", this, eventBus);
  }

  @FXML
  private void handleCloseButton(ActionEvent event){
    System.out.println("Push Close Button");
    this.parentView.hideDialog();
  }

  public void setView(IDialogView view) {
    this.view = view;
    this.titel = this.view.getTitle();
    this.dialogView.getChildren().clear();
    this.dialogView.getChildren().add(this.view.getRootParent());
  }

  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  @Override
  public String getTitle() {
    return "Dialog - " + this.titel;
  }
}
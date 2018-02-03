package ui.dialog;

import com.google.common.eventbus.EventBus;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ui.dialog.misc.IDialogableView;

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
  IDialogableView parentView;

  //Subview
  private IDialogView view;


  private Rectangle edgeRect = new Rectangle();

  private EventBus eventBus;

  public DialogView(IDialogableView view, EventBus eventBus) {
    this.parentView = view;
    this.eventBus = eventBus;
    initOwnView();
  }

  @FXML
  void initialize() {
  }


  @Override
  public void initOwnView() {
    if (this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/dialog/DialogView.fxml", this, eventBus);

    //TODO Schöne umrandung einfügen
    //Keine Ahnung wie man die Größe von einem Parent erhausbekomment
    //Umrandung rund machen
    //this.edgeRect.setArcHeight(30.0);
    //this.edgeRect.setArcWidth(30.0);
    //this.edgeRect.widthProperty().bind(this.parentView);
    //this.edgeRect.heightProperty().bind(this.myParent.getScene().heightProperty());
    //this.myParent.setClip(this.edgeRect);
  }

  @FXML
  private void handleCloseButton(ActionEvent event){
    this.parentView.hideDialog();
    this.dialogView.getChildren().clear();
  }

  public void setView(IDialogView view) {
    this.view = view;
    this.titel = this.view.getTitle();
    this.dialogView.getChildren().clear();
    this.dialogView.getChildren().add(this.view.getRootParent());
  }

  @Override
  public Parent getRootParent() {
    return this.myParent;
  }

  @Override
  public String getTitle() {
    return "Dialog - " + this.titel;
  }

  public void showDialog(IDialogView view) {
    setView(view);
  }
}
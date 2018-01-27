package ui.app.game.userinterface;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.scene.Parent;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;


public class UserInterfaceView implements IUserInterfaceView {

  private final INavigateableView parentView;
  private final UserInterfacePresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;

  // Own Parent
  private Parent myParent;

  public UserInterfaceView(INavigateableView parentView, EventBus eventBus, Connection connection, User user){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.user = user;
    this.mainPresenter = new UserInterfacePresenter(this, eventBus, connection, user);
    bind();
    initOwnView();
  }

  private void bind(){
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/game/BoardView.fxml", this, eventBus);
  }

  @Override
  public INavigateableView getParentView() {
    return null;
  }

  @Override
  public ShowViewEvent getEventToShowThisView() {
    return null;
  }

  @Override
  public String getTitle() {
    return "Board";
  }

  @Override
  public Parent getRootParent() {
    return null;
  }
}

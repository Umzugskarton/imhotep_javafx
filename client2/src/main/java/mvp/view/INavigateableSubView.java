package mvp.view;

public interface INavigateableSubView extends INavigateableView {
  INavigateableView getParentView();
}

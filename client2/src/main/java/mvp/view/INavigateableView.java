package mvp.view;

public interface INavigateableView extends IView {
    String getTitle();
    ShowViewEvent getEventToShowThisView();
}

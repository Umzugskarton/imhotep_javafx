package mvp.presenter;

import com.google.common.eventbus.EventBus;
import mvp.view.IView;

public class Presenter<T extends IView> {
    protected T view;
    protected EventBus eventBus;

    public Presenter() {

    }

    public Presenter(T view) {
        setView(view);
    }

    public Presenter(T view, EventBus eventBus) {
        setView(view);
        setEventBus(eventBus);
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}

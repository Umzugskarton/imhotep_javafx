package ui.app.game.checker;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.app.game.GameInfoEvent;

public class Checker {

    private final EventBus eventBus;

    public Checker(EventBus eventBus){
        this.eventBus = eventBus;
        bind();
    }

    private void bind(){
        this.eventBus.register(this);
    }

    @Subscribe
    public void onGameInfoEvent(GameInfoEvent e){
    }


}

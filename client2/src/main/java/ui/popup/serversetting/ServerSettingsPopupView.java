package ui.popup.serversetting;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import ui.popup.PopupView;

public class ServerSettingsPopupView extends PopupView implements IServerSettingsPopup {

    public ServerSettingsPopupView(String msg, EventBus eventBus, Connection connection){
        super(msg, eventBus, connection);
    }
}

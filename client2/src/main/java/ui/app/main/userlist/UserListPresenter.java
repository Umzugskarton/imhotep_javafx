package ui.app.main.userlist;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.main.ChatInfoEvent;
import events.main.UserListEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import mvp.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class UserListPresenter extends Presenter<IUserListView> {

    private final Connection connection;
    private User user;
    private ObservableList<String> users = FXCollections.observableArrayList();


    public UserListPresenter(IUserListView view, EventBus eventBus, Connection connection, User user) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
        bind();
    }

    private void bind() {
        getEventBus().register(this);
    }

    public void updateUserlist(ArrayList<String> userArray) {
        System.out.print("Start updateUserList");
        // Im Chat informieren wer gejoined/leaved ist
        boolean notifyInChat = true;
        if (users.isEmpty()) {
            notifyInChat = false;
        }

        if (notifyInChat) {
            List<String> list = users;
            List<String> joinedList = new ArrayList<>();
            List<String> leftList = new ArrayList<>();

            for (Object user : userArray) {
                joinedList.add(user.toString());
                leftList.add(user.toString());
            }

            joinedList.removeAll(list);
            list.removeAll(leftList);

            for (String username : list) {
                ChatInfoEvent event = new ChatInfoEvent();
                event.setMsg("- " + username + " hat den Chat verlassen");
                getEventBus().post(new ChatInfoEvent("- " + username + " hat den Chat verlassen", Color.RED));
            }

            for (String username : joinedList) {
                getEventBus().post(new ChatInfoEvent("+ " + username + " hat den Chat betreten", Color.GREEN));
            }

            users.toString();
        }

        // Userliste leeren und neu füllen
        users.clear();

        for (Object user : userArray) {
            users.add(user.toString());
        }
    }

    public ObservableList<String> getUserList() {
        return users;
    }

    @Subscribe
    public void onUserListEvent(UserListEvent e) {
        updateUserlist(e.getUserList());
    }
}

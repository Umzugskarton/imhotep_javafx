package ui.start.login;

import mvp.view.ShowViewEvent;

import java.util.Date;

public class ShowLoginViewEvent extends ShowViewEvent {

    private String username;
    private String passwort;

    public ShowLoginViewEvent(){ }

    public ShowLoginViewEvent(String username, String passwort){
       this.username = username;
       this.passwort = passwort;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswort() {
        return passwort;
    }

    //@Override
    public Date getDate() {
        //TODO
        return null;
    }
}

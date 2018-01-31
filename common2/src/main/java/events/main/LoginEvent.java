package events.main;

public class LoginEvent extends MainEvent {
    private String event = "login";
    private boolean success;
    private String username;
    private String email;

    public LoginEvent() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }
}

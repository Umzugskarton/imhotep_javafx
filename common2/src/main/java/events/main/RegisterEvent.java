package events.main;


public class RegisterEvent extends MainEvent {
    private String event = "register";
    private boolean validate;

    public RegisterEvent() {
    }

    public boolean isSuccess() {
        return validate;
    }

    public void setSuccess(boolean validate) {
        this.validate = validate;
    }
}

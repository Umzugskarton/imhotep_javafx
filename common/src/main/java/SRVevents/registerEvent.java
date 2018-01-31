package SRVevents;

public class registerEvent implements Event {
    private String event = "register";
    private String msg;
    private boolean validate;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean validate) {
        this.validate = validate;
    }

    public String getMsg() {
        return this.msg;
    }
}

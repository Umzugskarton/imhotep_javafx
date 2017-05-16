package registration.model;


import registration.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {
    private String password;
    private String username;
    private String passwordRepeat;
    private String userEmail;

    @Override
    public void Model() {
        password = "password";
        username = "username";
        passwordRepeat = "repeat password";
        userEmail = "userEmail";
    }

    @Override
    public void setPassword (String pass) {
        password = pass;
    }

    @Override
    public void setUsername (String user) {
        username = user;
    }

    @Override
    public void setPasswordRepeat (String passRepeat){
        passwordRepeat = passRepeat;
    }

    @Override
    public void setUserEmail (String userMail){
        userEmail = userMail;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }
}

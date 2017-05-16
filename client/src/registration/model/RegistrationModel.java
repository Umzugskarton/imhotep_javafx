package registration.model;

public interface RegistrationModel {

  void Model();
  void setUsername(String user);
  void setPassword(String pass);
  void setPasswordRepeat(String passRepeat);
  void setUserEmail(String userEmail);
  String getUsername();
  String getPassword();
  String getPasswordRepeat();
  String getUserEmail();
}

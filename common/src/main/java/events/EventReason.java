package events;

import java.io.Serializable;

public enum EventReason implements Serializable{
  ALREADY_LOGGED_IN,
  LOGIN_SUCCESSFUL,
  NAME_OR_PASSWORD_WRONG,
  INVALID_REQUEST,
  REGISTRATION_SUCCESSFUL,
  REGISTRATION_FAILED_USER_OR_EMAIL_EXISTS
}
package de.faceco.mashovapi.components;

/**
 * This class detonates a failed login request.
 */
public class LoginFailed implements LoginResponse {
  private String message;
  private transient int errorCode;
  
  LoginFailed() {
  
  }
  
  public String getMessage() {
    return message;
  }
  
  public int getErrorCode() {
    return errorCode;
  }
  
  void setErrorCode(int code) {
    errorCode = code;
  }
}

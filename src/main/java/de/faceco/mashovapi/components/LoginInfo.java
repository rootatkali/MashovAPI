package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.components.logininfo.*;

public class LoginInfo {
  private String sessionId;
  private Credential credential;
  private AccessToken accessToken;
  
  LoginInfo() {
  
  }
  
  public String getSessionId() {
    return sessionId;
  }
  
  public Credential getCredential() {
    return credential;
  }
  
  public AccessToken getAccessToken() {
    return accessToken;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("sessionId", sessionId).add("credential",
        credential).add("accessToken", accessToken).toString();
  }
}

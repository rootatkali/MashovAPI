package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.components.logininfo.*;

/**
 * This class detonates a successful login request.
 */
public final class LoginInfo implements LoginResponse {
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

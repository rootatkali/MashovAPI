package de.faceco.mashovapi.components.logininfo;

import com.google.common.base.MoreObjects;

public class UserOptions {
  private long pushOptions;
  private boolean emailNotifications;
  private boolean shareInfoToLnet;
  
  public UserOptions() {
  
  }
  
  public long getPushOptions() {
    return pushOptions;
  }
  
  public boolean hasEmailNotifications() {
    return emailNotifications;
  }
  
  public boolean hasShareInfoToLnet() {
    return shareInfoToLnet;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("pushOptions", pushOptions).add(
        "emailNotifications", emailNotifications).add("shareInfoToLnet", shareInfoToLnet).toString();
  }
}

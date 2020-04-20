package de.faceco.mashovapi.components.logininfo;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.components.School;

public class AccessToken {
  private UserOptions userOptions;
  private SchoolOptions schoolOptions;
  private int[] roles;
  private Child[] children;
  private Child[] externChildren;
  private String username;
  private String lastLogin;
  private String lastPassSet;
  private String displayName;
  private String gender;
  private int network;
  private School[] userSchools;
  private int[] userSchoolYears;
  
  public AccessToken() {
  
  }
  
  public UserOptions getUserOptions() {
    return userOptions;
  }
  
  public SchoolOptions getSchoolOptions() {
    return schoolOptions;
  }
  
  public int[] getRoles() {
    return roles;
  }
  
  public Child[] getChildren() {
    return children;
  }
  
  public Child[] getExternChildren() {
    return externChildren;
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getLastLogin() {
    return lastLogin;
  }
  
  public String getLastPassSet() {
    return lastPassSet;
  }
  
  public String getDisplayName() {
    return displayName;
  }
  
  public String getGender() {
    return gender;
  }
  
  public int getNetwork() {
    return network;
  }
  
  public School[] getUserSchools() {
    return userSchools;
  }
  
  public int[] getUserSchoolYears() {
    return userSchoolYears;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("userOptions", userOptions).add("schoolOptions",
        schoolOptions).add("roles", roles).add("children", children).add("externChildren",
        externChildren).add("username", username).add("lastLogin", lastLogin).add("lastPassSet",
        lastPassSet).add("displayName", displayName).add("gender", gender).add("network",
        network).add("userSchools", userSchools).add("userSchoolYears", userSchoolYears).toString();
  }
}

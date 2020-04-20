package de.faceco.mashovapi.components.logininfo;

import com.google.common.base.MoreObjects;

public class Credential {
  private String sessionId;
  private String userId;
  private int idNumber;
  private int userType;
  private int roleUserType;
  private int schoolUserType;
  private String idp;
  private boolean hasAuthenticated;
  private boolean hasStronglyAuthenticated;
  private int semel;
  private int year;
  private String correlationId;
  
  public Credential() {
  
  }
  
  public String getSessionId() {
    return sessionId;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public int getIdNumber() {
    return idNumber;
  }
  
  public int getUserType() {
    return userType;
  }
  
  public int getRoleUserType() {
    return roleUserType;
  }
  
  public int getSchoolUserType() {
    return schoolUserType;
  }
  
  public String getIdp() {
    return idp;
  }
  
  public boolean hasAuthenticated() {
    return hasAuthenticated;
  }
  
  public boolean hasStronglyAuthenticated() {
    return hasStronglyAuthenticated;
  }
  
  public int getSchoolId() {
    return semel;
  }
  
  public int getYear() {
    return year;
  }
  
  public String getCorrelationId() {
    return correlationId;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("sessionId", sessionId).add("userId", userId).add("idNumber", idNumber).add("userType", userType).add("roleUserType", roleUserType).add("schoolUserType", schoolUserType).add("idp", idp).add("hasAuthenticated", hasAuthenticated).add("hasStronglyAuthenticated", hasStronglyAuthenticated).add("semel", semel).add("year", year).add("correlationId", correlationId).toString();
  }
}

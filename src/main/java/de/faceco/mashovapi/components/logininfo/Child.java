package de.faceco.mashovapi.components.logininfo;

import com.google.common.base.MoreObjects;

public class Child {
  private String childGuid;
  private String familyName;
  private String privateName;
  private String classCode;
  private int classNum;
  private int[] groups;
  
  public Child() {
  
  }
  
  public String getChildGuid() {
    return childGuid;
  }
  
  public String getFamilyName() {
    return familyName;
  }
  
  public String getPrivateName() {
    return privateName;
  }
  
  public String getClassCode() {
    return classCode;
  }
  
  public int getClassNum() {
    return classNum;
  }
  
  public int[] getGroups() {
    return groups;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("childGuid", childGuid).add("familyName",
        familyName).add("privateName", privateName).add("classCode", classCode).add("classNum",
        classNum).add("groups", groups).toString();
  }
}

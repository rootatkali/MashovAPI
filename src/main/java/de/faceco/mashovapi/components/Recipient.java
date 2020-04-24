package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Recipient {
  private int displayOrder;
  private String cssClass;
  private String value;
  private String valueType;
  private String targetType;
  private String displayName;
  private boolean isGroup;
  
  Recipient() {
  
  }
  
  public int getDisplayOrder() {
    return displayOrder;
  }
  
  public String getCssClass() {
    return cssClass;
  }
  
  public String getValue() {
    return value;
  }
  
  public String getValueType() {
    return valueType;
  }
  
  public String getTargetType() {
    return targetType;
  }
  
  public String getDisplayName() {
    return displayName;
  }
  
  public boolean isGroup() {
    return isGroup;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("displayOrder", displayOrder).add("cssClass",
        cssClass).add("value", value).add("valueType", valueType).add("targetType", targetType).add("displayName", displayName).add("isGroup", isGroup).toString();
  }
}

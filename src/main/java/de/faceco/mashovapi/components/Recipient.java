package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

/**
 * A recipient of a message.
 */
public final class Recipient {
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Recipient recipient = (Recipient) o;
    
    if (displayOrder != recipient.displayOrder) return false;
    if (isGroup != recipient.isGroup) return false;
    if (!cssClass.equals(recipient.cssClass)) return false;
    if (!value.equals(recipient.value)) return false;
    if (!valueType.equals(recipient.valueType)) return false;
    if (!targetType.equals(recipient.targetType)) return false;
    return displayName.equals(recipient.displayName);
  }
  
  @Override
  public int hashCode() {
    int result = displayOrder;
    result = 31 * result + cssClass.hashCode();
    result = 31 * result + value.hashCode();
    result = 31 * result + valueType.hashCode();
    result = 31 * result + targetType.hashCode();
    result = 31 * result + displayName.hashCode();
    result = 31 * result + (isGroup ? 1 : 0);
    return result;
  }
}

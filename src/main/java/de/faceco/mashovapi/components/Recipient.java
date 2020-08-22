package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Objects;

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
        cssClass).add("value", value).add("valueType", valueType).add("targetType", targetType).add("displayName",
        displayName).add("isGroup", isGroup).toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recipient recipient = (Recipient) o;
    return displayOrder == recipient.displayOrder &&
        isGroup == recipient.isGroup &&
        Objects.equals(cssClass, recipient.cssClass) &&
        Objects.equals(value, recipient.value) &&
        Objects.equals(valueType, recipient.valueType) &&
        Objects.equals(targetType, recipient.targetType) &&
        Objects.equals(displayName, recipient.displayName);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(displayOrder, cssClass, value, valueType, targetType, displayName, isGroup);
  }
}

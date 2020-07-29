package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public final class MoodleAssignment {
  private long itemId;
  private String groupId;
  private String itemName;
  private String groupName;
  private int grade;
  private long date;
  private long startTime;
  private long endTime;
  private String state;
  private long linkId;
  
  MoodleAssignment() {
  
  }
  
  public long getItemId() {
    return itemId;
  }
  
  public String getGroupId() {
    return groupId;
  }
  
  public String getItemName() {
    return itemName;
  }
  
  public String getGroupName() {
    return groupName;
  }
  
  public int getGrade() {
    return grade;
  }
  
  public long getDate() {
    return date;
  }
  
  public long getStartTime() {
    return startTime;
  }
  
  public long getEndTime() {
    return endTime;
  }
  
  public String getState() {
    return state;
  }
  
  public long getLinkId() {
    return linkId;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("itemId", itemId)
        .add("groupId", groupId)
        .add("itemName", itemName)
        .add("groupName", groupName)
        .add("grade", grade)
        .add("date", date)
        .add("startTime", startTime)
        .add("endTime", endTime)
        .add("state", state)
        .add("linkId", linkId)
        .toString();
  }
}

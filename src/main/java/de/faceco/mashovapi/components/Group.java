package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

public class Group implements Comparable<Group> {
  private String groupLevel;
  private int groupId;
  private String groupName;
  private String subjectName;
  private Teacher[] groupTeachers;
  private Teacher[] groupInactiveTeachers;
  
  Group() {
  
  }
  
  public String getGroupLevel() {
    return groupLevel;
  }
  
  public int getGroupId() {
    return groupId;
  }
  
  public String getGroupName() {
    return groupName;
  }
  
  public String getSubjectName() {
    return subjectName;
  }
  
  public Teacher[] getGroupTeachers() {
    return groupTeachers;
  }
  
  public Teacher[] getGroupInactiveTeachers() {
    return groupInactiveTeachers;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("groupLevel", groupLevel).add("groupId", groupId).add("groupName", groupName).add("subjectName", subjectName).add("groupTeachers", groupTeachers).add("groupInactiveTeachers", groupInactiveTeachers).toString();
  }
  
  @Override
  public int compareTo(@NotNull Group g) {
    return ComparisonChain.start().compare(groupName, g.groupName).compare(groupId,
        g.groupId).result();
  }
}

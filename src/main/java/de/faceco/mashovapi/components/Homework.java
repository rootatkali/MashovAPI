package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class Homework {
  private String lessonDate;
  private String homework;
  private int groupId;
  private String subjectName;
  
  Homework() {
  
  }
  
  public String getLessonDate() {
    return lessonDate;
  }
  
  public String getHomework() {
    return homework;
  }
  
  public int getGroupId() {
    return groupId;
  }
  
  public String getSubjectName() {
    return subjectName;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("lessonDate", lessonDate)
        .add("homework", homework)
        .add("groupId", groupId)
        .add("subjectName", subjectName)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Homework homework1 = (Homework) o;
    return groupId == homework1.groupId &&
        Objects.equals(lessonDate, homework1.lessonDate) &&
        Objects.equals(homework, homework1.homework) &&
        Objects.equals(subjectName, homework1.subjectName);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(lessonDate, homework, groupId, subjectName);
  }
}

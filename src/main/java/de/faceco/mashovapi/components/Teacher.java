package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Teacher {
  private String teacherGuid;
  private String teacherName;
  
  Teacher() {
  
  }
  
  public String getTeacherGuid() {
    return teacherGuid;
  }
  
  public String getTeacherName() {
    return teacherName;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("teacherGuid", teacherGuid).add("teacherName",
        teacherName).toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Teacher t = (Teacher) o;
    return Objects.equal(teacherGuid, t.teacherGuid);
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(teacherGuid);
  }
}

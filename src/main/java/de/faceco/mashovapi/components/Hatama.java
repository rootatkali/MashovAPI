package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class Hatama {
  private String studentGuid;
  private int code;
  private String name;
  private String remark;
  
  Hatama() {
  
  }
  
  public String getStudentGuid() {
    return studentGuid;
  }
  
  public int getCode() {
    return code;
  }
  
  public String getName() {
    return name;
  }
  
  public String getRemark() {
    return remark;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("studentGuid", studentGuid)
        .add("code", code)
        .add("name", name)
        .add("remark", remark)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hatama hatama = (Hatama) o;
    return code == hatama.code &&
        Objects.equals(studentGuid, hatama.studentGuid) &&
        Objects.equals(name, hatama.name) &&
        Objects.equals(remark, hatama.remark);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(studentGuid, code, name, remark);
  }
}

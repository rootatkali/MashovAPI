package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public final class BagrutTime {
  private long id;
  private String name;
  private int moed;
  private int semel;
  private int examType;
  private String examDate;
  private String examStartTime;
  private String examEndTime;
  private String endEditTime;
  
  BagrutTime() {
  
  }
  
  public long getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public int getMoed() {
    return moed;
  }
  
  public int getSemel() {
    return semel;
  }
  
  public int getExamType() {
    return examType;
  }
  
  public String getExamDate() {
    return examDate;
  }
  
  public String getExamStartTime() {
    return examStartTime;
  }
  
  public String getExamEndTime() {
    return examEndTime;
  }
  
  public String getEndEditTime() {
    return endEditTime;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("moed", moed)
        .add("semel", semel)
        .add("examType", examType)
        .add("examDate", examDate)
        .add("examStartTime", examStartTime)
        .add("examEndTime", examEndTime)
        .add("endEditTime", endEditTime)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BagrutTime that = (BagrutTime) o;
    return id == that.id &&
        moed == that.moed &&
        semel == that.semel &&
        examType == that.examType &&
        Objects.equals(name, that.name) &&
        Objects.equals(examDate, that.examDate) &&
        Objects.equals(examStartTime, that.examStartTime) &&
        Objects.equals(examEndTime, that.examEndTime) &&
        Objects.equals(endEditTime, that.endEditTime);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, name, moed, semel, examType, examDate, examStartTime, examEndTime, endEditTime);
  }
}

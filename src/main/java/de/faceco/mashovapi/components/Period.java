package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

public class Period implements Comparable<Period> {
  private int lessonNumber;
  private String startTime;
  private String endTime;
  
  Period() {
  
  }
  
  public int getLessonNumber() {
    return lessonNumber;
  }
  
  public String getStartTime() {
    return startTime;
  }
  
  public String getEndTime() {
    return endTime;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("lessonNumber", lessonNumber).add("startTime",
        startTime).add("endTime", endTime).toString();
  }
  
  @Override
  public int compareTo(@NotNull Period p) {
    return ComparisonChain.start().compare(lessonNumber, p.lessonNumber).result();
  }
}

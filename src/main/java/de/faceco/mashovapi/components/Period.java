package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public class Period {
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
}

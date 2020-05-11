package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Ints;
import org.jetbrains.annotations.NotNull;

/**
 * This data type specifies a lesson slot in a day - the actual time of the lesson start and end.
 */
public final class Period implements Comparable<Period> {
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
    return Integer.compare(lessonNumber, p.lessonNumber);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Period p = (Period) o;
    
    if (lessonNumber != p.lessonNumber) return false;
    if (!startTime.equals(p.startTime)) return false;
    return endTime.equals(p.endTime);
  }
  
  @Override
  public int hashCode() {
    return lessonNumber;
  }
}

package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

/**
 * Lesson in a timetable.
 *
 * <p>Natural order is defined by {@link TimeTable}</p>
 */
public class Lesson implements Comparable<Lesson> {
  private TimeTable timeTable;
  private Group groupDetails;
  
  Lesson() {
  
  }
  
  public TimeTable getTimeTable() {
    return timeTable;
  }
  
  public Group getGroupDetails() {
    return groupDetails;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("timeTable", timeTable).add("groupDetails",
        groupDetails).toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Lesson l = (Lesson) o;
    
    if (!timeTable.equals(l.timeTable)) return false;
    return groupDetails.equals(l.groupDetails);
  }
  
  @Override
  public int hashCode() {
    int result = timeTable.hashCode();
    result = 31 * result + groupDetails.hashCode();
    return result;
  }
  
  @Override
  public int compareTo(@NotNull Lesson l) {
    return ComparisonChain.start().compare(timeTable, l.timeTable).result();
  }
}

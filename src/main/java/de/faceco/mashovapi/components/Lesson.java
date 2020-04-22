package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

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
  public int compareTo(@NotNull Lesson l) {
    return ComparisonChain.start().compare(timeTable, l.timeTable).result();
  }
}

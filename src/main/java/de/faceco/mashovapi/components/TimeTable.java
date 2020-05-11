package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

/**
 * Contains the time, location and group ID of a lesson in a timetable.
 */
public final class TimeTable implements Comparable<TimeTable>{
  private int groupId;
  private int day;
  private int lesson;
  private String roomNum;
  
  TimeTable() {
  
  }
  
  public int getGroupId() {
    return groupId;
  }
  
  public int getDay() {
    return day;
  }
  
  public int getLesson() {
    return lesson;
  }
  
  public String getRoomNum() {
    return roomNum;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("groupId", groupId).add("day", day).add("lesson",
        lesson).add("roomNum", roomNum).toString();
  }
  
  @Override
  public int compareTo(@NotNull TimeTable t) {
    return ComparisonChain.start().compare(day, t.day).compare(lesson, t.lesson).result();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    TimeTable timeTable = (TimeTable) o;
    
    if (groupId != timeTable.groupId) return false;
    if (day != timeTable.day) return false;
    if (lesson != timeTable.lesson) return false;
    return roomNum.equals(timeTable.roomNum);
  }
  
  @Override
  public int hashCode() {
    int result = groupId;
    result = 31 * result + day;
    result = 31 * result + lesson;
    result = 31 * result + roomNum.hashCode();
    return result;
  }
}

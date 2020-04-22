package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

public class TimeTable implements Comparable<TimeTable>{
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
}

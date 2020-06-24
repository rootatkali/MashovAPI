package de.faceco.mashovapi;

import com.google.common.base.MoreObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minute;
  private int second;
  
  private DateTime(int year, int month, int day, int hour, int minute, int second) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }
  
  public static DateTime parse(String s) {
    int year = Integer.parseInt(s.substring(0, 4));
    int month = Integer.parseInt(s.substring(5, 7));
    int day = Integer.parseInt(s.substring(8, 10));
    int hour = Integer.parseInt(s.substring(11, 13));
    int minute = Integer.parseInt(s.substring(14, 16));
    int second = Integer.parseInt(s.substring(17));
    return new DateTime(year, month, day, hour, minute, second);
  }
  
  public int getYear() {
    return year;
  }
  
  public int getMonth() {
    return month;
  }
  
  public int getDay() {
    return day;
  }
  
  public int getHour() {
    return hour;
  }
  
  public int getMinute() {
    return minute;
  }
  
  public int getSecond() {
    return second;
  }
  
  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
    month--;
    Calendar.getInstance().set(year, month, day, hour, minute, second);
    month++;
    return sdf.format(Calendar.getInstance().getTime());
  }
}

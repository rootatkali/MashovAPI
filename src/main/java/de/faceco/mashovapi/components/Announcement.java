package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@SuppressWarnings("SpellCheckingInspection")
public final class Announcement {
  @SerializedName("eventid")
  private int eventId;
  @SerializedName("eventtext")
  private String eventText;
  private String font;
  private String color;
  @SerializedName("classcode")
  private String classCode;
  @SerializedName("classnum")
  private Integer classNum;
  private String expirationDate;
  private String channel;
  private boolean inSite;
  
  Announcement() {
  
  }
  
  public int getEventId() {
    return eventId;
  }
  
  public String getEventText() {
    return eventText;
  }
  
  public String getFont() {
    return font;
  }
  
  public String getColor() {
    return color;
  }
  
  public String getClassCode() {
    return classCode;
  }
  
  public Integer getClassNum() {
    return classNum;
  }
  
  public String getExpirationDate() {
    return expirationDate;
  }
  
  public String getChannel() {
    return channel;
  }
  
  public boolean isInSite() {
    return inSite;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("eventId", eventId)
        .add("eventText", eventText)
        .add("font", font)
        .add("color", color)
        .add("classCode", classCode)
        .add("classNum", classNum)
        .add("expirationDate", expirationDate)
        .add("channel", channel)
        .add("inSite", inSite)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Announcement that = (Announcement) o;
    return eventId == that.eventId &&
        inSite == that.inSite &&
        Objects.equals(eventText, that.eventText) &&
        Objects.equals(font, that.font) &&
        Objects.equals(color, that.color) &&
        Objects.equals(classCode, that.classCode) &&
        Objects.equals(classNum, that.classNum) &&
        Objects.equals(expirationDate, that.expirationDate) &&
        Objects.equals(channel, that.channel);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(eventId, eventText, font, color, classCode, classNum, expirationDate, channel, inSite);
  }
}
